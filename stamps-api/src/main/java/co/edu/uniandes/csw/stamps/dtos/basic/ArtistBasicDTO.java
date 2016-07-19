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
import co.edu.uniandes.csw.stamps.entities.ArtistEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement
public class ArtistBasicDTO extends ArtistMinimumDTO{



    /**
     * @generated
     */
    public ArtistBasicDTO() {
        super();
    }

    /**
     * Crea un objeto ArtistBasicDTO a partir de un objeto ArtistEntity incluyendo los atributos de ArtistMinimumDTO.
     *
     * @param entity Entidad ArtistEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ArtistBasicDTO(ArtistEntity entity) {
        super(entity);
        
    }

    /**
     * Convierte un objeto ArtistBasicDTO a ArtistEntity incluyendo los atributos de ArtistMinimumDTO.
     *
     * @return Nueva objeto ArtistEntity.
     * @generated
     */
    @Override
    public ArtistEntity toEntity() {
        ArtistEntity entity = super.toEntity();
        return entity;
    }

}
