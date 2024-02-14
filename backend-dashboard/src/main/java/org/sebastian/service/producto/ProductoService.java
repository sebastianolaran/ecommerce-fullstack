package org.sebastian.service.producto;

import org.sebastian.domain.Producto;
import org.sebastian.service.producto.http.request.EditarRequest;


import java.util.List;


public interface ProductoService {

    /**
     * @return Una lista de productos recuperadas de la base de datos.
     */
    List<Producto> listarProductos();

    /**
     * Agrega el producto a la base de datos
     * @param producto El producto a agregar en la base de datos
     */

    void guardar(Producto producto);


    /**
     * Elimina el producto con ese mismo id
     * @param id_producto El ID del producto para eliminar de la base de datos

     */
    void eliminar(String id_producto);


    /**
     * Obtiene el producto asociado a la id dada
     * @param id_producto El ID del producto que se requiere
     * @return El producto que se busca, si no se lo encuentra un objeto Null
     */
    Producto encontrarProducto(Long id_producto);

    /**
     * Edita al producto con los datos obtenidos de la peticion
     * @param request Peticion de parte del cliente que contiene todos los datos nesecarios
     * @return Un string que indica el estado finalizado de la operacion
     */

    String editarProducto(EditarRequest request);
}
