package com.kata.tennis.domain.model;

public enum Player {
    A, B;

    public String label() {
        return this == A ? "Player A" : "Player B";

    }

}