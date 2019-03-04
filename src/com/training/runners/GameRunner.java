package com.training.runners;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;
import com.softwire.dynamite.game.Round;
import com.training.Config;
import com.training.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRunner {

    private Config config;
    private Pair<Bot> bots;
    private Pair<Integer> scores;
    private Pair<Integer> remainingDynamites;
    private Pair<Gamestate> gamestates;
    private int nextRoundScore;

    public GameRunner(Config configuration, Bot firstBot, Bot secondBot) {
        config = configuration;
        bots = new Pair<>(firstBot, secondBot);
        scores = new Pair<>(0, 0);
        remainingDynamites = new Pair<>(config.getMaxDynamite(), config.getMaxDynamite());
        nextRoundScore = 1;

        Gamestate firstGamestate = new Gamestate();
        firstGamestate.setRounds(new ArrayList<>());
        Gamestate secondgamestate = new Gamestate();
        secondgamestate.setRounds(new ArrayList<>());
        gamestates = new Pair<>(firstGamestate, secondgamestate);
    }

    public Optional<GameResult> playRound() {

        if (config.getScoreToWin() <= Math.max(scores.getFirst(), scores.getSecond())) {
            return Optional.of(getResult("Score limit reached", null));
        }

        if (gamestates.getFirst().getRounds().size() >= config.getRoundLimit()) {
            return Optional.of(getResult("Round limit reached", null));
        }

        Move firstMove;
        Move secondMove;

        try {
            firstMove = bots.getFirst().makeMove(gamestates.getFirst());
        } catch (Exception e) {
            return Optional.of(getResult("Exception thrown", new GameError(
                    bots.getFirst(), "Exception thrown", e
            )));
        }

        try {
            secondMove = bots.getSecond().makeMove(gamestates.getSecond());
        } catch (Exception e) {
            return Optional.of(getResult("Exception thrown", new GameError(
                    bots.getSecond(), "Exception thrown", e
            )));
        }

        System.out.format("%s plays %s\n", getBotName(bots.getFirst()), firstMove);
        System.out.format("%s plays %s\n", getBotName(bots.getSecond()), secondMove);

        try {
            updateGamestates(firstMove, secondMove);
            updateDynamites(firstMove, secondMove);
            updateScores(firstMove, secondMove);
        } catch (GameError e) {
            return Optional.of(getResult("Error", e));
        }

        System.out.format("Current score: %s %d - %d %s\n",
                getBotName(bots.getFirst()), scores.getFirst(),
                scores.getSecond(), getBotName(bots.getSecond()));

        return Optional.empty();
    }

    private void updateGamestates(Move firstMove, Move secondMove) throws GameError {
        if (firstMove == null) {
            throw new GameError(bots.getFirst(), "Invalid move", null);
        }
        if (secondMove == null) {
            throw new GameError(bots.getSecond(), "Invalid move", null);
        }

        addMovesToGamestate(gamestates.getFirst(), firstMove, secondMove);
        addMovesToGamestate(gamestates.getSecond(), secondMove, firstMove);
    }

    private void updateDynamites(Move firstMove, Move secondMove) throws GameError {
        if (firstMove == Move.D) {
            remainingDynamites.setFirst(remainingDynamites.getFirst() - 1);
            if (remainingDynamites.getFirst() < 0) {
                throw new GameError(bots.getFirst(), "Too many dynamites", null);
            }
        }
        if (secondMove == Move.D) {
            remainingDynamites.setSecond(remainingDynamites.getSecond() - 1);
            if (remainingDynamites.getSecond() < 0) {
                throw new GameError(bots.getSecond(), "Too many dynamites", null);
            }
        }
    }

    private void updateScores(Move firstMove, Move secondMove) {
        if (firstMove == secondMove) {
            nextRoundScore += 1;
            return;
        }

        if ((firstMove == Move.D && secondMove != Move.W) ||
                (firstMove == Move.W && secondMove == Move.D) ||
                (firstMove == Move.R && secondMove == Move.S) ||
                (firstMove == Move.S && secondMove == Move.P) ||
                (firstMove == Move.P && secondMove == Move.R) ||
                (firstMove != Move.D && secondMove == Move.W)) {
            scores.setFirst(scores.getFirst() + nextRoundScore);
        } else {
            scores.setSecond(scores.getSecond() + nextRoundScore);
        }

        nextRoundScore = 1;
    }

    private void addMovesToGamestate(Gamestate state, Move firstMove, Move secondMove) {
        Round round = new Round(); round.setP1(firstMove); round.setP2(secondMove);
        List<Round> rounds = state.getRounds();
        rounds.add(round);
    }


    private GameResult getResult(String reason, GameError error) {
        return new GameResult(
                (error == null) ? getResultSummary() : getWinningSummary(getOtherBot(error.getErrorBot())),
                scores,
                (error == null) ? reason : error.getErrorReason(),
                error
        );
    }

    private String getResultSummary() {
        if (scores.getFirst() >= config.getScoreToWin()) {
            return getWinningSummary(bots.getFirst());
        } else if (scores.getSecond() >= config.getScoreToWin()) {
            return getWinningSummary(bots.getSecond());
        } else {
            return "It's a draw";
        }
    }

    private String getWinningSummary(Bot bot) {
        return getBotName(bot) + " wins";
    }

    private String getBotName(Bot bot) {
        return bot.getClass().getSimpleName();
    }

    private Bot getOtherBot(Bot bot) {
        return (bots.getSecond() == bot) ? bots.getFirst() : bots.getSecond();
    }
}
