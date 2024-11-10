package com.github.kervincandido.accountmanagement.usecase.validation;

import com.github.kervincandido.accountmanagement.usecase.exception.BusinessException;

@FunctionalInterface
public interface Validation<T> {
    <E extends BusinessException> void validate(T t) throws E;
}
