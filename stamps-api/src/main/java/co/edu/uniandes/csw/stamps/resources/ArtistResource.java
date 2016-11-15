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
import co.edu.uniandes.csw.auth.stormpath.Utils;
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
import co.edu.uniandes.csw.stamps.dtos.detail.ArtistDetailDTO;
import co.edu.uniandes.csw.stamps.entities.ArtistEntity;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;

/**
 * @generated
 */
@Path("/artists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArtistResource {
    private static final String ARTIST_HREF = "https://api.stormpath.com/v1/groups/1UvMtOQRTKolOdyOKZZ2Gn";
    private static final String ADMIN_HREF = "https://api.stormpath.com/v1/groups/135l6x0yE4XawGjWV9ISq9";    

    @Inject private IArtistLogic artistLogic;
    @Context private HttpServletResponse response;
    @Context private HttpServletRequest req;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de ArtistEntity a una lista de ArtistBasicDTO.
     *
     * @param entityList Lista de ArtistEntity a convertir.
     * @return Lista de ArtistBasicDTO convertida.
     * @generated
     */
    private List<ArtistDetailDTO> listEntity2DTO(List<ArtistEntity> entityList){
        List<ArtistDetailDTO> list = new ArrayList<>();
        for (ArtistEntity entity : entityList) {
            list.add(new ArtistDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Artist
     *
     * @return Colección de objetos de ArtistBasicDTO
     * @generated
     */
    @GET
    public List<ArtistDetailDTO> getArtists() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = Utils.getClient().getResource(accountHref, Account.class);
            for (Group gr : account.getGroups()) {
                switch (gr.getHref()) {
                    case ADMIN_HREF:
                        if (page != null && maxRecords != null) {
                        this.response.setIntHeader("X-Total-Count", artistLogic.countArtists());
                        return listEntity2DTO(artistLogic.getArtists(page, maxRecords));
                    }
                    return listEntity2DTO(artistLogic.getArtists());
                    case ARTIST_HREF:
                        Integer id = (int) account.getCustomData().get("artist_id");
                        List<ArtistDetailDTO> list = new ArrayList();
                        list.add(new ArtistDetailDTO(artistLogic.getArtist(id.longValue())));
                        return list;
                }
            }
        } 
         return listEntity2DTO(artistLogic.getArtists(page, maxRecords));
        
    }

    /**
     * Obtiene los datos de una instancia de Artist a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ArtistBasicDTO con los datos del Artist consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ArtistDetailDTO getArtist(@PathParam("id") Long id) {
        return new ArtistDetailDTO(artistLogic.getArtist(id));
    }
    
        /**
     * Obtiene los datos de una instancia de Artist a partir de su nombre
     *
     * @param name nombre de la instancia a consultar
     * @return Instancia de ArtistBasicDTO con los datos del Artist consultado
     * @generated
     */
    @GET
    @Path("/byname/{name}")
    public ArtistDetailDTO getArtistByName(@PathParam("name") String name) {
        return new ArtistDetailDTO(artistLogic.getArtistByName(name));
    }

    /**
     * Se encarga de crear un Artist en la base de datos
     *
     * @param dto Objeto de ArtistBasicDTO con los datos nuevos
     * @return Objeto de ArtistBasicDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public ArtistDetailDTO createArtist(ArtistDetailDTO dto) {
        return new ArtistDetailDTO(artistLogic.createArtist(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Artist
     *
     * @param id Identificador de la instancia de Artist a modificar
     * @param dto Instancia de ArtistBasicDTO con los nuevos datos
     * @return Instancia de ArtistBasicDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ArtistDetailDTO updateArtist(@PathParam("id") Long id, ArtistDetailDTO dto) {
        ArtistEntity entity = dto.toEntity();
        entity.setId(id);
        ArtistEntity oldEntity = artistLogic.getArtist(id);
        return new ArtistDetailDTO(artistLogic.updateArtist(entity));
    }

    /**
     * Elimina una instancia de Artist de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteArtist(@PathParam("id") Long id) {
        artistLogic.deleteArtist(id);
    }
    public void existsArtist(Long artistId){
        ArtistDetailDTO artist = getArtist(artistId);
        if (artist== null) {
            throw new WebApplicationException(404);
        }
    }
    
    @Path("{artistId: \\d+}/stamps")
    public Class<StampResource> getStampResource(@PathParam("artistId") Long artistId){
        existsArtist(artistId);
        return StampResource.class;
    }
    
     @GET
    @Path("/HighlightedArtist")
    public List<ArtistDetailDTO> getHighlighted() {
           return listEntity2DTO(artistLogic.getHighlighted());
    }
    
}
