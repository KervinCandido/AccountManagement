package com.github.kervincandido.accountmanagement.domain.exception;

public class InvalidIdentifierException extends RuntimeException {
    public InvalidIdentifierException(String identifier) {
        super("Invalid identifier exception : " + identifier);
    }
}
