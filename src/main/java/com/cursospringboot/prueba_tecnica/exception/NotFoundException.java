package com.cursospringboot.prueba_tecnica.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}
