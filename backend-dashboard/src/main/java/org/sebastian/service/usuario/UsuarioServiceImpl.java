package org.sebastian.service.usuario;

import org.sebastian.dao.ProductoDAO;
import org.sebastian.dao.UsuarioDAO;
import org.sebastian.domain.Producto;
import org.sebastian.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> obtenerUsuarios() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Override
    public void guardar(Usuario usuario)
    {
        usuarioDAO.save(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        usuarioDAO.delete(usuario);
    }

    @Override
    public Usuario encontrarUsuario(Usuario usuario) {
        return usuarioDAO.findById(usuario.getId_usuario()).orElse(null);
    }

    @Override
    public Usuario encontrarUsuarioPorUsername(String username) {
        List<Usuario> usuarios = (List<Usuario>) usuarioDAO.findAll();
        Usuario usuarioEncontrado = null;
        for ( Usuario usuario : usuarios){
            if(Objects.equals(usuario.getUsername(), username)){
                usuarioEncontrado = usuario;
            }
        }
        return usuarioEncontrado;

    }
}
