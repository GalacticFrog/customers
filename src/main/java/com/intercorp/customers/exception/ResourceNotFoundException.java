package com.intercorp.customers.exception;

/**
 *
 * @author USUARIO
 */

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
