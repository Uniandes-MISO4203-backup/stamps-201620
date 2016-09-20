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
import javax.persistence.ManyToOne;

/**
 * @generated
 */
@Entity
public class ItemEntity extends BaseEntity implements Serializable {

    private Long qty;
    
    private int status;

    @PodamExclude
    @ManyToOne
    private ClientEntity client;

    @PodamExclude
    @ManyToOne
    private TShirtEntity tShirt;

    @PodamExclude
    @ManyToOne
    private StampEntity stamp;

    
    
    /**
     * Obtiene el atributo status.
     *
     * @return atributo status.
     * @generated
     */
    public int getStatus(){
        return status;
    }

    /**
     * Establece el valor del atributo status.
     *
     * @param status nuevo valor del atributo
     * @generated
     */
    public void setStatus(int status){
        this.status = status;
    }
    
    /**
     * Obtiene el atributo qty.
     *
     * @return atributo qty.
     * @generated
     */
    public Long getQty(){
        return qty;
    }

    /**
     * Establece el valor del atributo qty.
     *
     * @param qty nuevo valor del atributo
     * @generated
     */
    public void setQty(Long qty){
        this.qty = qty;
    }

    /**
     * Obtiene el atributo client.
     *
     * @return atributo client.
     * @generated
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * Establece el valor del atributo client.
     *
     * @param client nuevo valor del atributo
     * @generated
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    /**
     * Obtiene el atributo tShirt.
     *
     * @return atributo tShirt.
     * @generated
     */
    public TShirtEntity getTShirt() {
        return tShirt;
    }

    /**
     * Establece el valor del atributo tShirt.
     *
     * @param tShirt nuevo valor del atributo
     * @generated
     */
    public void setTShirt(TShirtEntity tshirt) {
        this.tShirt = tshirt;
    }

    /**
     * Obtiene el atributo stamp.
     *
     * @return atributo stamp.
     * @generated
     */
    public StampEntity getStamp() {
        return stamp;
    }

    /**
     * Establece el valor del atributo stamp.
     *
     * @param stamp nuevo valor del atributo
     * @generated
     */
    public void setStamp(StampEntity stamp) {
        this.stamp = stamp;
    }
}
