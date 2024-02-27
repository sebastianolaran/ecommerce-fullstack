package org.sebastian.dao;

import org.sebastian.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDAO extends JpaRepository<Usuario,Long> {

    /**
     * Busca un usuario por su dirección de correo electrónico.
     * @param email La dirección de correo electrónico del usuario a buscar.
     * @return Un Optional que contiene el usuario encontrado, si existe.
     */

    Optional<Usuario> findByEmail(String email);




}
