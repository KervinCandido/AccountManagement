package com.github.kervincandido.accountmanagement.repository.user;

import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.repository.excetion.RepositoryException;

public interface UserRepository {
    <E extends RepositoryException> User save(User user) throws E;
}
