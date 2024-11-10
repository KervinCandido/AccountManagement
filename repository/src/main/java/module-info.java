module kervincandido.accountmanagement.repository {
    requires transitive kervincandido.accountmanagement.domain;

    exports com.github.kervincandido.accountmanagement.repository.excetion;
    exports com.github.kervincandido.accountmanagement.repository.user;
}