/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.stamps.ejbs;

import co.edu.uniandes.csw.stamps.api.IStampCommentaryLogic;
import co.edu.uniandes.csw.stamps.entities.StampCommentaryEntity;
import co.edu.uniandes.csw.stamps.persistence.StampCommentaryPersistence;
import co.edu.uniandes.csw.stamps.api.IStampLogic;
import co.edu.uniandes.csw.stamps.entities.StampEntity;
import co.edu.uniandes.csw.stamps.persistence.StampPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author ga.chica10
 */
@Stateless
public class StampCommentaryLogic implements IStampCommentaryLogic {
    
    @Inject private StampCommentaryPersistence persistence;
    
    @Inject 
    private IStampLogic stampLogic;
    /**
     * Obtiene el número de registros de StampCommentary.
     *
     * @return Número de registros de StampCommentary.
     * @generated
     */
    @Override
    public int countStampCommentaries(){
        return persistence.count();
    }
    /**
     * Obtiene la lista de los registros de StampCommentary que pertenecen a un Stamp.
     *
     * @param stampId id del Stamp el cual es padre de los StampCommentary.
     * @return Colección de objetos de StampCommentaryEntity.
     * @generated
     */
    @Override
    public List<StampCommentaryEntity> getStampCommentaries(){
        return persistence.findAll();
    }
    /**
     * Obtiene la lista de los registros de StampCommentary que pertenecen a un Stamp.
     *
     * @param stampId id del Stamp el cual es padre de los StampCommentary.
     * @return Colección de objetos de StampCommentaryEntity.
     * @generated
     */
    @Override
    public List<StampCommentaryEntity> getStampCommentaries(Long stampId){
        StampEntity stamp = stampLogic.getStamp(stampId);
        return stamp.getStampCommentaries();
    }
     /**
     * Obtiene la lista de los registros de StampCommentary que pertenecen a un Stamp indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param stampId id del Stamp el cual es padre de los StampCommentary.
     * @return Colección de objetos de StampCommentaryEntity.
     * @generated
     */
    
    
    @Override
    public List<StampCommentaryEntity> getStampCommentaries(Integer page, Integer maxRecords, Long stampId){
        return persistence.findAll(page, maxRecords, stampId);
    }
    /**
     * Obtiene los datos de una instancia de StampCommentary a partir de su ID.
     *
     * @pre La existencia del elemento padre Stamp se debe garantizar.
     * @param stampCommentaryId Identificador del StampCommentary a consultar
     * @return Instancia de StampCommentaryEntity con los datos del StampCommentary consultado.
     * @generated
     */
    @Override
    public StampCommentaryEntity getStampCommentary(Long stampCommentaryId){       
        try {
            return persistence.find(stampCommentaryId);
        } catch (NoResultException e){
            throw new IllegalArgumentException("La estampa no tiene comentarios");
        }
    }
     /**
     * Se encarga de crear un StampCommentary en la base de datos.
     *
     * @param entity Objeto de StampCommentaryEntity con los datos nuevos
     * @param stampId id del Stamp el cual sera padre del nuevo StampCommentary.
     * @return Objeto de StampCommentaryEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public StampCommentaryEntity createStampCommentary(Long stampId, StampCommentaryEntity entity){
        StampEntity stamp = stampLogic.getStamp(stampId);
        entity.setStamp(stamp);
        entity = persistence.create(entity);
        return entity;
    }
    /**
     * Actualiza la información de una instancia de StampCommentary.
     *
     * @param entity Instancia de StampCommentaryEntity con los nuevos datos.
     * @param stampId id del Stamp el cual sera padre del StampCommentary actualizado.
     * @return Instancia de StampCommentaryEntity con los datos actualizados.
     * @generated
     */
    @Override
    public StampCommentaryEntity updateStampCommentary(Long stampId, StampCommentaryEntity entity){
        StampEntity stamp = stampLogic.getStamp(stampId);
        entity.setStamp(stamp);
        return persistence.update(entity);
    }
    /**
     * Elimina una instancia de StampCommentary de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param stampCommentaryId id del Stamp el cual es padre del StampCommentary.
     * @generated
     */
    @Override
    public void deleteStampCommentary(Long stampCommentaryId){
        StampCommentaryEntity old = getStampCommentary(stampCommentaryId);
        persistence.delete(old.getId());
    }
}

