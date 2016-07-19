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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.stamps.api.IStampLogic;
import co.edu.uniandes.csw.stamps.dtos.basic.CategoryBasicDTO;
import co.edu.uniandes.csw.stamps.entities.CategoryEntity;
import java.util.ArrayList;
/**
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StampCategoryResource {

    @Inject private IStampLogic stampLogic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de CategoryEntity a una lista de CategoryBasicDTO.
     *
     * @param entityList Lista de CategoryEntity a convertir.
     * @return Lista de CategoryBasicDTO convertida.
     * @generated
     */
    private List<CategoryBasicDTO> categoryListEntity2DTO(List<CategoryEntity> entityList){
        List<CategoryBasicDTO> list = new ArrayList<>();
        for (CategoryEntity entity : entityList) {
            list.add(new CategoryBasicDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de CategoryBasicDTO a una lista de CategoryEntity.
     *
     * @param dtos Lista de CategoryBasicDTO a convertir.
     * @return Lista de CategoryEntity convertida.
     * @generated
     */
    private List<CategoryEntity> categoryListDTO2Entity(List<CategoryBasicDTO> dtos){
        List<CategoryEntity> list = new ArrayList<>();
        for (CategoryBasicDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de CategoryBasicDTO asociadas a una
     * instancia de Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @return Colección de instancias de CategoryBasicDTO asociadas a la instancia de Stamp
     * @generated
     */
    @GET
    public List<CategoryBasicDTO> listCategory(@PathParam("stampId") Long stampId) {
        return categoryListEntity2DTO(stampLogic.listCategory(stampId));
    }

    /**
     * Obtiene una instancia de Category asociada a una instancia de Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param categoryId Identificador de la instancia de Category
     * @generated
     */
    @GET
    @Path("{categoryId: \\d+}")
    public CategoryBasicDTO getCategory(@PathParam("stampId") Long stampId, @PathParam("categoryId") Long categoryId) {
        return new CategoryBasicDTO(stampLogic.getCategory(stampId, categoryId));
    }

    /**
     * Asocia un Category existente a un Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param categoryId Identificador de la instancia de Category
     * @return Instancia de CategoryBasicDTO que fue asociada a Stamp
     * @generated
     */
    @POST
    @Path("{categoryId: \\d+}")
    public CategoryBasicDTO addCategory(@PathParam("stampId") Long stampId, @PathParam("categoryId") Long categoryId) {
        return new CategoryBasicDTO(stampLogic.addCategory(stampId, categoryId));
    }

    /**
     * Remplaza las instancias de Category asociadas a una instancia de Stamp
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param categorys Colección de instancias de CategoryDTO a asociar a instancia de Stamp
     * @return Nueva colección de CategoryDTO asociada a la instancia de Stamp
     * @generated
     */
    @PUT
    public List<CategoryBasicDTO> replaceCategory(@PathParam("stampId") Long stampId, List<CategoryBasicDTO> categorys) {
        return categoryListEntity2DTO(stampLogic.replaceCategory(stampId, categoryListDTO2Entity(categorys)));
    }

    /**
     * Desasocia un Category existente de un Stamp existente
     *
     * @param stampId Identificador de la instancia de Stamp
     * @param categoryId Identificador de la instancia de Category
     * @generated
     */
    @DELETE
    @Path("{categoryId: \\d+}")
    public void removeCategory(@PathParam("stampId") Long stampId, @PathParam("categoryId") Long categoryId) {
        stampLogic.removeCategory(stampId, categoryId);
    }
}
