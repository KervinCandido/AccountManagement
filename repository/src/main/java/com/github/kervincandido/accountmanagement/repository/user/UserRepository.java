package com.github.kervincandido.accountmanagement.repository.user;

import com.github.kervincandido.accountmanagement.domain.entity.Email;
import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.repository.excetion.RepositoryException;

import java.util.Optional;

public interface UserRepository {
    <E extends RepositoryException> User save(User user) throws E;
    <E extends RepositoryException> Optional<User> findByUserName(String userName) throws E;
    <E extends RepositoryException> Optional<User> findByEmail(Email email) throws E;
}
