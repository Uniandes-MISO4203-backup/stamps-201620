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

import co.edu.uniandes.csw.stamps.entities.TShirtEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class TShirtDTO implements Serializable{

    private Long id;
    private String name;
    private String size;
    private String color;
    private Long price;
    private String material;

    /**
     * @generated
     */
    public TShirtDTO() {
    }

    /**
     * Crea un objeto TShirtMinimumDTO a partir de un objeto TShirtEntity.
     *
     * @param entity Entidad TShirtEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public TShirtDTO(TShirtEntity entity) {
	if (entity!=null){
            this.id=entity.getId();
            this.name=entity.getName();
            this.size=entity.getSize();
            this.color=entity.getColor();
            this.price=entity.getPrice();
            this.material=entity.getMaterial();
       }
    }

    /**
     * Convierte un objeto TShirtMinimumDTO a TShirtEntity.
     *
     * @return Nueva objeto TShirtEntity.
     * @generated
     */
    public TShirtEntity toEntity() {
        TShirtEntity entity = new TShirtEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setSize(this.getSize());
        entity.setColor(this.getColor());
        entity.setPrice(this.getPrice());
        entity.setMaterial(this.getMaterial());
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
     * Obtiene el atributo size.
     *
     * @return atributo size.
     * @generated
     */
    public String getSize() {
        return size;
    }

    /**
     * Establece el valor del atributo size.
     *
     * @param size nuevo valor del atributo
     * @generated
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Obtiene el atributo color.
     *
     * @return atributo color.
     * @generated
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el valor del atributo color.
     *
     * @param color nuevo valor del atributo
     * @generated
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene el atributo price.
     *
     * @return atributo price.
     * @generated
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Establece el valor del atributo price.
     *
     * @param price nuevo valor del atributo
     * @generated
     */
    public void setPrice(Long price) {
        this.price = price;
    }

            /**
     * Obtiene el atributo material.
     *
     * @return atributo material.
     * @generated
     */
    public String getMaterial(){
        return material;
    }

    /**
     * Establece el valor del atributo material.
     *
     * @param material nuevo valor del atributo
     * @generated
     */
    public void setMaterial(String material){
        this.material = material;
    }

}
