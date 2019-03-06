package com.training.bots;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;
import com.softwire.dynamite.game.Round;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        return Move.R;
    }
    

}
