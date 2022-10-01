package com.company;

public class Waiter implements Runnable {
    private final Restaurant restaurant;
    private final String prophesy;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.prophesy = "waiter";
    }

    public void goInRestaurant() {
        restaurant.enterInRestaurant(Thread.currentThread(), prophesy);
    }

    @Override
    public void run() {
        goInRestaurant();
        while (restaurant.isWork()) {
        synchronized (restaurant) {

                try {
                    while (restaurant.getOrdersQueue().isEmpty()) {
                        restaurant.wait();
                    }
                    restaurant.takeOrderToCooker(Thread.currentThread());
                    restaurant.notify();
                    while (restaurant.getCompletedOrdersQueue().isEmpty()) {
                        restaurant.wait();
                    }
                    System.out.println("Официант" +  restaurant.getCompletedOrdersQueue().remove(0).getName() + " несет заказ");

                    restaurant.takeOrderToVisitor(Thread.currentThread());
                    restaurant.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
