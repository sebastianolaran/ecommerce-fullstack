package org.sebastian.dao;


import org.sebastian.domain.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductoDAO extends CrudRepository<Producto,Long> {


    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    Optional<Producto> encontrarPorNombre(@Param("nombre") String nombre);

}
