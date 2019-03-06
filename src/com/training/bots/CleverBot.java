package com.training.bots;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;
import com.softwire.dynamite.game.Round;

import java.util.List;

public class CleverBot implements Bot {

    @Override
    public Move makeMove(Gamestate gamestate) {


        if (gamestate.getRounds().isEmpty()){
            return Move.S;
        }

        List<Round> rounds = gamestate.getRounds();
        Round lastRound = rounds.get(rounds.size()-1);

        switch (lastRound.getP2()) {
            case R:
                return Move.P;
            case P:
                return Move.S;
            case S:
                return Move.R;
            case D:
                return Move.W;
            case W:
                return Move.R;
            default:
                return null;
        }

    }

}
