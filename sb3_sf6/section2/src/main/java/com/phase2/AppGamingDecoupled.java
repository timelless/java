package com.phase2;


import com.phase2.game.GameRunner;
import com.phase2.game.MarioGame;
import com.phase2.game.PacmanGame;
import com.phase2.game.SuperContraGame;

public class AppGamingDecoupled {
    public static void main(String[] args) {
        var superContra = new SuperContraGame();
        var gameRunner = new GameRunner(superContra);

        var mario = new MarioGame();
        var gameRunner2 = new GameRunner(mario);

        var pacman = new PacmanGame();
        var gameRunner3 = new GameRunner(pacman);

        gameRunner.run();
        gameRunner2.run();
        gameRunner3.run();
    }
}
