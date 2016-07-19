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
import co.edu.uniandes.csw.stamps.entities.ItemEntity;
import co.edu.uniandes.csw.stamps.entities.ClientEntity;
import co.edu.uniandes.csw.stamps.dtos.minimum.ItemMinimumDTO;
import co.edu.uniandes.csw.stamps.resources.ItemResource;
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
public class ItemTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String itemPath = "wishList";
    private final static List<ItemEntity> oraculo = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;    
    private final String clientPath = "clients";
    ClientEntity fatherClientEntity;

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
                .addPackage(ItemResource.class.getPackage())
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
        
        em.createQuery("delete from ItemEntity").executeUpdate();
        em.createQuery("delete from ClientEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherClientEntity = factory.manufacturePojo(ClientEntity.class);
        fatherClientEntity.setId(1L);
        em.persist(fatherClientEntity);
        
        for (int i = 0; i < 3; i++) {            
            ItemEntity item = factory.manufacturePojo(ItemEntity.class);
            item.setId(i + 1L);
            item.setClient(fatherClientEntity);
            em.persist(item);
            oraculo.add(item);
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
     * Prueba para crear un Item
     *
     * @generated
     */
    @Test
    public void createItemTest() throws IOException {
        PodamFactory factory = new PodamFactoryImpl();
        ItemMinimumDTO item = factory.manufacturePojo(ItemMinimumDTO.class);
        Cookie cookieSessionId = login(username, password);
        Response response = target
            .path(clientPath).path(fatherClientEntity.getId().toString())
          .path(itemPath)
            .request().cookie(cookieSessionId)
            .post(Entity.entity(item, MediaType.APPLICATION_JSON));
        
        ItemMinimumDTO  itemTest = (ItemMinimumDTO) response.readEntity(ItemMinimumDTO.class);
        Assert.assertEquals(item.getName(), itemTest.getName());
        Assert.assertEquals(item.getQty(), itemTest.getQty());
        Assert.assertEquals(Created, response.getStatus());
        ItemEntity entity = em.find(ItemEntity.class, itemTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Item
     *
     * @generated
     */
    @Test
    public void getItemByIdTest() {
        Cookie cookieSessionId = login(username, password);
        ItemMinimumDTO itemTest = target
            .path(clientPath).path(fatherClientEntity.getId().toString())
            .path(itemPath)
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(ItemMinimumDTO.class);
        
        Assert.assertEquals(itemTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(itemTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(itemTest.getQty(), oraculo.get(0).getQty());
    }

    /**
     * Prueba para consultar la lista de Items
     *
     * @generated
     */
    @Test
    public void listItemTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target
            .path(clientPath).path(fatherClientEntity.getId().toString())
            .path(itemPath)
            .request().cookie(cookieSessionId).get();
        
        String listItem = response.readEntity(String.class);
        List<ItemMinimumDTO> listItemTest = new ObjectMapper().readValue(listItem, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listItemTest.size());
    }

    /**
     * Prueba para actualizar un Item
     *
     * @generated
     */
    @Test
    public void updateItemTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ItemMinimumDTO item = new ItemMinimumDTO(oraculo.get(0));
        PodamFactory factory = new PodamFactoryImpl();
        ItemMinimumDTO itemChanged = factory.manufacturePojo(ItemMinimumDTO.class);
        item.setName(itemChanged.getName());
        item.setQty(itemChanged.getQty());
        Response response = target
            .path(clientPath).path(fatherClientEntity.getId().toString())
          .path(itemPath)
            .path(item.getId().toString())
            .request().cookie(cookieSessionId).put(Entity.entity(item, MediaType.APPLICATION_JSON));
        
        ItemMinimumDTO itemTest = (ItemMinimumDTO) response.readEntity(ItemMinimumDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(item.getName(), itemTest.getName());
        Assert.assertEquals(item.getQty(), itemTest.getQty());
    }
    
    /**
     * Prueba para eliminar un Item
     *
     * @generated
     */
    @Test
    public void deleteItemTest() {
        Cookie cookieSessionId = login(username, password);
        ItemMinimumDTO item = new ItemMinimumDTO(oraculo.get(0));
        Response response = target
            .path(clientPath).path(fatherClientEntity.getId().toString())
            .path(itemPath)
            .path(item.getId().toString())
            .request().cookie(cookieSessionId).delete();
        
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
