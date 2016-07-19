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
package co.edu.uniandes.csw.stamps.ejbs;

import co.edu.uniandes.csw.stamps.api.ITShirtLogic;
import co.edu.uniandes.csw.stamps.entities.TShirtEntity;
import co.edu.uniandes.csw.stamps.persistence.TShirtPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class TShirtLogic implements ITShirtLogic {

    @Inject private TShirtPersistence persistence;


    /**
     * Obtiene el número de registros de TShirt.
     *
     * @return Número de registros de TShirt.
     * @generated
     */
    public int countTShirts() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de TShirt.
     *
     * @return Colección de objetos de TShirtEntity.
     * @generated
     */
    @Override
    public List<TShirtEntity> getTShirts() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de TShirt indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de TShirtEntity.
     * @generated
     */
    @Override
    public List<TShirtEntity> getTShirts(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de TShirt a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de TShirtEntity con los datos del TShirt consultado.
     * @generated
     */
    public TShirtEntity getTShirt(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un TShirt en la base de datos.
     *
     * @param entity Objeto de TShirtEntity con los datos nuevos
     * @return Objeto de TShirtEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public TShirtEntity createTShirt(TShirtEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de TShirt.
     *
     * @param entity Instancia de TShirtEntity con los nuevos datos.
     * @return Instancia de TShirtEntity con los datos actualizados.
     * @generated
     */
    @Override
    public TShirtEntity updateTShirt(TShirtEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de TShirt de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteTShirt(Long id) {
        persistence.delete(id);
    }
  
}
