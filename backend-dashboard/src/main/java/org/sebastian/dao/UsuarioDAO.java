package org.sebastian.dao;

import org.sebastian.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioDAO extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByUsername(String username);

}
