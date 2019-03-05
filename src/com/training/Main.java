package com.training;

import com.training.bots.RandoBot;
import com.training.bots.RockBot;
import com.training.bots.ScissorsBot;
import com.training.bots.UserBot;
import com.training.runners.GameResult;
import com.training.runners.GameRunner;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        /////////// EDIT THIS CODE TO CHANGE THE RULES OF THE GAME AND TO CHANGE WHICH BOTS ARE USED /////////////

        Config gameConfig = new Config(1000, 100, 2500);
        GameRunner runner = new GameRunner(gameConfig, new RandoBot(), new RockBot());

        /////////// EDIT THIS CODE TO CHANGE THE RULES OF THE GAME AND TO CHANGE WHICH BOTS ARE USED /////////////

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
