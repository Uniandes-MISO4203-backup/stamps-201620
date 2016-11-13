/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.stamps.dtos.detail;

import co.edu.uniandes.csw.stamps.dtos.minimum.*;
import co.edu.uniandes.csw.stamps.entities.StampCommentaryEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author ga.chica10
 */
@XmlRootElement
public class StampCommentaryDetailDTO extends StampCommentaryDTO {
    
    @PodamExclude
    private StampDTO stamp;
    
    public StampCommentaryDetailDTO(){
        super();
    }
    /**
     * Crea un objeto StampCommentaryDetailDTO a partir de un objeto StampCommentaryEntity incluyendo los atributos de StampDTO.
     *
     * @param entity Entidad StampCommentaryEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public StampCommentaryDetailDTO(StampCommentaryEntity entity) {
        super(entity);
        if (entity.getStamp()!=null){
            this.stamp = new StampDTO(entity.getStamp());
        }
    }
    /**
     * Convierte un objeto StampCommentaryDetailDTO a StampCommentaryEntity incluyendo los atributos de StampDTO.
     *
     * @return Nuevo objeto StampCommentaryEntity.
     * @generated
     */
    @Override
    public StampCommentaryEntity toEntity (){
        StampCommentaryEntity entity = super.toEntity();
        if (this.getStamp()!=null){
            entity.setStamp(this.getStamp().toEntity());
        }
        return entity;
    }
    /**
     * Obtiene el atributo stamp.
     *
     * @return atributo stamp.
     * @generated
     */
    public StampDTO getStamp() {
        return stamp;
    }
    /**
     * Establece el valor del atributo stamp.
     *
     * @param stamp nuevo valor del atributo
     * @generated
     */
    public void setStamp(StampDTO stamp) {
        this.stamp = stamp;
    }
}

