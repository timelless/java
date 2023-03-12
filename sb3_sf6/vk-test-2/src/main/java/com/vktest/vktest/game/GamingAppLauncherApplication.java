package com.vktest.vktest.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//@Configuration
//class GamingConfiguration {
//
//    @Bean
//    public GamingConsole game() {
//        return new PacmanGame();
//    }
//
//    @Bean
//    public GameRunner gameRunner(GamingConsole game) {
//        return new GameRunner(game);
//    }
//}

@Configuration
@ComponentScan("com.vktest.vktest.game") // Loolup package for beans
public class GamingAppLauncherApplication {

    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class)) {
            context.getBean(GamingConsole.class).up();
            context.getBean(GameRunner.class).run();
        }
    }
}
