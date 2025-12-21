package com.kata.tennis.model;

public enum Player {
    A, B;

    public String label() {
        return this == A ? "Player A" : "Player B";

    }

}