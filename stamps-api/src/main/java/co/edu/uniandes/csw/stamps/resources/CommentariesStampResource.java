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
import co.edu.uniandes.csw.stamps.api.IStampLogic;
import co.edu.uniandes.csw.stamps.api.IStampCommentaryLogic;
import co.edu.uniandes.csw.stamps.dtos.detail.StampCommentaryDetailDTO;
import co.edu.uniandes.csw.stamps.entities.StampCommentaryEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author ga.chica10
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentariesStampResource {
    
    @Inject private IStampLogic stampLogic;
    @Context private HttpServletResponse response;
    /**
     * Convierte una lista de stampCommentaryEntity a una lista de stampCommentaryDetailDTO.
     *
     * @param entityList Lista de stampCommentaryEntity a convertir.
     * @return Lista de stampCommentaryDetailDTO convertida.
     * @generated
     */
    private List<StampCommentaryDetailDTO> commentariesStampListEntity2DTO(List<StampCommentaryEntity> entityList) {
        List<StampCommentaryDetailDTO> list = new ArrayList<>();
        for (StampCommentaryEntity entity : entityList){
            list.add(new StampCommentaryDetailDTO(entity));
        }
        return list;
    }
    /**
     * Convierte una lista de stampCommentaryDetailDTO a una lista de stampCommentaryEntity.
     *
     * @param dtos Lista de stampCommentaryDetailDTO a convertir.
     * @return Lista de stampCommentaryEntity convertida.
     * @generated
     */
    private List<StampCommentaryEntity> commentariesStampListDTO2Entity(List<StampCommentaryDetailDTO> dtos) {
        List<StampCommentaryEntity> list = new ArrayList<>();
        for (StampCommentaryDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    /**
     * Obtiene una colección de instancias de stampCommentaryDetailDTO asociadas a una
     * instancia de Stap
     *
     * @param stampId Identificador de la instancia de Stamp
     * @return Colección de instancias de stampCommentaryDetailDTO asociadas a la instancia de Stamp
     * @generated
     */
    @GET
    public List<StampCommentaryDetailDTO> listCommentariesStamp(@PathParam("stampId") Long stampId) {
        return commentariesStampListEntity2DTO(stampLogic.listStampCommentaries(stampId));
    }
    /**
     * Obtiene una instancia de stampCommentary asociada a una instancia de Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param stampCommentaryId Identificador de la instancia de StampCommentary
     * @generated
     */
    @GET
    @Path("{stampCommentaryId: \\d+}")
    public StampCommentaryDetailDTO getCommentariesStamp(@PathParam("stampId") Long stampId, @PathParam("stampCommentaryId") Long stampCommentaryId) {
        return new StampCommentaryDetailDTO(stampLogic.getStampCommentaries(stampId, stampCommentaryId));
    }
    /**
     * Asocia un stampCommentary existente a un Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param stampCommentaryId Identificador de la instancia de StampCommentary
     * @return Instancia de StampCommentaryDetailDTO que fue asociada a Stamp
     * @generated
     */
    @POST
    @Path("{stampCommentaryId: \\d+}")
    public StampCommentaryDetailDTO addCommentariesStamp(@PathParam("stampId") Long stampId, @PathParam("stampCommentaryId") Long stampCommentaryId) {
        return new StampCommentaryDetailDTO(stampLogic.addStampCommentaries(stampId, stampCommentaryId));
    }
    /**
     * Remplaza las instancias de StampCommentary asociadas a una instancia de Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param stampCommentaries Colección de instancias de StampCommentaryDTO a asociar a instancia de Stamp
     * @return Nueva colección de StampCommentaryDTO asociada a la instancia de Stamp
     * @generated
     */
    @PUT
    public List<StampCommentaryDetailDTO> replaceCommentariesStamp(@PathParam("stampId") Long stampId, List<StampCommentaryDetailDTO> stampCommentaries) {
        return commentariesStampListEntity2DTO(stampLogic.replaceStampCommentaries(stampId, commentariesStampListDTO2Entity(stampCommentaries)));
    }
    /**
     * Desasocia un stampCommentary existente de un Stamp existente
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param stampCommentaryId Identificador de la instancia de StampCommentary
     * @generated
     */
    @DELETE
    @Path("{stampCommentaryId: \\d+}")
    public void removeCommentariesStamp(@PathParam("stampId") Long stampId, @PathParam("stampCommentaryId") Long stampCommentaryId) {
        stampLogic.removeStampCommentaries(stampId, stampCommentaryId);
    }
}
