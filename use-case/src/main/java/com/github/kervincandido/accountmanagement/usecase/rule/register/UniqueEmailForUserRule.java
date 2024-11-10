package com.github.kervincandido.accountmanagement.usecase.rule.register;

import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.repository.user.UserRepository;
import com.github.kervincandido.accountmanagement.usecase.exception.BusinessException;
import com.github.kervincandido.accountmanagement.usecase.validation.Validation;

import java.util.Optional;

public class UniqueEmailForUserRule implements Validation<User> {

    private final UserRepository userRepository;

    public UniqueEmailForUserRule(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public <E extends BusinessException> void validate(User user) throws E {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new BusinessException("Email is already taken");
        }
    }
}
