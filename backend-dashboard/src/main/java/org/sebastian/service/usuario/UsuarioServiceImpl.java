package org.sebastian.service.usuario;

import org.sebastian.dao.UsuarioDAO;
import org.sebastian.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService  {


    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.findAll();
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

    @Override
    public String verificarLogin(String email, String password) {
        String mensaje;
        if (Objects.equals(this.encontrarUsuarioPorEmail(email).get().getPassword(), password)){
            mensaje = "Login Correcto";
        }
        else {
            mensaje= "Login Incorrecto";
        }
        return mensaje;
    }

}
