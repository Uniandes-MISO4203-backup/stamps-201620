/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.stamps.dtos.minimum;

import co.edu.uniandes.csw.stamps.entities.StampCommentaryEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ga.chica10
 */
@XmlRootElement
public class StampCommentaryDTO implements Serializable{
    
    private Long id;
    private String name;
    private String commentary;
    private short qualification;
    private Date dateCommentary;
    
    public StampCommentaryDTO(){
    }
    /**
     * Crea un objeto StampCommentaryMinimumDTO a partir de un objeto StampCommentaryEntity.
     *
     * @param entity Entidad StampCommentaryEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */   
    public StampCommentaryDTO(StampCommentaryEntity entity) {
        if (entity!=null){
            this.id = entity.getId();
            this.name = entity.getName();
            this.commentary = entity.getCommentary();
            this.qualification = entity.getQualification();
            this.dateCommentary = entity.getDateCommentary();
        }
    }   
    /**
     * Convierte un objeto StampCommentaryMinimumDTO a StampCommentaryEntity.
     *
     * @return Nueva objeto StampCommentaryEntity.
     * @generated
     */   
    public StampCommentaryEntity toEntity() {
        StampCommentaryEntity entity = new StampCommentaryEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setCommentary(this.getCommentary());
        entity.setQualification(this.getQualification());
        entity.setDateCommentary(this.getDateCommentary());
        return entity;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public short getQualification() {
        return qualification;
    }

    public void setQualification(short qualification) {
        this.qualification = qualification;
    }

    public Date getDateCommentary() {
        return dateCommentary;
    }

    public void setDateCommentary(Date dateCommentary) {
        this.dateCommentary = dateCommentary;
    }
}
