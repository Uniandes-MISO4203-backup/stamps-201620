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
package co.edu.uniandes.csw.stamps.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;

/**
 * @generated
 */
@Entity
public class TShirtEntity extends BaseEntity implements Serializable {

    private String size;

    private String color;

    private Long price;

    private String material;

    /**
     * Obtiene el atributo size.
     *
     * @return atributo size.
     * @generated
     */
    public String getSize(){
        return size;
    }

    /**
     * Establece el valor del atributo size.
     *
     * @param size nuevo valor del atributo
     * @generated
     */
    public void setSize(String size){
        this.size = size;
    }

    /**
     * Obtiene el atributo color.
     *
     * @return atributo color.
     * @generated
     */
    public String getColor(){
        return color;
    }

    /**
     * Establece el valor del atributo color.
     *
     * @param color nuevo valor del atributo
     * @generated
     */
    public void setColor(String color){
        this.color = color;
    }

    /**
     * Obtiene el atributo price.
     *
     * @return atributo price.
     * @generated
     */
    public Long getPrice(){
        return price;
    }

    /**
     * Establece el valor del atributo price.
     *
     * @param price nuevo valor del atributo
     * @generated
     */
    public void setPrice(Long price){
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
