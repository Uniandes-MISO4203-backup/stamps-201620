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
import co.edu.uniandes.csw.stamps.api.IArtistLogic;
import co.edu.uniandes.csw.stamps.api.IStampLogic;
import co.edu.uniandes.csw.stamps.dtos.detail.ArtistDetailDTO;
import co.edu.uniandes.csw.stamps.dtos.detail.StampDetailDTO;
import co.edu.uniandes.csw.stamps.entities.ArtistEntity;
import co.edu.uniandes.csw.stamps.entities.StampEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * @generated
 */
@Path("/stamps")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RootStampResource {

    @Inject private IStampLogic stampLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
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
     * Obtiene la lista de los registros de Artist
     *
     * @return Colección de objetos de StampDetailDTO
     * @generated
     */
    @GET
    public List<StampDetailDTO> getStamps() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", stampLogic.countStamps());
            return listEntity2DTO(stampLogic.getStamps(page, maxRecords,null));
        }
        return listEntity2DTO(stampLogic.getStamps(null,null,null));
    }
    
    /**
     * Obtiene la lista de los registros de Stamp por categoria.
     *
     * @param categoryid id de la categoria.
     * @return Colección de objetos de StampDetailDTO.
     * @generated
     */
    @GET
    @Path("{categoryid: \\d+}")
    public List<StampDetailDTO> getStampByCategory(@PathParam("categoryid") Long categoryid) {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", stampLogic.countStamps());
            return listEntity2DTO(stampLogic.getStampByCategory(page, maxRecords,categoryid));
        }
        return listEntity2DTO(stampLogic.getStampByCategory(null,null,categoryid));
    }
    

        
        /**
     * Obtiene la lista de los registros de Stamp
     *
     * @return Colección de objetos de StampBasicDTO
     * @generated
     */
    @GET
    @Path("/all")
    public List<StampDetailDTO> getStampsAll() {

            return listEntity2DTO(stampLogic.getStampsAll());
        
    }

    @GET
    @Path("/HighlightedStamps")
    public List<StampDetailDTO> getHighlighted() {
           return listEntity2DTO(stampLogic.getHighlighted());
    }
    
    @GET
    @Path("/latestStamps")
    public List<StampDetailDTO> getLatest() {
           return listEntity2DTO(stampLogic.getLatest());
    }

}
