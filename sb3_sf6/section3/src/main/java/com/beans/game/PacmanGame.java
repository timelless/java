package com.beans.game;

import org.springframework.stereotype.Component;

// @Component - this class will be managed by spring
@Component
public class PacmanGame implements GamingConsole {
    public void up() {
        System.out.println("Action: move up");
    }

    public void down() {
        System.out.println("Action: move down");
    }

    public void left() {
        System.out.println("Action: move left");
    }

    public void right() {
        System.out.println("Action: move right");
    }
}
