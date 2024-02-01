package org.sebastian.service.usuario;

import org.sebastian.dao.UsuarioDAO;
import org.sebastian.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService  {


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
    @Transactional
    public Optional<Usuario> encontrarUsuarioPorEmail(String email) {
       return usuarioDAO.findByEmail(email);
    }

}
