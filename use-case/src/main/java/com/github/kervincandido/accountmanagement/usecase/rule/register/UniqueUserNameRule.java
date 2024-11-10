package com.github.kervincandido.accountmanagement.usecase.rule.register;

import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.usecase.exception.BusinessException;
import com.github.kervincandido.accountmanagement.usecase.validation.Validation;

public class UniqueUserNameRule implements Validation<User> {
    @Override
    public <E extends BusinessException> void validate(User user) throws E {
    }
}
