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
import co.edu.uniandes.csw.stamps.entities.TShirtEntity;
import co.edu.uniandes.csw.stamps.dtos.minimum.TShirtDTO;
import co.edu.uniandes.csw.stamps.resources.TShirtResource;
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
public class TShirtTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String tShirtPath = "tShirts";
    private final static List<TShirtEntity> oraculo = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;    

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
                .addPackage(TShirtResource.class.getPackage())
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
        
        em.createQuery("delete from TShirtEntity").executeUpdate();    
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {            
            TShirtEntity tShirt = factory.manufacturePojo(TShirtEntity.class);
            tShirt.setId(i + 1L);
            em.persist(tShirt);
            oraculo.add(tShirt);
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
     * Prueba para crear un TShirt
     *
     * @generated
     */
    @Test
    public void createTShirtTest() throws IOException {
        PodamFactory factory = new PodamFactoryImpl();
        TShirtDTO tShirt = factory.manufacturePojo(TShirtDTO.class);
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(tShirtPath)
            .request().cookie(cookieSessionId)
            .post(Entity.entity(tShirt, MediaType.APPLICATION_JSON));
        
        TShirtDTO  tshirtTest = (TShirtDTO) response.readEntity(TShirtDTO.class);
        Assert.assertEquals(tShirt.getName(), tshirtTest.getName());
        Assert.assertEquals(tShirt.getSize(), tshirtTest.getSize());
        Assert.assertEquals(tShirt.getColor(), tshirtTest.getColor());
        Assert.assertEquals(tShirt.getPrice(), tshirtTest.getPrice());
        Assert.assertEquals(Created, response.getStatus());
        TShirtEntity entity = em.find(TShirtEntity.class, tshirtTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un TShirt
     *
     * @generated
     */
    @Test
    public void getTShirtByIdTest() {
        Cookie cookieSessionId = login(username, password);
        TShirtDTO tshirtTest = target.path(tShirtPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(TShirtDTO.class);
        
        Assert.assertEquals(tshirtTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(tshirtTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(tshirtTest.getSize(), oraculo.get(0).getSize());
        Assert.assertEquals(tshirtTest.getColor(), oraculo.get(0).getColor());
        Assert.assertEquals(tshirtTest.getPrice(), oraculo.get(0).getPrice());
    }

    /**
     * Prueba para consultar la lista de TShirts
     *
     * @generated
     */
    @Test
    public void listTShirtTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(tShirtPath)
                .request().cookie(cookieSessionId).get();
        
        String listTShirt = response.readEntity(String.class);
        List<TShirtDTO> listTShirtTest = new ObjectMapper().readValue(listTShirt, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listTShirtTest.size());
    }

    /**
     * Prueba para actualizar un TShirt
     *
     * @generated
     */
    @Test
    public void updateTShirtTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TShirtDTO tShirt = new TShirtDTO(oraculo.get(0));
        PodamFactory factory = new PodamFactoryImpl();
        TShirtDTO tShirtChanged = factory.manufacturePojo(TShirtDTO.class);
        tShirt.setName(tShirtChanged.getName());
        tShirt.setSize(tShirtChanged.getSize());
        tShirt.setColor(tShirtChanged.getColor());
        tShirt.setPrice(tShirtChanged.getPrice());
        Response response = target.path(tShirtPath).path(tShirt.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(tShirt, MediaType.APPLICATION_JSON));
        
        TShirtDTO tshirtTest = (TShirtDTO) response.readEntity(TShirtDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(tShirt.getName(), tshirtTest.getName());
        Assert.assertEquals(tShirt.getSize(), tshirtTest.getSize());
        Assert.assertEquals(tShirt.getColor(), tshirtTest.getColor());
        Assert.assertEquals(tShirt.getPrice(), tshirtTest.getPrice());
    }
    
    /**
     * Prueba para eliminar un TShirt
     *
     * @generated
     */
    @Test
    public void deleteTShirtTest() {
        Cookie cookieSessionId = login(username, password);
        TShirtDTO tShirt = new TShirtDTO(oraculo.get(0));
        Response response = target.path(tShirtPath).path(tShirt.getId().toString())
                .request().cookie(cookieSessionId).delete();
        
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
