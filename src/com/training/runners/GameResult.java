package com.training.runners;

import com.training.util.Pair;

public class GameResult {

    private String resultSummary;
    private Pair<Integer> scores;
    private String reason;
    private GameError error;

    public GameResult(String resultSummary, Pair<Integer> scores, String reason, GameError error) {
        this.resultSummary = resultSummary;
        this.scores = scores;
        this.reason = reason;
        this.error = error;
    }

    public String getResultSummary() {
        return resultSummary;
    }

    public Pair<Integer> getScores() {
        return scores;
    }

    public String getReason() {
        return reason;
    }

    public GameError getError() {
        return error;
    }
}
