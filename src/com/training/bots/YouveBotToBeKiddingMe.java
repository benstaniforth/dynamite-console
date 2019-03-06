package com.training.bots;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;
import com.softwire.dynamite.game.Round;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class YouveBotToBeKiddingMe implements Bot {

    private int dynamitesRemaining;

    public YouveBotToBeKiddingMe() {
        this.dynamitesRemaining = 100;
    }


    @Override
    public Move makeMove(Gamestate gamestate) {

        List<Round> roundsPlayed = gamestate.getRounds();
        int numberOfRounds = roundsPlayed.size();

        if (gamestate.getRounds().isEmpty()){
            return Move.S;
        }

        List<Round> rounds = gamestate.getRounds();
        Round lastRound = rounds.get(rounds.size()-1);

        if ((numberOfRounds % 3 == 0) && (lastRound.getP2() == Move.S)){
            return Move.R;
        } else if ((numberOfRounds % 3 == 1) && (lastRound.getP2() == Move.P)){
            return Move.S;
        } else if ((numberOfRounds % 3 == 2) && (lastRound.getP2() == Move.R)){
            return Move.P;
        } else if ((numberOfRounds % 8 == 0) && (dynamitesRemaining > 0)){
            dynamitesRemaining--;
            return Move.D;
        }

        List<Move> moveList = new ArrayList<>(Arrays.asList(Move.R,Move.P, Move.S));
        return moveList.get(randomNumber(moveList.size()));

    }


    public static Integer randomNumber(int upperBound) {

        Random random = new Random();
        int rn = random.nextInt(upperBound);
        return rn;

    }

}
