package org.sebastian.excepciones;

public class ContraseñaIncorrectaExcepcion extends Exception{
    public ContraseñaIncorrectaExcepcion() {
        super("Contraseña incorrecta");
    }

    public ContraseñaIncorrectaExcepcion(String message) {
        super(message);
    }

    public ContraseñaIncorrectaExcepcion(String message, Throwable cause) {
        super(message, cause);
    }
}
