/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.stamps.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.stamps.entities.StampEntity;
import co.edu.uniandes.csw.stamps.entities.ArtistEntity;
import co.edu.uniandes.csw.stamps.dtos.minimum.StampMinimumDTO;
import co.edu.uniandes.csw.stamps.resources.StampResource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@RunWith(Arquillian.class)
public class StampTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String stampPath = "stamps";
    private final static List<StampEntity> oraculo = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;    
    private final String artistPath = "artists";
    ArtistEntity fatherArtistEntity;

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(StampResource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @PersistenceContext(unitName = "StampsPU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private void clearData() {
        
        em.createQuery("delete from StampEntity").executeUpdate();
        em.createQuery("delete from ArtistEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherArtistEntity = factory.manufacturePojo(ArtistEntity.class);
        fatherArtistEntity.setId(1L);
        em.persist(fatherArtistEntity);
        
        for (int i = 0; i < 3; i++) {            
            StampEntity stamp = factory.manufacturePojo(StampEntity.class);
            stamp.setId(i + 1L);
            stamp.setArtist(fatherArtistEntity);
            em.persist(stamp);
            oraculo.add(stamp);
        }
    }

    /**
     * Login para poder consultar los diferentes servicios
     *
     * @param username Nombre de usuario
     * @param password Clave del usuario
     * @return Cookie con información de la sesión del usuario
     * @generated
     */
    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = target.path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }


    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void setUpTest() {
        target = createWebTarget();
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Prueba para crear un Stamp
     *
     * @generated
     */
    @Test
    public void createStampTest() throws IOException {
        PodamFactory factory = new PodamFactoryImpl();
        StampMinimumDTO stamp = factory.manufacturePojo(StampMinimumDTO.class);
        Cookie cookieSessionId = login(username, password);
        Response response = target
            .path(artistPath).path(fatherArtistEntity.getId().toString())
          .path(stampPath)
            .request().cookie(cookieSessionId)
            .post(Entity.entity(stamp, MediaType.APPLICATION_JSON));
        
        StampMinimumDTO  stampTest = (StampMinimumDTO) response.readEntity(StampMinimumDTO.class);
        Assert.assertEquals(stamp.getName(), stampTest.getName());
        Assert.assertEquals(stamp.getImage(), stampTest.getImage());
        Assert.assertEquals(stamp.getPrice(), stampTest.getPrice());
        Assert.assertEquals(Created, response.getStatus());
        StampEntity entity = em.find(StampEntity.class, stampTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Stamp
     *
     * @generated
     */
    @Test
    public void getStampByIdTest() {
        Cookie cookieSessionId = login(username, password);
        StampMinimumDTO stampTest = target
            .path(artistPath).path(fatherArtistEntity.getId().toString())
            .path(stampPath)
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(StampMinimumDTO.class);
        
        Assert.assertEquals(stampTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(stampTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(stampTest.getImage(), oraculo.get(0).getImage());
        Assert.assertEquals(stampTest.getPrice(), oraculo.get(0).getPrice());
    }

    /**
     * Prueba para consultar la lista de Stamps
     *
     * @generated
     */
    @Test
    public void listStampTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target
            .path(artistPath).path(fatherArtistEntity.getId().toString())
            .path(stampPath)
            .request().cookie(cookieSessionId).get();
        
        String listStamp = response.readEntity(String.class);
        List<StampMinimumDTO> listStampTest = new ObjectMapper().readValue(listStamp, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listStampTest.size());
    }

    /**
     * Prueba para actualizar un Stamp
     *
     * @generated
     */
    @Test
    public void updateStampTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        StampMinimumDTO stamp = new StampMinimumDTO(oraculo.get(0));
        PodamFactory factory = new PodamFactoryImpl();
        StampMinimumDTO stampChanged = factory.manufacturePojo(StampMinimumDTO.class);
        stamp.setName(stampChanged.getName());
        stamp.setImage(stampChanged.getImage());
        stamp.setPrice(stampChanged.getPrice());
        Response response = target
            .path(artistPath).path(fatherArtistEntity.getId().toString())
          .path(stampPath)
            .path(stamp.getId().toString())
            .request().cookie(cookieSessionId).put(Entity.entity(stamp, MediaType.APPLICATION_JSON));
        
        StampMinimumDTO stampTest = (StampMinimumDTO) response.readEntity(StampMinimumDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(stamp.getName(), stampTest.getName());
        Assert.assertEquals(stamp.getImage(), stampTest.getImage());
        Assert.assertEquals(stamp.getPrice(), stampTest.getPrice());
    }
    
    /**
     * Prueba para eliminar un Stamp
     *
     * @generated
     */
    @Test
    public void deleteStampTest() {
        Cookie cookieSessionId = login(username, password);
        StampMinimumDTO stamp = new StampMinimumDTO(oraculo.get(0));
        Response response = target
            .path(artistPath).path(fatherArtistEntity.getId().toString())
            .path(stampPath)
            .path(stamp.getId().toString())
            .request().cookie(cookieSessionId).delete();
        
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
