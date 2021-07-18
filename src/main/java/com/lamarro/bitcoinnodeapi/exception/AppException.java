package com.lamarro.bitcoinnodeapi.exception;

public class AppException extends RuntimeException {

    public AppException(Exception e) {
        super(e);
    }
}
