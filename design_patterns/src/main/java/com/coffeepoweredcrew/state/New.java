package com.coffeepoweredcrew.state;

public class New implements OrderState {

    @Override
    public double handleCancellation() {
        System.out.println("It is a new order, no processing done with 0 fee");
        return 0;
    }
}
