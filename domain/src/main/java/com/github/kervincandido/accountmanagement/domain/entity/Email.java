package com.github.kervincandido.accountmanagement.domain.entity;

import com.github.kervincandido.accountmanagement.domain.exception.InvalidEmailAddressException;
import com.github.kervincandido.accountmanagement.domain.exception.InvalidIdentifierException;

import java.util.Objects;
import java.util.regex.Pattern;

public record Email(Long emailId, String emailAddress) {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public Email(Long emailId, String emailAddress) {
        Objects.requireNonNull(emailAddress, "email Address cannot be null");
        if (emailId != null && emailId < 1)
            throw new InvalidIdentifierException(String.valueOf(emailId));
        if (!EMAIL_PATTERN.matcher(emailAddress.trim()).matches())
            throw new InvalidEmailAddressException(emailAddress);

        this.emailId = emailId;
        this.emailAddress = emailAddress.trim();
    }

    public Email(String emailAddress) {
        this(null, emailAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email)) return false;

        return emailAddress.equals(email.emailAddress);
    }

    @Override
    public int hashCode() {
        return emailAddress.hashCode();
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailId=" + emailId +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
