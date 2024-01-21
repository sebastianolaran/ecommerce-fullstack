package org.sebastian.service.usuario;

import org.sebastian.domain.Producto;
import org.sebastian.domain.Usuario;

import java.util.List;

public interface UsuarioService {


    public List<Usuario> obtenerUsuarios();

    public void guardar(Usuario usuario);

    public void eliminar(Usuario usuario);

    public Usuario encontrarUsuario(Usuario usuario);

    Usuario encontrarUsuarioPorUsername(String username);


}
