package com.beans.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// @Qualifier - may be used on field or parameter as a qualifier for candidate beans when autowiring
// has higher priority than @Primary
@Component
@Qualifier("SuperContraGameQualifier")
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
