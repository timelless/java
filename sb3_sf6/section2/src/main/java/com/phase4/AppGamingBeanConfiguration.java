package com.phase4;


import com.phase4.game.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppGamingBeanConfiguration {
    public static void main(String[] args) {
        try (
            var context = new AnnotationConfigApplicationContext(GamingConfiguration.class);
        ) {
            context.getBean(GamingConsole.class).up();
            context.getBean(GameRunner.class).run();
        }
    }
}
