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

/**
 * @generated
 */
@Entity
public class ClientEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> wishList = new ArrayList<>();

    /**
     * Obtiene la colección de wishList.
     *
     * @return colección wishList.
     * @generated
     */
    public List<ItemEntity> getWishList() {
        return wishList;
    }

    /**
     * Establece el valor de la colección de wishList.
     *
     * @param wishList nuevo valor de la colección.
     * @generated
     */
    public void setWishList(List<ItemEntity> wishlist) {
        this.wishList = wishlist;
    }
    
    
    @PodamExclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> cartList = new ArrayList<>();

    /**
     * Obtiene la colección de cartList.
     *
     * @return colección cartList.
     * @generated
     */
    public List<ItemEntity> getCartList() {
        return cartList;
    }

    /**
     * Establece el valor de la colección de cartList.
     *
     * @param cartlist nuevo valor de la colección.
     * @generated
     */
    public void setCartList(List<ItemEntity> cartlist) {
        this.cartList = cartlist;
    }
    
    
    
    @PodamExclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> acquiredList = new ArrayList<>();

    /**
     * Obtiene la colección de acquiredList.
     *
     * @return colección acquiredList.
     * @generated
     */
    public List<ItemEntity> getAcquiredList() {
        return acquiredList;
    }

    /**
     * Establece el valor de la colección de acquiredList.
     *
     * @param acquiredist nuevo valor de la colección.
     * @generated
     */
    public void setAcquiredList(List<ItemEntity> acquiredlist) {
        this.acquiredList = acquiredlist;
    }
    
}
