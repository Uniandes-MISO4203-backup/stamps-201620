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
package co.edu.uniandes.csw.stamps.test.logic;

import co.edu.uniandes.csw.stamps.ejbs.TShirtLogic;
import co.edu.uniandes.csw.stamps.api.ITShirtLogic;
import co.edu.uniandes.csw.stamps.entities.TShirtEntity;
import co.edu.uniandes.csw.stamps.persistence.TShirtPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class TShirtLogicTest {

    /**
     * @generated
     */

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ITShirtLogic tShirtLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<TShirtEntity> data = new ArrayList<TShirtEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TShirtEntity.class.getPackage())
                .addPackage(TShirtLogic.class.getPackage())
                .addPackage(ITShirtLogic.class.getPackage())
                .addPackage(TShirtPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
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
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from TShirtEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            TShirtEntity entity = factory.manufacturePojo(TShirtEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un TShirt
     *
     * @generated
     */
    @Test
    public void createTShirtTest() {
        TShirtEntity newEntity = factory.manufacturePojo(TShirtEntity.class);
        TShirtEntity result = tShirtLogic.createTShirt(newEntity);
        Assert.assertNotNull(result);
        TShirtEntity entity = em.find(TShirtEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getSize(), entity.getSize());
        Assert.assertEquals(newEntity.getColor(), entity.getColor());
        Assert.assertEquals(newEntity.getPrice(), entity.getPrice());
    }

    /**
     * Prueba para consultar la lista de TShirts
     *
     * @generated
     */
    @Test
    public void getTShirtsTest() {
        List<TShirtEntity> list = tShirtLogic.getTShirts();
        Assert.assertEquals(data.size(), list.size());
        for (TShirtEntity entity : list) {
            boolean found = false;
            for (TShirtEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un TShirt
     *
     * @generated
     */
    @Test
    public void getTShirtTest() {
        TShirtEntity entity = data.get(0);
        TShirtEntity resultEntity = tShirtLogic.getTShirt(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getSize(), resultEntity.getSize());
        Assert.assertEquals(entity.getColor(), resultEntity.getColor());
        Assert.assertEquals(entity.getPrice(), resultEntity.getPrice());
    }

    /**
     * Prueba para eliminar un TShirt
     *
     * @generated
     */
    @Test
    public void deleteTShirtTest() {
        TShirtEntity entity = data.get(1);
        tShirtLogic.deleteTShirt(entity.getId());
        TShirtEntity deleted = em.find(TShirtEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un TShirt
     *
     * @generated
     */
    @Test
    public void updateTShirtTest() {
        TShirtEntity entity = data.get(0);
        TShirtEntity pojoEntity = factory.manufacturePojo(TShirtEntity.class);

        pojoEntity.setId(entity.getId());

        tShirtLogic.updateTShirt(pojoEntity);

        TShirtEntity resp = em.find(TShirtEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getSize(), resp.getSize());
        Assert.assertEquals(pojoEntity.getColor(), resp.getColor());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
    }
}

