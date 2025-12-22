package com.kata.tennis;

import com.kata.tennis.adapters.out.console.ConsoleScorePresenter;
import com.kata.tennis.application.port.in.PlayMatchUseCase;
import com.kata.tennis.application.service.PlayMatchService;
import com.kata.tennis.domain.service.TennisGame;

public final class TennisScoreComputer {

    private TennisScoreComputer() {}

    public static void computeScore(String input) {
        var presenter = new ConsoleScorePresenter();
        var game = new TennisGame();
        PlayMatchUseCase useCase = new PlayMatchService(game, presenter);

        useCase.play(input);
    }
}
