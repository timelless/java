package com.vktest.vktest;

import com.vktest.vktest.game.GameRunner;
import com.vktest.vktest.game.MarioGame;
import com.vktest.vktest.game.PacmanGame;
import com.vktest.vktest.game.SuperContra;


public class App01GamingBasicJava {
    public static void main(String[] args) {
        var marioGame = new MarioGame();
        var superContraGame = new SuperContra();
        var pacmanGame = new PacmanGame();

        var gameRunner1 = new GameRunner(marioGame);
        var gameRunner2 = new GameRunner(superContraGame);
        var gameRunner3 = new GameRunner(pacmanGame);

        gameRunner1.run();
        gameRunner2.run();
        gameRunner3.run();
    }
}
