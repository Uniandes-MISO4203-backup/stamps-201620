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
import co.edu.uniandes.csw.stamps.api.ICategoryLogic;
import co.edu.uniandes.csw.stamps.dtos.basic.CategoryBasicDTO;
import co.edu.uniandes.csw.stamps.entities.CategoryEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * @generated
 */
@Path("/categorys")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject private ICategoryLogic categoryLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de CategoryEntity a una lista de CategoryBasicDTO.
     *
     * @param entityList Lista de CategoryEntity a convertir.
     * @return Lista de CategoryBasicDTO convertida.
     * @generated
     */
    private List<CategoryBasicDTO> listEntity2DTO(List<CategoryEntity> entityList){
        List<CategoryBasicDTO> list = new ArrayList<>();
        for (CategoryEntity entity : entityList) {
            list.add(new CategoryBasicDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Category
     *
     * @return Colección de objetos de CategoryBasicDTO
     * @generated
     */
    @GET
    public List<CategoryBasicDTO> getCategorys() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", categoryLogic.countCategorys());
            return listEntity2DTO(categoryLogic.getCategorys(page, maxRecords));
        }
        return listEntity2DTO(categoryLogic.getCategorys());
    }

    /**
     * Obtiene los datos de una instancia de Category a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CategoryBasicDTO con los datos del Category consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public CategoryBasicDTO getCategory(@PathParam("id") Long id) {
        return new CategoryBasicDTO(categoryLogic.getCategory(id));
    }

    /**
     * Se encarga de crear un Category en la base de datos
     *
     * @param dto Objeto de CategoryBasicDTO con los datos nuevos
     * @return Objeto de CategoryBasicDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public CategoryBasicDTO createCategory(CategoryBasicDTO dto) {
        return new CategoryBasicDTO(categoryLogic.createCategory(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Category
     *
     * @param id Identificador de la instancia de Category a modificar
     * @param dto Instancia de CategoryBasicDTO con los nuevos datos
     * @return Instancia de CategoryBasicDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public CategoryBasicDTO updateCategory(@PathParam("id") Long id, CategoryBasicDTO dto) {
        CategoryEntity entity = dto.toEntity();
        entity.setId(id);
        CategoryEntity oldEntity = categoryLogic.getCategory(id);
        return new CategoryBasicDTO(categoryLogic.updateCategory(entity));
    }

    /**
     * Elimina una instancia de Category de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCategory(@PathParam("id") Long id) {
        categoryLogic.deleteCategory(id);
    }
    
}
