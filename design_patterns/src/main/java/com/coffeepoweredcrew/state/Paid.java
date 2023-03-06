package com.coffeepoweredcrew.state;

public class Paid implements OrderState {

    @Override
    public double handleCancellation() {
        System.out.println("Contacting payments to rollback the transaction");
        return 10;
    }
}
