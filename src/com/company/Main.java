package com.company;

public class Main {

    private static final int totalVisitors = 5, totalWaiters = 3, ordersLimit = 2;
    private static final Restaurant restaurant = new Restaurant(ordersLimit);


    public static void main(String[] args) {
        new Thread(
                null,
                new Cook(restaurant),
                "1")
                .start();
        for (int i = 0; i < totalVisitors; i++) {
            new Thread(
                    null,
                    new Visitor(restaurant),
                    i + "")
                    .start();
        }
        for (int i = 0; i < totalWaiters; i++) {
            new Thread(
                    null,
                    new Waiter(restaurant),
                    i + "")
                    .start();
        }
    }
}