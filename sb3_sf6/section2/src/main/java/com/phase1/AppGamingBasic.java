package com.phase1;

import com.phase1.game.GameRunner;
import com.phase1.game.SuperContraGame;

public class AppGamingBasic {
    public static void main(String[] args) {
        var superContra = new SuperContraGame();
        var gameRunner = new GameRunner(superContra);

        gameRunner.run();
    }
}
