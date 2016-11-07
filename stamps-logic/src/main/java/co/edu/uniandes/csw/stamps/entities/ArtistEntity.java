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
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @generated
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Artist.getHighlighted", query = "select u from ArtistEntity u ORDER BY u.qualification desc")})
public class ArtistEntity extends BaseEntity implements Serializable {
    private String address;
    private Long telephone;
    private Byte qualification;
    private Long popularity;
    private String artisticCareer;
    
    @PodamExclude
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StampEntity> stamps = new ArrayList<>();

    /**
     * Obtiene la colección de stamps.
     *
     * @return colección stamps.
     * @generated
     */
    public List<StampEntity> getStamps() {
        return stamps;
    }

    /**
     * Establece el valor de la colección de stamps.
     *
     * @param stamps nuevo valor de la colección.
     * @generated
     */
    public void setStamps(List<StampEntity> stamps) {
        this.stamps = stamps;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public Byte getQualification() {
        return qualification;
    }

    public void setQualification(Byte qualification) {
        this.qualification = qualification;
    }

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }
    
    /**
     *Obtiene el valor de artist career 
     * @return
     */
    public String getArtisticCareer(){
        return this.artisticCareer;
    }
    /**
     * Establece el valor de artist career  
     * 
     * @param artisticCareer nuevo valor del atributo
    */
    public void setArtisticCareer(String artisticCareer) {
        this.artisticCareer = artisticCareer;
    }
}
