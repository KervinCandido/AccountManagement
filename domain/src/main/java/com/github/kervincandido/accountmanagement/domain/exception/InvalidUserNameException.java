package com.github.kervincandido.accountmanagement.domain.exception;

public class InvalidUserNameException extends RuntimeException {
    public InvalidUserNameException(String userName) {
        super("Invalid user name : " + userName);
    }
}
