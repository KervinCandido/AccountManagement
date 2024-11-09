package com.github.kervincandido.accountmanagement.domain.entity;

import com.github.kervincandido.accountmanagement.domain.exception.InvalidEmailAddressException;
import com.github.kervincandido.accountmanagement.domain.exception.InvalidIdentifierException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {"test@email.com", "test@emai", "test.test@mail.com.br", "test_email@mail"})
    void validEmails(String emailAddress) {
        var email = new Email(1L, emailAddress);
        assertNotNull(email);
        assertNotNull(email.emailId());
        assertNotNull(email.emailAddress());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "teste", "teste@", "test_test", "test.test"})
    void invalidEmail(String emailAddress) {
        assertThrows(InvalidEmailAddressException.class, () -> new Email(1L, emailAddress));
    }

    @Test
    void nullEmail() {
        assertThrows(NullPointerException.class, () -> new Email(1L, null));
    }

    @ParameterizedTest
    @ValueSource(longs = {-100_000_000, -100_000, -100, -1, -2, 0})
    void invalidEmailId(Long id) {
        assertThrows(InvalidIdentifierException.class, () -> new Email(id, "emailAddress@email"));
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 1000, 10_000, 100_000, 100_000_000})
    void validEmailId(Long id) {
        var email = new Email(id, "email.test@mail.com");
        assertNotNull(email);
        assertNotNull(email.emailId());
        assertNotNull(email.emailAddress());
    }
}