module kervincandido.accountmanagement.usecase {
    requires transitive kervincandido.accountmanagement.domain;
    requires transitive kervincandido.accountmanagement.repository;

    exports com.github.kervincandido.accountmanagement.usecase.exception;
    exports com.github.kervincandido.accountmanagement.usecase.rule.register;
    exports com.github.kervincandido.accountmanagement.usecase.security;
    exports com.github.kervincandido.accountmanagement.usecase.repository;
    exports com.github.kervincandido.accountmanagement.usecase.validation;
}