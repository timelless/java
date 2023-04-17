package com.beans;

import com.beans.game.GameRunner;
import com.beans.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @ComponentScan - the package that will be scanned for components
@Configuration
@ComponentScan("com.beans.game")
public class AppGaming {
    /*
    @Bean
    public GamingConsole game() {
        var game = new PacmanGame();
        return game;
    }

    @Bean
    public GameRunner gameRunner(GamingConsole game) {
        var gameRunner = new GameRunner(game);
        return gameRunner;
    }
    */

    public static void main(String[] args) {
        try (
                var context = new AnnotationConfigApplicationContext(AppGaming.class);
        ) {
            context.getBean(GamingConsole.class).up();
            context.getBean(GameRunner.class).run();
        }
    }
}
