package com.training.bots;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EagerBot implements Bot {

    private int dynamitesRemaining;

    public EagerBot() {
        this.dynamitesRemaining = 100;
    }

    @Override
    public Move makeMove(Gamestate gamestate) {

        List<Move> moveList = new ArrayList<>(Arrays.asList(Move.R, Move.P, Move.S));

        if (dynamitesRemaining > 0){
            dynamitesRemaining--;
            return Move.D;
        } else {
            return moveList.get(randomNumber(moveList.size()));
        }

    }

    public static Integer randomNumber(int upperBound) {

        Random random = new Random();
        int rn = random.nextInt(upperBound);
        return rn;

    }
}
