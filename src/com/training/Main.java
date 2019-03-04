package com.training;

import com.training.bots.ExceptionBot;
import com.training.bots.RockBot;
import com.training.bots.UserBot;
import com.training.runners.GameResult;
import com.training.runners.GameRunner;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Config gameConfig = new Config(1000, 100, 2500);
        GameRunner runner = new GameRunner(gameConfig, new UserBot(), new RockBot());
        Optional<GameResult> result;

        do {
            result = runner.playRound();
        } while (!result.isPresent());

        GameResult gameResult = result.get();
        System.out.println(gameResult.getResultSummary());
        System.out.println("Reason: " + gameResult.getReason());
        if (gameResult.getError() != null && gameResult.getError().getThrowable() != null) {
            gameResult.getError().getThrowable().printStackTrace();
        }
    }
}
