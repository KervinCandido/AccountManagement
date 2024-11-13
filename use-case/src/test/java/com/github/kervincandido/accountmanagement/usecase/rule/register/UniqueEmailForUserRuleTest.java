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
import static org.mockito.Mockito.*;

class UniqueEmailForUserRuleTest {

    private AutoCloseable mockitoAnnotations;
    private final Random random = new Random();

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UniqueEmailForUserRule uniqueEmailForUserRule;

    @BeforeEach
    void setUp() {
        mockitoAnnotations = MockitoAnnotations.openMocks(this);
    }

    @Test
    void freeEmail() {
        var existingUserWithEmail = new User("tester", "secret", new Email("thetester@mail.com"));
        when(repository.findByEmail(any(Email.class))).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> uniqueEmailForUserRule.validate(existingUserWithEmail));
        verify(repository).findByEmail(any(Email.class));
    }

    @Test
    void emailAlreadyUsed() {
        var existingUserWithEmail = new User(
                UUID.randomUUID(),
                "tester",
                "secret",
                new Email(random.nextLong(0, Long.MAX_VALUE), "tester@mail.com"),
                LocalDateTime.now()
        );
        when(repository.findByEmail(any(Email.class))).thenReturn(Optional.of(existingUserWithEmail));
        assertThrows(BusinessException.class, () -> uniqueEmailForUserRule.validate(existingUserWithEmail));
        verify(repository).findByEmail(any(Email.class));
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoAnnotations.close();
    }
}