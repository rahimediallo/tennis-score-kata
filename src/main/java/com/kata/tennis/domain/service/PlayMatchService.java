package com.kata.tennis.domain.service;

import com.kata.tennis.application.port.in.PlayMatchUseCase;
import com.kata.tennis.application.port.out.ScorePresenter;
import com.kata.tennis.domain.model.Player;
import com.kata.tennis.domain.model.Score;
import com.kata.tennis.domain.service.TennisGame;

import java.util.Objects;

public final class PlayMatchService implements PlayMatchUseCase {

    private final TennisGame game;
    private final ScorePresenter presenter;

    public PlayMatchService(TennisGame game, ScorePresenter presenter) {
        this.game = Objects.requireNonNull(game, "game");
        this.presenter = Objects.requireNonNull(presenter, "presenter");
    }

    @Override
    public void play(String sequence) {
        Objects.requireNonNull(sequence, "sequence");

        Score score = Score.initial();

        for (char c : sequence.toCharArray()) {
            Player winner = parse(c);

            score = game.playBall(score, winner);
            presenter.onScore(game.formatLine(score));

            if (score.winner().isPresent()) {
                break;
            }
        }
    }

    private Player parse(char c) {
        return switch (c) {
            case 'A' -> Player.A;
            case 'B' -> Player.B;
            default -> throw new IllegalArgumentException("Invalid char: " + c + " (expected A or B)");
        };
    }
}
