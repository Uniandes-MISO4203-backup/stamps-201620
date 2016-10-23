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
package co.edu.uniandes.csw.stamps.dtos.minimum;

import co.edu.uniandes.csw.stamps.entities.ItemEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class ItemDTO implements Serializable{

    private Long id;
    private String name;
    private Long qty;
    private Integer status;

    /**
     * @generated
     */
    public ItemDTO() {
    }

    /**
     * Crea un objeto ItemMinimumDTO a partir de un objeto ItemEntity.
     *
     * @param entity Entidad ItemEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ItemDTO(ItemEntity entity) {
	if (entity!=null){
            this.id=entity.getId();
            this.name=entity.getName();
            this.qty=entity.getQty();
            this.status=entity.getStatus();
       }
    }

    /**
     * Convierte un objeto ItemMinimumDTO a ItemEntity.
     *
     * @return Nueva objeto ItemEntity.
     * @generated
     */
    public ItemEntity toEntity() {
        ItemEntity entity = new ItemEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setQty(this.getQty());
        entity.setStatus(this.getStatus());
    return entity;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el atributo qty.
     *
     * @return atributo qty.
     * @generated
     */
    public Long getQty() {
        return qty;
    }

    /**
     * Establece el valor del atributo qty.
     *
     * @param qty nuevo valor del atributo
     * @generated
     */
    public void setQty(Long qty) {
        this.qty = qty;
    }
    
        /**
     * Obtiene el atributo status.
     *
     * @return atributo status.
     * @generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Establece el valor del atributo status.
     *
     * @param status nuevo valor del atributo
     * @generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}
