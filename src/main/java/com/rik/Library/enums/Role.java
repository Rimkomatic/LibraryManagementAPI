package com.rik.Library.enums;

public enum Role {
    ADMIN(1),
    CUSTOMER(0);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Role fromValue(int value) {
        for (Role role : values()) {
            if (role.value == value) return role;
        }
        throw new IllegalArgumentException("Invalid role value: " + value);
    }
}

