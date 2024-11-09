package com.github.kervincandido.accountmanagement.domain.exception;

public class InvalidEmailAddressException extends RuntimeException {
    public InvalidEmailAddressException(String emailAddress) {
        super("Invalid email address : " + emailAddress);
    }
}
