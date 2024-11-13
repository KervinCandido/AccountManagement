package com.github.kervincandido.accountmanagement.usecase.rule.register;

import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.usecase.exception.BusinessException;
import com.github.kervincandido.accountmanagement.usecase.repository.UserRepository;
import com.github.kervincandido.accountmanagement.usecase.validation.Validation;

import java.util.Optional;

public class UniqueUserNameRule implements Validation<User> {

    private final UserRepository repository;

    public UniqueUserNameRule(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public <E extends BusinessException> void validate(User user) throws E {
        Optional<User> optionalUser = repository.findByUserName(user.getUserName());
        if (optionalUser.isPresent()) {
            throw new BusinessException("Username is already taken");
        }
    }
}
