package com.phase4.game;

public class SuperContraGame implements GamingConsole {
    public void up() {
        System.out.println("Action: up");
    }

    public void down() {
        System.out.println("Action: sit down");
    }

    public void left() {
        System.out.println("Action: go back");
    }

    public void right() {
        System.out.println("Action: shoot a bullet");
    }
}
