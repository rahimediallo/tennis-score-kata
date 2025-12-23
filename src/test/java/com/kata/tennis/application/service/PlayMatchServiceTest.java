package com.kata.tennis.application.service;

import com.kata.tennis.application.port.out.ScorePresenter;
import com.kata.tennis.domain.model.Player;
import com.kata.tennis.domain.model.Score;
import com.kata.tennis.domain.service.TennisGame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayMatchServiceTest {

    @Test
    void play_publishes_each_score_and_stops_on_winner() {
        InMemoryPresenter presenter = new InMemoryPresenter();
        var service = new PlayMatchService(new TennisGame(), presenter);

        service.play("ABABAA");

        assertEquals(List.of(
                "Player A : 15 / Player B : 0",
                "Player A : 15 / Player B : 15",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 40 / Player B : 30",
                "Player A wins the game"
        ), presenter.lines);
    }

    private static final class InMemoryPresenter implements ScorePresenter {
        private final List<String> lines = new ArrayList<>();

        @Override
        public void onScore(String line) {
            lines.add(line);
        }
    }

    @Test
    void incomplete_sequence_does_not_crash() {
        var presenter = new InMemoryPresenter();
        var service = new PlayMatchService(new TennisGame(), presenter);

        service.play("AB");

        assertFalse(
                presenter.lines.get(presenter.lines.size() - 1)
                        .contains("wins the game")
        );
    }

    @Test
    void deuce_then_advantage_then_back_to_deuce() {
        var game = new TennisGame();
        var score = Score.initial();

        score = game.playBall(score, Player.A); // 15-0
        score = game.playBall(score, Player.A); // 30-0
        score = game.playBall(score, Player.A); // 40-0
        score = game.playBall(score, Player.B); // 40-15
        score = game.playBall(score, Player.B); // 40-30
        score = game.playBall(score, Player.B); // 40-40

        assertEquals("Player A : 40 / Player B : 40", game.formatLine(score));

        score = game.playBall(score, Player.A);
        assertEquals("Player A : 50 / Player B : 40", game.formatLine(score));

        score = game.playBall(score, Player.B);
        assertEquals("Player A : 40 / Player B : 40", game.formatLine(score));
    }
}
