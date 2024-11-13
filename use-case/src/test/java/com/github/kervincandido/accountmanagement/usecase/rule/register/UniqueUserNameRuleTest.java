package com.github.kervincandido.accountmanagement.usecase.rule.register;

import com.github.kervincandido.accountmanagement.domain.entity.Email;
import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.usecase.exception.BusinessException;
import com.github.kervincandido.accountmanagement.usecase.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UniqueUserNameRuleTest {

    private AutoCloseable mockitoAnnotations;
    private final Random random = new Random();

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UniqueUserNameRule uniqueUserNameRule;

    @BeforeEach
    void setUp() {
        mockitoAnnotations = MockitoAnnotations.openMocks(this);
    }

    @Test
    void freeUserName() {
        var user = new User("tester", "secret", new Email("tester@mail.com"));
        when(repository.findByUserName(anyString())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> uniqueUserNameRule.validate(user));
        verify(repository).findByUserName(anyString());
    }

    @Test
    void usernameAlreadyUsed() {
        var existingUser = new User(
                UUID.randomUUID(),
                "tester",
                "secret",
                new Email(random.nextLong(0, Long.MAX_VALUE), "thetester@mail.com"),
                LocalDateTime.now()
        );
        var user = new User("tester", "secret", new Email("tester@mail.com"));
        when(repository.findByUserName(anyString())).thenReturn(Optional.of(existingUser));
        assertThrows(BusinessException.class, () -> uniqueUserNameRule.validate(user));
        verify(repository).findByUserName(anyString());
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoAnnotations.close();
    }
}