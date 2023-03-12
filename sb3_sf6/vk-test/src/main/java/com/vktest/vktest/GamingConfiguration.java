package com.vktest.vktest;

import com.vktest.vktest.game.GameRunner;
import com.vktest.vktest.game.GamingConsole;
import com.vktest.vktest.game.PacmanGame;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GamingConfiguration {

    @Bean
    public GamingConsole game() {
        return new PacmanGame();
    }

    @Bean
    public GameRunner gameRunner(GamingConsole game) {
        return new GameRunner(game);
    }
}
