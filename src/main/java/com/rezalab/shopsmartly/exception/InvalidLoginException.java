package com.rezalab.shopsmartly.exception;

public class InvalidLoginException extends RuntimeException {

    private static final long serialVersionUID = 5704820133651147829L;

    public InvalidLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLoginException(String message) {
        super(message);
    }

    public InvalidLoginException(Throwable cause) {
        super(cause);
    }
}
