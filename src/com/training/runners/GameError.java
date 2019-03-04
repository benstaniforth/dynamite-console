package com.training.runners;

import com.softwire.dynamite.bot.Bot;

public class GameError extends Exception {

    private Bot errorBot;
    private String errorReason;
    private Throwable throwable;

    public GameError(Bot errorBot, String errorReason, Throwable throwable) {
        super(throwable);
        this.errorBot = errorBot;
        this.errorReason = errorReason;
        this.throwable = throwable;
    }

    public Bot getErrorBot() {
        return errorBot;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
