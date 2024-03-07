package org.sebastian.test.serviceTest;

import org.junit.jupiter.api.Test;
import org.sebastian.domain.Producto;
import org.sebastian.service.producto.ProductoServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductosServiceTest {



    @Test
    public void test01ObtenerProductos(){
        ProductoServiceImpl productoService = new ProductoServiceImpl();

        List<Producto> productoList = productoService.listarProductos();

        assertEquals(productoList.size(),4);
    }
}
