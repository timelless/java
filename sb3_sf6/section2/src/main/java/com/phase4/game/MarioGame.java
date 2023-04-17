package com.phase4.game;

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
