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
import co.edu.uniandes.csw.stamps.entities.ClientEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement
public class ClientBasicDTO extends ClientMinimumDTO{



    /**
     * @generated
     */
    public ClientBasicDTO() {
        super();
    }

    /**
     * Crea un objeto ClientBasicDTO a partir de un objeto ClientEntity incluyendo los atributos de ClientMinimumDTO.
     *
     * @param entity Entidad ClientEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ClientBasicDTO(ClientEntity entity) {
        super(entity);
        
    }

    /**
     * Convierte un objeto ClientBasicDTO a ClientEntity incluyendo los atributos de ClientMinimumDTO.
     *
     * @return Nueva objeto ClientEntity.
     * @generated
     */
    @Override
    public ClientEntity toEntity() {
        ClientEntity entity = super.toEntity();
        return entity;
    }

}
