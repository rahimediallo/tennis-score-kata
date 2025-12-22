package com.kata.tennis.adapters.in.console;

import com.kata.tennis.adapters.out.console.ConsoleScorePresenter;
import com.kata.tennis.application.port.in.PlayMatchUseCase;
import com.kata.tennis.application.service.PlayMatchService;
import com.kata.tennis.domain.service.TennisGame;

public final class ConsoleMain {

    public static void main(String[] args) {
        String input = args.length > 0 ? args[0] : "ABABAA";

        var presenter = new ConsoleScorePresenter();
        var game = new TennisGame();
        PlayMatchUseCase useCase = new PlayMatchService(game, presenter);

        useCase.play(input);
    }
}
