package com.beans.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// @Primary - bean should be given preference when multiple beans are qualified
@Component
@Primary
public class MarioGame implements GamingConsole {
    public void up() {
        System.out.println("Action: jump");
    }

    public void down() {
        System.out.println("Action: go into a hole");
    }

    public void left() {
        System.out.println("Action: go back");
    }

    public void right() {
        System.out.println("Action: go forward");
    }
}
