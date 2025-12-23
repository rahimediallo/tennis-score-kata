package com.kata.tennis.domain.model;

import java.util.Optional;

public final class Score {

    private final Point a;
    private final Point b;
    private final Optional<Player> winner;

    private Score(Point a, Point b, Optional<Player> winner) {
        this.a = a;
        this.b = b;
        this.winner = winner;
    }

    public static Score initial() {
        return new Score(Point.LOVE, Point.LOVE, Optional.empty());
    }

    public Point point(Player p) {
        return p == Player.A ? a : b;
    }

    public Optional<Player> winner() {
        return winner;
    }

    public Score withPoints(Point na, Point nb) {
        return new Score(na, nb, Optional.empty());
    }

    public Score win(Player p) {
        return new Score(a, b, Optional.of(p));
    }
}
