package com.github.kervincandido.accountmanagement.usecase.repository;

import com.github.kervincandido.accountmanagement.domain.entity.Email;
import com.github.kervincandido.accountmanagement.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    <E extends RuntimeException> User save(User user) throws E;
    <E extends RuntimeException> Optional<User> findByUserName(String userName) throws E;
    <E extends RuntimeException> Optional<User> findByEmail(Email email) throws E;
}
