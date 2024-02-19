package org.sebastian.service.usuario;

import org.sebastian.domain.Usuario;
import org.sebastian.excepciones.ContraseñaIncorrectaExcepcion;
import org.sebastian.excepciones.EmailNoEncontrado;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    /**
     * @return Una lista de usuarios recuperados de la base de datos.
     */
    List<Usuario> obtenerUsuarios();

    /**
     * Agrega el usuario a la base de datos
     * @param usuario El usuario a agregar en la base de datos
     */
    String guardar(Usuario usuario);

    /**
     * Elimina el usuario de la base de datos
     * @param usuario El usuario para eliminar de la base de datos

     */

    void eliminar(Usuario usuario);

    /**
     * Obtiene el usuario asociado a la id dada
     * @param usuario El usuario con la id de la peticion  que se requiere encontrar
     * @return El usuario que se busca, si no se lo encuentra un objeto Null
     */
    Usuario encontrarUsuario(Usuario usuario);

    /**
     * Obtiene el usuario con el email asociado en la base de datos
     * @param email El email del usuario que se requiere encontrar
     * @return El usuario que se busca, si no se lo encuentra un objeto Null
     */
    Optional<Usuario> encontrarUsuarioPorEmail(String email);

    /**
     * Verifica que los datos enviados pertenezcan y correspondan a un usuario
     * @param email El email del usuario que se requiere encontrar
     * @param contrasenia La contraseña del usuario que se requiere encontrar
     * @return Un string que indica el estado final de la operacion
     */
    String verificarLogin(String email, String contrasenia) throws ContraseñaIncorrectaExcepcion, EmailNoEncontrado;


}
