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

import co.edu.uniandes.csw.stamps.ejbs.StampLogic;
import co.edu.uniandes.csw.stamps.api.IStampLogic;
import co.edu.uniandes.csw.stamps.entities.StampEntity;
import co.edu.uniandes.csw.stamps.entities.ArtistEntity;
import co.edu.uniandes.csw.stamps.persistence.StampPersistence;
import co.edu.uniandes.csw.stamps.entities.ArtistEntity;
import co.edu.uniandes.csw.stamps.entities.CategoryEntity;
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
public class StampLogicTest {

    /**
     * @generated
     */
    ArtistEntity fatherEntity;

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IStampLogic stampLogic;

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
    private List<StampEntity> data = new ArrayList<StampEntity>();

    /**
     * @generated
     */
    private List<ArtistEntity> artistData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CategoryEntity> categoryData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(StampEntity.class.getPackage())
                .addPackage(StampLogic.class.getPackage())
                .addPackage(IStampLogic.class.getPackage())
                .addPackage(StampPersistence.class.getPackage())
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
        em.createQuery("delete from StampEntity").executeUpdate();
        em.createQuery("delete from ArtistEntity").executeUpdate();
        em.createQuery("delete from CategoryEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CategoryEntity category = factory.manufacturePojo(CategoryEntity.class);
            em.persist(category);
            categoryData.add(category);
        }

        fatherEntity = factory.manufacturePojo(ArtistEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            StampEntity entity = factory.manufacturePojo(StampEntity.class);
            entity.setArtist(fatherEntity);

            entity.getCategory().add(categoryData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }
   /**
     * Prueba para crear un Stamp
     *
     * @generated
     */
    @Test
    public void createStampTest() {
        StampEntity newEntity = factory.manufacturePojo(StampEntity.class);
        StampEntity result = stampLogic.createStamp(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        StampEntity entity = em.find(StampEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
        Assert.assertEquals(newEntity.getPrice(), entity.getPrice());
    }

    /**
     * Prueba para consultar la lista de Stamps
     *
     * @generated
     */
    @Test
    public void getStampsTest() {
        List<StampEntity> list = stampLogic.getStamps(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (StampEntity entity : list) {
            boolean found = false;
            for (StampEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Stamp
     *
     * @generated
     */
    @Test
    public void getStampTest() {
        StampEntity entity = data.get(0);
        StampEntity resultEntity = stampLogic.getStamp(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getImage(), resultEntity.getImage());
        Assert.assertEquals(entity.getPrice(), resultEntity.getPrice());
    }

    /**
     * Prueba para eliminar un Stamp
     *
     * @generated
     */
    @Test
    public void deleteStampTest() {
        StampEntity entity = data.get(1);
        stampLogic.deleteStamp(entity.getId());
        StampEntity deleted = em.find(StampEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Stamp
     *
     * @generated
     */
    @Test
    public void updateStampTest() {
        StampEntity entity = data.get(0);
        StampEntity pojoEntity = factory.manufacturePojo(StampEntity.class);

        pojoEntity.setId(entity.getId());

        stampLogic.updateStamp(fatherEntity.getId(), pojoEntity);

        StampEntity resp = em.find(StampEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getImage(), resp.getImage());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
    }

    /**
     * Prueba para obtener una instancia de Category asociada a una instancia Stamp
     *
     * @generated
     */
    @Test
    public void getCategoryTest() {
        StampEntity entity = data.get(0);
        CategoryEntity categoryEntity = categoryData.get(0);
        CategoryEntity response = stampLogic.getCategory(entity.getId(), categoryEntity.getId());

        Assert.assertEquals(categoryEntity.getId(), response.getId());
        Assert.assertEquals(categoryEntity.getName(), response.getName());
    }

    /**
     * Prueba para obtener una colección de instancias de Category asociadas a una instancia Stamp
     *
     * @generated
     */
    @Test
    public void listCategoryTest() {
        List<CategoryEntity> list = stampLogic.listCategory(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un Category existente a un Stamp
     *
     * @generated
     */
    @Test
    public void addCategoryTest() {
        StampEntity entity = data.get(0);
        CategoryEntity categoryEntity = categoryData.get(1);
        CategoryEntity response = stampLogic.addCategory(entity.getId(), categoryEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(categoryEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Category asociadas a una instancia de Stamp
     *
     * @generated
     */
    @Test
    public void replaceCategoryTest() {
        StampEntity entity = data.get(0);
        List<CategoryEntity> list = categoryData.subList(1, 3);
        stampLogic.replaceCategory(entity.getId(), list);

        entity = stampLogic.getStamp(entity.getId());
        Assert.assertFalse(entity.getCategory().contains(categoryData.get(0)));
        Assert.assertTrue(entity.getCategory().contains(categoryData.get(1)));
        Assert.assertTrue(entity.getCategory().contains(categoryData.get(2)));
    }

    /**
     * Prueba para desasociar un Category existente de un Stamp existente
     *
     * @generated
     */
    @Test
    public void removeCategoryTest() {
        stampLogic.removeCategory(data.get(0).getId(), categoryData.get(0).getId());
        CategoryEntity response = stampLogic.getCategory(data.get(0).getId(), categoryData.get(0).getId());
        Assert.assertNull(response);
    }
}

