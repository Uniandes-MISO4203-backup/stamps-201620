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
import co.edu.uniandes.csw.stamps.api.IStampLogic;
import co.edu.uniandes.csw.stamps.dtos.detail.StampDetailDTO;
import co.edu.uniandes.csw.stamps.entities.StampEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StampResource {

    @Inject private IStampLogic stampLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("artistId")
    private Long artistId;
   
    /**
     * Convierte una lista de StampEntity a una lista de StampBasicDTO
     *
     * @param entityList Lista de StampEntity a convertir
     * @return Lista de StampBasicDTO convertida
     * @generated
     */
    private List<StampDetailDTO> listEntity2DTO(List<StampEntity> entityList){
        List<StampDetailDTO> list = new ArrayList<>();
        for (StampEntity entity : entityList) {
            list.add(new StampDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Stamp asociados a un Artist
     *
     * @return Colección de objetos de StampBasicDTO
     * @generated
     */
    @GET
    public List<StampDetailDTO> getStamps() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", stampLogic.countStamps());
            return listEntity2DTO(stampLogic.getStamps(page, maxRecords, artistId));
        }
        return listEntity2DTO(stampLogic.getStamps(artistId));
    }

    /**
     * Obtiene los datos de una instancia de Stamp a partir de su ID asociado a un Artist
     *
     * @param stampId Identificador de la instancia a consultar
     * @return Instancia de StampBasicDTO con los datos del Stamp consultado
     * @generated
     */
    @GET
    @Path("{stampId: \\d+}")
    public StampDetailDTO getStamp(@PathParam("stampId") Long stampId) {
        StampEntity entity = stampLogic.getStamp(stampId);
        if (entity.getArtist() != null && !artistId.equals(entity.getArtist().getId())) {
            throw new WebApplicationException(404);
        }
        return new StampDetailDTO(entity);
    }

    /**
     * Asocia un Stamp existente a un Artist
     *
     * @param dto Objeto de StampBasicDTO con los datos nuevos
     * @return Objeto de StampBasicDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public StampDetailDTO createStamp(StampDetailDTO dto) {
        return new StampDetailDTO(stampLogic.createStamp(artistId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Stamp.
     *
     * @param stampId Identificador de la instancia de Stamp a modificar
     * @param dto Instancia de StampBasicDTO con los nuevos datos.
     * @return Instancia de StampBasicDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{stampId: \\d+}")
    public StampDetailDTO updateStamp(@PathParam("stampId") Long stampId, StampDetailDTO dto) {
        StampEntity entity = dto.toEntity();
        entity.setId(stampId);
        StampEntity oldEntity = stampLogic.getStamp(stampId);
        entity.setCategory(oldEntity.getCategory());
        return new StampDetailDTO(stampLogic.updateStamp(artistId, entity));
    }

    /**
     * Elimina una instancia de Stamp de la base de datos.
     *
     * @param stampId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{stampId: \\d+}")
    public void deleteStamp(@PathParam("stampId") Long stampId) {
        stampLogic.deleteStamp(stampId);
    }
    public void existsStamp(Long stampId){
        StampDetailDTO stamp = getStamp(stampId);
        if (stamp== null) {
            throw new WebApplicationException(404);
        }
    }
    
    @Path("{stampId: \\d+}/category")
    public Class<StampCategoryResource> getStampCategoryResource(@PathParam("stampId") Long stampId){
        existsStamp(stampId);
        return StampCategoryResource.class;
    }
    
    
    
}
