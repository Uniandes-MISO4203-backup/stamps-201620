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
package co.edu.uniandes.csw.stamps.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.stamps.api.ITShirtLogic;
import co.edu.uniandes.csw.stamps.dtos.basic.TShirtBasicDTO;
import co.edu.uniandes.csw.stamps.entities.TShirtEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * @generated
 */
@Path("/tShirts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TShirtResource {

    @Inject private ITShirtLogic tShirtLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de TShirtEntity a una lista de TShirtBasicDTO.
     *
     * @param entityList Lista de TShirtEntity a convertir.
     * @return Lista de TShirtBasicDTO convertida.
     * @generated
     */
    private List<TShirtBasicDTO> listEntity2DTO(List<TShirtEntity> entityList){
        List<TShirtBasicDTO> list = new ArrayList<>();
        for (TShirtEntity entity : entityList) {
            list.add(new TShirtBasicDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de TShirt
     *
     * @return Colección de objetos de TShirtBasicDTO
     * @generated
     */
    @GET
    public List<TShirtBasicDTO> getTShirts() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", tShirtLogic.countTShirts());
            return listEntity2DTO(tShirtLogic.getTShirts(page, maxRecords));
        }
        return listEntity2DTO(tShirtLogic.getTShirts());
    }

    /**
     * Obtiene los datos de una instancia de TShirt a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de TShirtBasicDTO con los datos del TShirt consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public TShirtBasicDTO getTShirt(@PathParam("id") Long id) {
        return new TShirtBasicDTO(tShirtLogic.getTShirt(id));
    }

    /**
     * Se encarga de crear un TShirt en la base de datos
     *
     * @param dto Objeto de TShirtBasicDTO con los datos nuevos
     * @return Objeto de TShirtBasicDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public TShirtBasicDTO createTShirt(TShirtBasicDTO dto) {
        return new TShirtBasicDTO(tShirtLogic.createTShirt(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de TShirt
     *
     * @param id Identificador de la instancia de TShirt a modificar
     * @param dto Instancia de TShirtBasicDTO con los nuevos datos
     * @return Instancia de TShirtBasicDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public TShirtBasicDTO updateTShirt(@PathParam("id") Long id, TShirtBasicDTO dto) {
        TShirtEntity entity = dto.toEntity();
        entity.setId(id);
        TShirtEntity oldEntity = tShirtLogic.getTShirt(id);
        return new TShirtBasicDTO(tShirtLogic.updateTShirt(entity));
    }

    /**
     * Elimina una instancia de TShirt de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTShirt(@PathParam("id") Long id) {
        tShirtLogic.deleteTShirt(id);
    }
    
}
