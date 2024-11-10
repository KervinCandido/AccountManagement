package com.github.kervincandido.accountmanagement.domain.entity;

import com.github.kervincandido.accountmanagement.domain.exception.InvalidUserNameException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public class User {

    private static final String USERNAME_REGEX = "^(?=.{3,12}$)[a-zA-Z][a-zA-Z0-9]*$";
    private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);

    private final UUID userUuid;
    private final String userName;
    private String password;
    private final Email email;
    private LocalDateTime inclusionDate;
    private LocalDateTime exclusionDate;
    private LocalDateTime lastUpdateDate;

    public User(UUID userUuid, String userName, String password, Email email, LocalDateTime inclusionDate) {
        Objects.requireNonNull(userName, "the user name cannot be null");
        Objects.requireNonNull(email, "the email cannot be null");
        if (!USERNAME_PATTERN.matcher(userName).matches())
            throw new InvalidUserNameException(userName);
        this.userUuid = userUuid;
        this.userName = userName;
        this.setPassword(password);
        this.email = email;
    }

    public User(String userName, String password, Email email) {
        this(null, userName, password, email, null);
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Objects.requireNonNull(password, "the password cannot be null");
        if (password.trim().isBlank()) throw new IllegalArgumentException("the password cannot be empty");
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public LocalDateTime getInclusionDate() {
        return inclusionDate;
    }

    public LocalDateTime getExclusionDate() {
        return exclusionDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setInclusionDate(LocalDateTime inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public void setExclusionDate(LocalDateTime exclusionDate) {
        this.exclusionDate = exclusionDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userUuid=" + userUuid +
                ", userName='" + userName + '\'' +
                ", email=" + email +
                ", inclusionDate=" + inclusionDate +
                ", exclusionDate=" + exclusionDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        return userName.equals(user.userName) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
