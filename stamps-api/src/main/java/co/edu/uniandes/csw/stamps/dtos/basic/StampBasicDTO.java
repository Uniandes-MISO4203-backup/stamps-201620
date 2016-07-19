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
package co.edu.uniandes.csw.stamps.dtos.basic;

import co.edu.uniandes.csw.stamps.dtos.minimum.*;
import co.edu.uniandes.csw.stamps.entities.StampEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class StampBasicDTO extends StampMinimumDTO{


    @PodamExclude
    private ArtistMinimumDTO artist;

    /**
     * @generated
     */
    public StampBasicDTO() {
        super();
    }

    /**
     * Crea un objeto StampBasicDTO a partir de un objeto StampEntity incluyendo los atributos de StampMinimumDTO.
     *
     * @param entity Entidad StampEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public StampBasicDTO(StampEntity entity) {
        super(entity);
        if (entity.getArtist()!=null){
        this.artist = new ArtistMinimumDTO(entity.getArtist());
        }
        
    }

    /**
     * Convierte un objeto StampBasicDTO a StampEntity incluyendo los atributos de StampMinimumDTO.
     *
     * @return Nueva objeto StampEntity.
     * @generated
     */
    @Override
    public StampEntity toEntity() {
        StampEntity entity = super.toEntity();
        if (this.getArtist()!=null){
        entity.setArtist(this.getArtist().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo artist.
     *
     * @return atributo artist.
     * @generated
     */
    public ArtistMinimumDTO getArtist() {
        return artist;
    }

    /**
     * Establece el valor del atributo artist.
     *
     * @param artist nuevo valor del atributo
     * @generated
     */
    public void setArtist(ArtistMinimumDTO artist) {
        this.artist = artist;
    }

}
