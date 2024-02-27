package org.sebastian.service.usuario;

import org.sebastian.dao.UsuarioDAO;
import org.sebastian.domain.Usuario;
import org.sebastian.excepciones.ContraseñaIncorrectaExcepcion;
import org.sebastian.excepciones.EmailEnUsoExcepcion;
import org.sebastian.excepciones.EmailNoEncontrado;
import org.sebastian.service.jwt.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;



@Service
public class UsuarioServiceImpl implements UsuarioService {



    private final JwtTokenService tokenService;


    private UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioServiceImpl(JwtTokenService tokenService, UsuarioDAO usuarioDAO) {
        this.tokenService = tokenService;
        this.usuarioDAO = usuarioDAO;
    }



    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.findAll();
    }

    @Override
    public String guardar(Usuario usuario) {
        String mensaje;
        try {
            // Verificar si el correo electrónico ya está en uso
            Optional<Usuario> usuarioExistente = usuarioDAO.findByEmail(usuario.getEmail());
            if (usuarioExistente.isPresent()) {
                throw new EmailEnUsoExcepcion();
            }else{
            // Si el correo electrónico no está en uso, guardar el usuario
            usuarioDAO.save(usuario);
            mensaje =  this.tokenService.generateToken(usuario.getEmail(),usuario.getPassword());}
        } catch (Exception e) {
            mensaje ="Error al intentar guardar el usuario: " + e.getMessage();
        }
        return mensaje;
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
    public String verificarLogin(String email, String contrasenia) throws ContraseñaIncorrectaExcepcion, EmailNoEncontrado {
        String mensaje;
        Optional<Usuario> optionalUser = this.encontrarUsuarioPorEmail(email);

        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();

            if (Objects.equals(contrasenia, user.getPassword())) {
                mensaje = this.tokenService.generateToken(email,contrasenia);
            } else {
                throw new ContraseñaIncorrectaExcepcion();
            }
        } else {

            throw new EmailNoEncontrado();

        }
        return mensaje;
    }


}
