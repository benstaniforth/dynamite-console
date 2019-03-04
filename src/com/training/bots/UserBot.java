package com.training.bots;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;

import java.util.Scanner;

public class UserBot implements Bot {

    @Override
    public Move makeMove(Gamestate gamestate) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your move: ");
        switch (scanner.next()) {
            case "R": return Move.R;
            case "P": return Move.P;
            case "S": return Move.S;
            case "W": return Move.W;
            case "D": return Move.D;
            default:  return null;
        }
    }
}
