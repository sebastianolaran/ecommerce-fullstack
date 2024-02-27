package org.sebastian.excepciones;

public class ProductoExistente extends Exception{

    public ProductoExistente() {
        super("El producto ya existe en la base de datos");
    }

    public ProductoExistente(String message) {
        super(message);
    }

    public ProductoExistente(String message, Throwable cause) {
        super(message, cause);
    }
}
