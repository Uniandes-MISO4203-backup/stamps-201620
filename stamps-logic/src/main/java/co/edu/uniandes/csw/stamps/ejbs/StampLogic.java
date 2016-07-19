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
package co.edu.uniandes.csw.stamps.ejbs;

import co.edu.uniandes.csw.stamps.api.IStampLogic;
import co.edu.uniandes.csw.stamps.entities.StampEntity;
import co.edu.uniandes.csw.stamps.persistence.StampPersistence;
import co.edu.uniandes.csw.stamps.api.IArtistLogic;
import co.edu.uniandes.csw.stamps.entities.ArtistEntity;
import co.edu.uniandes.csw.stamps.entities.CategoryEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class StampLogic implements IStampLogic {

    @Inject private StampPersistence persistence;

    @Inject
    private IArtistLogic artistLogic;

    /**
     * Obtiene el número de registros de Stamp.
     *
     * @return Número de registros de Stamp.
     * @generated
     */
    public int countStamps() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Stamp que pertenecen a un Artist.
     *
     * @param artistid id del Artist el cual es padre de los Stamps.
     * @return Colección de objetos de StampEntity.
     * @generated
     */
    @Override
    public List<StampEntity> getStamps(Long artistid) {
        ArtistEntity artist = artistLogic.getArtist(artistid);
        return artist.getStamps();
    }

    /**
     * Obtiene la lista de los registros de Stamp que pertenecen a un Artist indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param artistid id del Artist el cual es padre de los Stamps.
     * @return Colección de objetos de StampEntity.
     * @generated
     */
    @Override
    public List<StampEntity> getStamps(Integer page, Integer maxRecords, Long artistid) {
        if (artistid!=null){
        return persistence.findAll(page, maxRecords, artistid);    
        }else{
        return persistence.findAll(page, maxRecords);    
        }
        
    }

    /**
     * Obtiene los datos de una instancia de Stamp a partir de su ID.
     *
     * @pre La existencia del elemento padre Artist se debe garantizar.
     * @param stampid) Identificador del Stamp a consultar
     * @return Instancia de StampEntity con los datos del Stamp consultado.
     * @generated
     */
    @Override
    public StampEntity getStamp(Long stampid) {
        try {
            return persistence.find(stampid);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El Stamp no existe");
        }
    }

    /**
     * Se encarga de crear un Stamp en la base de datos.
     *
     * @param entity Objeto de StampEntity con los datos nuevos
     * @param artistid id del Artist el cual sera padre del nuevo Stamp.
     * @return Objeto de StampEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public StampEntity createStamp(Long artistid, StampEntity entity) {
        ArtistEntity artist = artistLogic.getArtist(artistid);
        entity.setArtist(artist);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Stamp.
     *
     * @param entity Instancia de StampEntity con los nuevos datos.
     * @param artistid id del Artist el cual sera padre del Stamp actualizado.
     * @return Instancia de StampEntity con los datos actualizados.
     * @generated
     */
    @Override
    public StampEntity updateStamp(Long artistid, StampEntity entity) {
        ArtistEntity artist = artistLogic.getArtist(artistid);
        entity.setArtist(artist);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Stamp de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param artistid id del Artist el cual es padre del Stamp.
     * @generated
     */
    @Override
    public void deleteStamp(Long id) {
        StampEntity old = getStamp(id);
        persistence.delete(old.getId());
    }
  

    /**
     * Obtiene una colección de instancias de CategoryEntity asociadas a una
     * instancia de Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @return Colección de instancias de CategoryEntity asociadas a la instancia de Stamp
     * @generated
     */
    @Override
    public List<CategoryEntity> listCategory(Long stampId) {
        return getStamp(stampId).getCategory();
    }

    /**
     * Obtiene una instancia de CategoryEntity asociada a una instancia de Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param categoryId Identificador de la instancia de Category
     * @generated
     */
    @Override
    public CategoryEntity getCategory(Long stampId, Long categoryId) {
        List<CategoryEntity> list = getStamp(stampId).getCategory();
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        int index = list.indexOf(categoryEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Category existente a un Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param categoryId Identificador de la instancia de Category
     * @return Instancia de CategoryEntity que fue asociada a Stamp
     * @generated
     */
    @Override
    public CategoryEntity addCategory(Long stampId, Long categoryId) {
        StampEntity stampEntity = getStamp(stampId);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        stampEntity.getCategory().add(categoryEntity);
        return getCategory(stampId, categoryId);
    }

    /**
     * Remplaza las instancias de Category asociadas a una instancia de Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param list Colección de instancias de CategoryEntity a asociar a instancia de Stamp
     * @return Nueva colección de CategoryEntity asociada a la instancia de Stamp
     * @generated
     */
    @Override
    public List<CategoryEntity> replaceCategory(Long stampId, List<CategoryEntity> list) {
        StampEntity stampEntity = getStamp(stampId);
        stampEntity.setCategory(list);
        return stampEntity.getCategory();
    }

    /**
     * Desasocia un Category existente de un Stamp existente
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param categoryId Identificador de la instancia de Category
     * @generated
     */
    @Override
    public void removeCategory(Long stampId, Long categoryId) {
        StampEntity entity = getStamp(stampId);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        entity.getCategory().remove(categoryEntity);
    }
}
