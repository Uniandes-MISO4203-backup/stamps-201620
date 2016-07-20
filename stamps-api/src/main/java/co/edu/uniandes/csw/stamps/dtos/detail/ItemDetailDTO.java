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
package co.edu.uniandes.csw.stamps.dtos.detail;

import co.edu.uniandes.csw.stamps.dtos.minimum.*;
import co.edu.uniandes.csw.stamps.entities.ItemEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class ItemDetailDTO extends ItemDTO{


    @PodamExclude
    private ClientDTO client;
    @PodamExclude
    private TShirtDTO tShirt;
    @PodamExclude
    private StampDTO stamp;

    /**
     * @generated
     */
    public ItemDetailDTO() {
        super();
    }

    /**
     * Crea un objeto ItemBasicDTO a partir de un objeto ItemEntity incluyendo los atributos de ItemMinimumDTO.
     *
     * @param entity Entidad ItemEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ItemDetailDTO(ItemEntity entity) {
        super(entity);
        if (entity.getClient()!=null){
        this.client = new ClientDTO(entity.getClient());
        }
        if (entity.getTShirt()!=null){
        this.tShirt = new TShirtDTO(entity.getTShirt());
        }
        if (entity.getStamp()!=null){
        this.stamp = new StampDTO(entity.getStamp());
        }
        
    }

    /**
     * Convierte un objeto ItemBasicDTO a ItemEntity incluyendo los atributos de ItemMinimumDTO.
     *
     * @return Nueva objeto ItemEntity.
     * @generated
     */
    @Override
    public ItemEntity toEntity() {
        ItemEntity entity = super.toEntity();
        if (this.getClient()!=null){
        entity.setClient(this.getClient().toEntity());
        }
        if (this.getTShirt()!=null){
        entity.setTShirt(this.getTShirt().toEntity());
        }
        if (this.getStamp()!=null){
        entity.setStamp(this.getStamp().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo client.
     *
     * @return atributo client.
     * @generated
     */
    public ClientDTO getClient() {
        return client;
    }

    /**
     * Establece el valor del atributo client.
     *
     * @param client nuevo valor del atributo
     * @generated
     */
    public void setClient(ClientDTO client) {
        this.client = client;
    }

    /**
     * Obtiene el atributo tShirt.
     *
     * @return atributo tShirt.
     * @generated
     */
    public TShirtDTO getTShirt() {
        return tShirt;
    }

    /**
     * Establece el valor del atributo tShirt.
     *
     * @param tShirt nuevo valor del atributo
     * @generated
     */
    public void setTShirt(TShirtDTO tshirt) {
        this.tShirt = tshirt;
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
