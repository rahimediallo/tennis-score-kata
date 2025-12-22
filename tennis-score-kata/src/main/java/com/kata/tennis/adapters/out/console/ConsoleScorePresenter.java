package com.kata.tennis.adapters.out.console;

import com.kata.tennis.application.port.out.ScorePresenter;

public final class ConsoleScorePresenter implements ScorePresenter {
    @Override
    public void onScore(String line) {
        System.out.println(line);
    }
}
