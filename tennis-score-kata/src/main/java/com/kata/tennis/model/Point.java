package com.kata.tennis.model;

public enum Point {
    LOVE(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40),
    ADVANTAGE(50);

    private final int value;

    Point(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public Point next() {
        return switch (this) {
            case LOVE -> FIFTEEN;
            case FIFTEEN -> THIRTY;
            case THIRTY -> FORTY;
            default -> this;
        };
    }
}
