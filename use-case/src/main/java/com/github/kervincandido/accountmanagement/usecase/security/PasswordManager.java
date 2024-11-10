package com.github.kervincandido.accountmanagement.usecase.security;

public interface PasswordManager {
    String encrypt(String password);
    boolean matches(String rawPassword, String encryptedPassword);
}
