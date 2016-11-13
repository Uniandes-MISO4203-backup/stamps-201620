/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import co.edu.uniandes.csw.stamps.api.IStampCommentaryLogic;
import co.edu.uniandes.csw.stamps.dtos.detail.StampCommentaryDetailDTO;
import co.edu.uniandes.csw.stamps.entities.StampCommentaryEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author ga.chica10
 */
@Path("/stampCommentaries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StampCommentaryResource {
    
    @Inject private IStampCommentaryLogic stampCommentaryLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("stampId") private Long stampId;
    /**
     * Convierte una lista de StampCommentaryEntity a una lista de StampCommentaryDetailDTO
     *
     * @param entityList Lista de StampCommentaryEntity a convertir
     * @return Lista de StampCommentaryDetailDTO convertida
     * @generated
     */
    private List<StampCommentaryDetailDTO> listEntity2DTO(List<StampCommentaryEntity> entityList) {
        List<StampCommentaryDetailDTO> list = new ArrayList<>();
        for (StampCommentaryEntity entity : entityList){
            list.add(new StampCommentaryDetailDTO(entity));
        }
        return list;
    }
    /**
     * Obtiene la lista de los registros de StampCommentary
     *
     * @return Colección de objetos de StampCommentaryDetailDTO
     * @generated
     */
    @GET
    public List<StampCommentaryDetailDTO> getStampCommentaries() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", stampCommentaryLogic.countStampCommentaries());
            return listEntity2DTO(stampCommentaryLogic.getStampCommentaries(page, maxRecords, stampId));
        }
        return listEntity2DTO(stampCommentaryLogic.getStampCommentaries(stampId));
    }
    /**
     * Obtiene los datos de una instancia de StampCommentary a partir de su ID
     *
     * @param stampCommentaryId Identificador de la instancia a consultar
     * @return Instancia de StampCommentaryDetailDTO con los datos del StampCommentary consultado
     * @generated
     */
    @GET
    @Path("{stampCommentaryId: \\d+}")
    public StampCommentaryDetailDTO getStampCommentary(@PathParam("stampCommentaryId") Long stampCommentaryId) {
        StampCommentaryEntity entity = stampCommentaryLogic.getStampCommentary(stampCommentaryId);
        if (entity.getStamp() == null && !stampId.equals(entity.getStamp().getId())){
            throw new WebApplicationException(404);
        }
        return new StampCommentaryDetailDTO(entity);
    }
    /**
     * Asocia un StampCommentary existente a un Stamp
     *
     * @param dto Objeto de StampCommentaryDetailDTO con los datos nuevos
     * @return Objeto de StampCommentaryDetailDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public StampCommentaryDetailDTO createStampCommentary(StampCommentaryDetailDTO dto){
        return new StampCommentaryDetailDTO(stampCommentaryLogic.createStampCommentary(stampId, dto.toEntity()));
    }
    /**
     * Actualiza la información de una instancia de StampCommentary.
     *
     * @param stampCommentaryId Identificador de la instancia de StampCommentary a modificar
     * @param dto Instancia de StampCommentaryDetailDTO con los nuevos datos.
     * @return Instancia de StampCommentaryDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{stampCommentaryId: \\d+}")
    public StampCommentaryDetailDTO updateStampCommentary(@PathParam("stampCommentaryId") Long stampCommentaryId, StampCommentaryDetailDTO dto){
        StampCommentaryEntity entity = dto.toEntity();
        entity.setId(stampCommentaryId);
        StampCommentaryEntity oldEntity = stampCommentaryLogic.getStampCommentary(stampCommentaryId);
        entity.setStamp(oldEntity.getStamp());
        return new StampCommentaryDetailDTO(stampCommentaryLogic.updateStampCommentary(stampId, entity));
    }
    /**
     * Elimina una instancia de StampCommentary de la base de datos.
     *
     * @param stampCommentaryId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{stampCommentaryId: \\d+}")
    public void deleteStampCommentary(@PathParam("stampCommentaryId") Long stampCommentaryId) {
        stampCommentaryLogic.deleteStampCommentary(stampCommentaryId);
    }
    
    public void existsStampCommentary(Long stampCommentaryId){
        StampCommentaryDetailDTO stampCommentary = getStampCommentary(stampCommentaryId);
        if (stampCommentary == null) {
            throw new WebApplicationException(404);
        }
    }
}