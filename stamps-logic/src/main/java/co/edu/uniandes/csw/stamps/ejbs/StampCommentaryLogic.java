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
    public int countStampCommentaries(){
        return persistence.count();
    }
    /**
     * Obtiene la lista de los registros de StampCommentary que pertenecen a un Stamp.
     *
     * @param stampid id del Stamp el cual es padre de los StampCommentary.
     * @return Colección de objetos de StampCommentaryEntity.
     * @generated
     */
    @Override
    public List<StampCommentaryEntity> getStampCommentaries(Long stampid){
        StampEntity stamp = stampLogic.getStamp(stampid);
        return stamp.getCommentaries();
    }
     /**
     * Obtiene la lista de los registros de Contact que pertenecen a un Author indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param stampid id del Stamp el cual es padre de los StampCommentary.
     * @return Colección de objetos de StampCommentaryEntity.
     * @generated
     */
    @Override
    public List<StampCommentaryEntity> getStampCommentaries(Integer page, Integer maxRecords, Long stampid){
        return persistence.findAll(page, maxRecords, stampid);
    }
    /**
     * Obtiene los datos de una instancia de StampCommentary a partir de su ID.
     *
     * @pre La existencia del elemento padre Stamp se debe garantizar.
     * @param stampid Identificador del Stamp a consultar
     * @return Instancia de StampCommentaryEntity con los datos del StampCommentary consultado.
     * @generated
     */
    @Override
    public StampCommentaryEntity getStampCommentary(Long stampcommentaryid){       
        try {
            return persistence.find(stampcommentaryid);
        } catch (NoResultException e){
            throw new IllegalArgumentException("El Comentario de la estampa no existe");
        }
    }
     /**
     * Se encarga de crear un StampCommentary en la base de datos.
     *
     * @param entity Objeto de StampCommentaryEntity con los datos nuevos
     * @param stampid id del Stamp el cual sera padre del nuevo StampCommentary.
     * @return Objeto de StampCommentaryEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public StampCommentaryEntity createStampCommentary(Long stampid, StampCommentaryEntity entity){
        StampEntity stamp = stampLogic.getStamp(stampid);
        entity.setStamp(stamp);
        entity = persistence.create(entity);
        return entity;
    }
    /**
     * Actualiza la información de una instancia de StampCommentary.
     *
     * @param entity Instancia de StampCommentaryEntity con los nuevos datos.
     * @param stampid id del Stamp el cual sera padre del StampCommentary actualizado.
     * @return Instancia de StampCommentaryEntity con los datos actualizados.
     * @generated
     */
    @Override
    public StampCommentaryEntity updateStampCommentary(Long stampid, StampCommentaryEntity entity){
        StampEntity stamp = stampLogic.getStamp(stampid);
        entity.setStamp(stamp);
        return persistence.update(entity);
    }
    /**
     * Elimina una instancia de StampCommentary de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param stampid id del Stamp el cual es padre del StampCommentary.
     * @generated
     */
    @Override
    public void deleteStampCommentary(Long stampcommentaryid){
        StampCommentaryEntity old = getStampCommentary(stampcommentaryid);
        persistence.delete(old.getId());
    }
}
