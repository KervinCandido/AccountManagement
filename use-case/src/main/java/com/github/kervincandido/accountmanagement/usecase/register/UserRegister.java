package com.github.kervincandido.accountmanagement.usecase.register;

import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.repository.user.UserRepository;
import com.github.kervincandido.accountmanagement.usecase.exception.BusinessException;
import com.github.kervincandido.accountmanagement.usecase.security.PasswordManager;
import com.github.kervincandido.accountmanagement.usecase.validation.Validation;

import java.util.Collections;
import java.util.List;

public class UserRegister {

    private final List<Validation<User>> validations;
    private final UserRepository repository;
    private final PasswordManager passwordManager;

    public UserRegister(UserRepository repository, PasswordManager passwordManager, List<Validation<User>> validations) {
        this.repository = repository;
        this.passwordManager = passwordManager;
        this.validations = Collections.unmodifiableList(validations);
    }

    public <E extends BusinessException> User register(User user) throws E {
        validations.forEach(v -> v.validate(user));
        user.setPassword(passwordManager.encrypt(user.getPassword()));
        return repository.save(user);
    }
}
