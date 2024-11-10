package com.github.kervincandido.accountmanagement.domain.entity;

import com.github.kervincandido.accountmanagement.domain.exception.InvalidUserNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void nullFieldThrowException() {
        assertThrows(NullPointerException.class, () -> new User(null, null, null));
        assertThrows(NullPointerException.class, () -> new User("test", null, null));
        assertThrows(NullPointerException.class, () -> new User("test", "secret", null));
        var user = new User("test", "secret", new Email(null, "email@email"));
        assertNotNull(user.getUserName());
        assertEquals("test", user.getUserName());
        assertNotNull(user.getPassword());
        assertEquals("secret", user.getPassword());
        assertNotNull(user.getEmail());
        assertEquals(new Email(null, "email@email"), user.getEmail());
    }

    @Test
    void validUser() {
        var user = new User("test", "secret", new Email(null, "email@email"));
        assertNotNull(user.getUserName());
        assertEquals("test", user.getUserName());
        assertNotNull(user.getPassword());
        assertEquals("secret", user.getPassword());
        assertNotNull(user.getPassword());
        assertEquals(new Email(null, "email@email"), user.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Vi", "1van", "theSuperUserWithALargeName"})
    void invalidUserName(String userName) {
        var email = new Email(null, "user@mail.com");
        assertThrows(InvalidUserNameException.class, () -> new User(userName, "secret", email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"john", "marie", "littleBoy199"})
    void validUserName(String userName) {
        var email = new Email(null, "user@mail.com");
        var user = new User(userName, "secret", email);
        assertNotNull(user);
        assertNotNull(user.getUserName());
        assertEquals(userName, user.getUserName());
    }
}