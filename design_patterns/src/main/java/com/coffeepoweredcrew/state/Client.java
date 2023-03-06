package com.coffeepoweredcrew.state;

public class Client {

    public static void main(String[] args) {
        System.out.println("STATE OUTPUT");

        Order order = new Order();
        order.cancel();

        Order order2 = new Order();
        order2.paymentSuccessful();
        order2.cancel();
    }
}
