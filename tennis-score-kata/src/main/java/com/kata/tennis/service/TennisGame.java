package com.kata.tennis.service;


import com.kata.tennis.model.Player;
import com.kata.tennis.model.Point;
import com.kata.tennis.model.Score;

public final class TennisGame {

    public Score playBall(Score current, Player winner) {
        if (current.winner().isPresent()) return current;

        Point a = current.point(Player.A);
        Point b = current.point(Player.B);

        // Advantage
        if (a == Point.ADVANTAGE || b == Point.ADVANTAGE) {
            return handleAdvantage(current, winner);
        }

        // Deuce
        if (a == Point.FORTY && b == Point.FORTY) {
            return winner == Player.A
                    ? current.withPoints(Point.ADVANTAGE, Point.FORTY)
                    : current.withPoints(Point.FORTY, Point.ADVANTAGE);
        }

        // Normal
        return winner == Player.A
                ? scoreForA(current)
                : scoreForB(current);
    }

    private Score handleAdvantage(Score current, Player winner) {
        if (current.point(winner) == Point.ADVANTAGE) {
            return current.win(winner);
        }
        return current.withPoints(Point.FORTY, Point.FORTY);
    }

    private Score scoreForA(Score current) {
        Point a = current.point(Player.A);
        Point b = current.point(Player.B);

        if (a == Point.FORTY && b != Point.FORTY) {
            return current.win(Player.A);
        }
        return current.withPoints(a.next(), b);
    }

    private Score scoreForB(Score current) {
        Point a = current.point(Player.A);
        Point b = current.point(Player.B);

        if (b == Point.FORTY && a != Point.FORTY) {
            return current.win(Player.B);
        }
        return current.withPoints(a, b.next());
    }

    public String formatLine(Score s) {
        if (s.winner().isPresent()) {
            return s.winner().get().label() + " wins the game";
        }
        return "Player A : " + s.point(Player.A).value()
             + " / Player B : " + s.point(Player.B).value();
    }
}
