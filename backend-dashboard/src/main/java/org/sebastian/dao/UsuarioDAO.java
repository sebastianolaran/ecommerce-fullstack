package org.sebastian.dao;

import org.sebastian.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario,Long> {
}
