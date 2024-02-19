package org.sebastian.excepciones;

public class EmailEnUsoExcepcion extends Exception{
    public EmailEnUsoExcepcion() {
        super("El email esta en uso");
    }

    public EmailEnUsoExcepcion(String message) {
        super(message);
    }

    public EmailEnUsoExcepcion(String message, Throwable cause) {
        super(message, cause);
    }
}
