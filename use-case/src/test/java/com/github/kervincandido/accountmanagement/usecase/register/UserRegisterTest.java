package com.github.kervincandido.accountmanagement.usecase.register;

import com.github.kervincandido.accountmanagement.domain.entity.Email;
import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.usecase.repository.UserRepository;
import com.github.kervincandido.accountmanagement.usecase.security.PasswordManager;
import com.github.kervincandido.accountmanagement.usecase.validation.Validation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

class UserRegisterTest {

    private AutoCloseable mockitoAnnotations;
    private final Random random = new Random();

    @Mock
    private UserRepository repository;
    @Mock
    private PasswordManager passwordManager;
    @Spy
    private List<Validation<User>> validations = List.of();

    private UserRegister userRegister;

    @BeforeEach
    void setUp() {
        mockitoAnnotations = MockitoAnnotations.openMocks(this);
        when(repository.save(any(User.class)))
                .then(invocation -> {
                    var user = invocation.getArgument(0, User.class);
                    var newEmail = new Email(random.nextLong(1, Long.MAX_VALUE), user.getEmail().emailAddress());
                    return new User(UUID.randomUUID(), user.getUserName(), user.getPassword(), newEmail, LocalDateTime.now());
                });
        when(passwordManager.encrypt(any(String.class))).thenReturn("encryptedPassword");
        this.userRegister = new UserRegister(repository, passwordManager, validations);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoAnnotations.close();
    }

    @Test
    void registerASimpleUser() {
        var newUser = userRegister.register (
                new User(
                        "tester",
                        "secret",
                        new Email(null, "tester@test.com")
                )
        );

        assertNotNull(newUser);
        assertNotNull(newUser.getUserUuid());
        assertNotNull(newUser.getUserName());
        assertNotNull(newUser.getPassword());
        assertNotNull(newUser.getEmail());
        assertNotNull(newUser.getEmail().emailId());
        assertNotNull(newUser.getEmail().emailAddress());

        assertEquals("tester", newUser.getUserName());
        assertEquals("encryptedPassword", newUser.getPassword());
        assertEquals("tester@test.com", newUser.getEmail().emailAddress());

        verify(validations).forEach(any());
        verify(passwordManager).encrypt(any(String.class));
        verify(repository).save(any(User.class));
    }
}