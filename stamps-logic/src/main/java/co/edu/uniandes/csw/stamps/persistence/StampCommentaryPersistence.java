/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.stamps.persistence;

import co.edu.uniandes.csw.stamps.entities.StampCommentaryEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.stamps.entities.ItemEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author ga.chica10
 */
@Stateless
public class StampCommentaryPersistence extends CrudPersistence<StampCommentaryEntity> {
    @PersistenceContext(unitName="StampsPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    protected Class<StampCommentaryEntity> getEntityClass() {
        return StampCommentaryEntity.class;
    }
    
    public StampCommentaryEntity find(Long stampid, Long stampcommentaryid){
        TypedQuery<StampCommentaryEntity> q = em.createQuery("select p from StampCommentaryEntity p where (p.stamp.id = :stampid) and (p.id = :stampcommentaryid)", StampCommentaryEntity.class);
        q.setParameter("stampid", stampid);
        q.setParameter("stampcommentaryid", stampcommentaryid);
        return q.getSingleResult();
    }
    
    public List<StampCommentaryEntity> findAll(Integer page, Integer maxRecords, Long stampid){
       TypedQuery<StampCommentaryEntity> q = em.createQuery("select p from StampCommentaryEntity p where (p.stamp.id = :stampid)", StampCommentaryEntity.class);
       q.setParameter("stampid", stampid);
       if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
       return q.getResultList();
    }
}
