package com.training;

public class Config {

    private int scoreToWin;
    private int maxDynamite;
    private int roundLimit;

    public Config(int scoreToWin, int maxDynamite, int roundLimit) {
        this.scoreToWin = scoreToWin;
        this.maxDynamite = maxDynamite;
        this.roundLimit = roundLimit;
    }

    public int getScoreToWin() {
        return scoreToWin;
    }

    public int getMaxDynamite() {
        return maxDynamite;
    }

    public int getRoundLimit() {
        return roundLimit;
    }
}
