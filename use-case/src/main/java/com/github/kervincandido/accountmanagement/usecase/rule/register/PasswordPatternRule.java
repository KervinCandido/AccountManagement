package com.github.kervincandido.accountmanagement.usecase.rule.register;

import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.usecase.exception.BusinessException;
import com.github.kervincandido.accountmanagement.usecase.validation.Validation;

import java.util.regex.Pattern;

public class PasswordPatternRule implements Validation<User> {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>\\[\\] ';/`~¨_+=-])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>\\[\\] ';/`~¨_+=-]{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public <E extends BusinessException> void validate(User user) throws E {
        if (user.getPassword().length() < 8) {
            throw new BusinessException("Password must be at least 8 characters long");
        } else if (user.getPassword().length() > 20) {
            throw new BusinessException("Password must not be longer than 20 characters");
        }
        if (!pattern.matcher(user.getPassword()).matches()) {
            throw new BusinessException("The password is weak. Please use at least one lowercase letter, one uppercase letter, one number and one special character.");
        }
    }
}
