/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.stamps.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import java.util.Date;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;

/**
 *
 * @author ga.chica10
 */
@Entity
public class StampCommentaryEntity extends BaseEntity implements Serializable {
    
    private String commentary;
    private short qualification;
    private Date DateCommentary;
    
    @PodamExclude
    @ManyToOne
    private StampEntity stamp;

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
        return DateCommentary;
    }

    public void setDateCommentary(Date DateCommentary) {
        this.DateCommentary = DateCommentary;
    }
   
    public StampEntity getStamp() {
        return stamp;
    }

    public void setStamp(StampEntity stamp) {
        this.stamp = stamp;
    }
}

