package org.sebastian.dao;


import org.sebastian.domain.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDAO extends CrudRepository<Producto,Long> {

}
