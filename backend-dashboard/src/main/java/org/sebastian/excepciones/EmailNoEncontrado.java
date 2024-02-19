package org.sebastian.excepciones;

public class EmailNoEncontrado extends Exception {

    public EmailNoEncontrado() {
        super("Email no encontrado incorrecta");
    }

    public EmailNoEncontrado(String message) {
        super(message);
    }

    public EmailNoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }
}
