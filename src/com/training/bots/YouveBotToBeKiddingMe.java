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

        List<Move> moveList = new ArrayList<>(Arrays.asList(Move.R,Move.P,Move.S));

        List<Round> roundsPlayed = gamestate.getRounds();
        int numberOfRounds = roundsPlayed.size();

        if (numberOfRounds < 3){
            roundsPlayedSoFar++;
            return moveList.get(randomNumber(moveList.size()));
        }

        List<Round> rounds = gamestate.getRounds();
        Round lastRound = rounds.get(rounds.size()-1);
        Round twoRoundsAgo = rounds.get(rounds.size()-2);
        Round threeRoundsAgo = rounds.get(rounds.size()-3);


        if (((lastRound.getP1() == (Move.R)) && (lastRound.getP2() == Move.S))
            || ((lastRound.getP1() == (Move.P)) && (lastRound.getP2() == Move.R))
                || ((lastRound.getP1() == (Move.S)) && (lastRound.getP2() == Move.P))
                    || ((lastRound.getP1() == (Move.D)) && (lastRound.getP2() != Move.W) && (lastRound.getP2() != Move.D))){
            myScore++;
        }

        if (((lastRound.getP2() == (Move.R)) && (lastRound.getP1() == Move.S))
                || ((lastRound.getP2() == (Move.P)) && (lastRound.getP1() == Move.R))
                    || ((lastRound.getP2() == (Move.S)) && (lastRound.getP1() == Move.P))
                        || ((lastRound.getP2() == (Move.D)) && (lastRound.getP1() != Move.W) && (lastRound.getP1() != Move.D))){
            oppoScore++;
        }


        if ((lastRound.getP2() == Move.S) && (twoRoundsAgo.getP2() == Move.S) && (threeRoundsAgo.getP2() == Move.S)){
            roundsPlayedSoFar++;
            return Move.R;
        }

        if ((lastRound.getP2() == Move.R) && (twoRoundsAgo.getP2() == Move.R) && (threeRoundsAgo.getP2() == Move.R)){
            roundsPlayedSoFar++;
            return Move.P;
        }

        if ((lastRound.getP2() == Move.P) && (twoRoundsAgo.getP2() == Move.P) && (threeRoundsAgo.getP2() == Move.P)){
            roundsPlayedSoFar++;
            return Move.S;
        }


        if ((numberOfRounds % 8 == 0) && (dynamitesRemaining > 0)){
            dynamitesRemaining--;
            roundsPlayedSoFar++;
            return Move.D;
        } else if ((numberOfRounds % 3 == 0) && (lastRound.getP2() == Move.S)){
            roundsPlayedSoFar++;
            return Move.R;
        } else if ((numberOfRounds % 3 == 1) && (lastRound.getP2() == Move.P)){
            roundsPlayedSoFar++;
            return Move.S;
        } else if ((oppoScore - myScore) > 30){
            roundsPlayedSoFar++;
            return moveList.get(randomNumber(moveList.size()));
        } else if ((numberOfRounds % 3 == 2) && (lastRound.getP2() == Move.R)){
            roundsPlayedSoFar++;
            return Move.P;
        }


        return moveList.get(randomNumber(moveList.size()));

    }


    public static Integer randomNumber(int upperBound) {

        Random random = new Random();
        int rn = random.nextInt(upperBound);
        return rn;

    }

        private int myScore;
        private int roundsPlayedSoFar;
        private int oppoScore;

    public YouveBotToBeKiddingMe (int scoreTracker) {
        this.myScore = 0;
        this.roundsPlayedSoFar = 0;
        this.oppoScore = 0;



    }

}
