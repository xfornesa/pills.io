package com.prunatic.pills.domain.users;

/**
 */
public class Email {
    private final String email;

    private Email(String email) {
        this.email = email;
    }

    public static Email fromString(String email) {
        return new Email(email);
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Pending to implement");
    }

    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("Pending to implement");
    }
}
