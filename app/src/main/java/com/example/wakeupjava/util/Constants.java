package com.example.wakeupjava.util;

public enum Constants {

    PICK_FROM_FILE(7);

    private final int value;

    Constants(final int value) {
        this.value=value;
    }
    public int getValue() { return value; }
}
