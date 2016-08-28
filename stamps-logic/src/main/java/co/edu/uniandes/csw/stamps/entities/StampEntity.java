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
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@Entity
public class StampEntity extends BaseEntity implements Serializable {

    private Long id;
    private String name;
    private String image;
    private Long price;
    private boolean availableForSale;
    private short qualification;
    private String description;

    @PodamExclude
    @ManyToOne
    private ArtistEntity artist;

    @PodamExclude
    @ManyToMany
    private List<CategoryEntity> category = new ArrayList<>(); 
    
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
     * Obtiene el atributo image.
     *
     * @return atributo image.
     * @generated
     */
    public String getImage(){
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image nuevo valor del atributo
     * @generated
     */
    public void setImage(String image){
        this.image = image;
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
     * Obtiene el atributo artist.
     *
     * @return atributo artist.
     * @generated
     */
    public ArtistEntity getArtist() {
        return artist;
    }

    /**
     * Establece el valor del atributo artist.
     *
     * @param artist nuevo valor del atributo
     * @generated
     */
    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    /**
     * Obtiene la colección de category.
     *
     * @return colección category.
     * @generated
     */
    public List<CategoryEntity> getCategory() {
        return category;
    }

    /**
     * Establece el valor de la colección de category.
     *
     * @param category nuevo valor de la colección.
     * @generated
     */
    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }
    
       /**
     *Obtiene el valor de available 
     * @return
     */
    public boolean isAvailableForSale() {
        return availableForSale;
    }
    /**
     * Establece el valor de available 
     * 
     * @param availableForSale ForSale
    */
    public void setAvailableForSale(boolean availableForSale) {
        this.availableForSale = availableForSale;
    }
    /**
     * Obtiene el atributo qualification.
     *
     * @return atributo qualification.
     * @generated
     */
    public short getQualification(){
        return qualification;
    }

    /**
     * Establece el valor del atributo qualification.
     *
     * @param qualification nuevo valor del atributo
     * @generated
     */
    public void setQualification(short qualification){
        this.qualification = qualification;
    }
    /**
     *Obtiene el valor de description 
     * @return
     */
    public String getDescription(){
        return this.description;
    }
    /**
     * Establece el valor de description 
     * 
     * @param description ForSale
    */
    public void setDescription(String description) {
        this.description = description;
    }
}
