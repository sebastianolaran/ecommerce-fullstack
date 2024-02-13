package org.sebastian.service.usuario;

import org.sebastian.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {


    List<Usuario> obtenerUsuarios();

    String guardar(Usuario usuario);

    public void eliminar(Usuario usuario);

    public Usuario encontrarUsuario(Usuario usuario);

    Optional<Usuario> encontrarUsuarioPorEmail(String email);

    String verificarLogin(String email, String contrasenia);


}
