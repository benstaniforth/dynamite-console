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


    @Override
    public Move makeMove(Gamestate gamestate) {

        List<Move> moveList = new ArrayList<>(Arrays.asList(Move.R, Move.P, Move.S));

//        if (gamestate.getRounds().isEmpty()){
//            return Move.S;
//        }
//
//        List<Round> rounds = gamestate.getRounds();
//        Round lastRound = rounds.get(rounds.size()-1);

        



        return null;
    }


    private int dynamitesRemaining;

    public YouveBotToBeKiddingMe() {
        this.dynamitesRemaining = 100;
    }


}
