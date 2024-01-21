package org.sebastian.dao;

import org.sebastian.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioDAO extends CrudRepository<Usuario,Long> {


}
