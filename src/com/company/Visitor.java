package com.company;

public class Visitor implements Runnable {
    private final Restaurant restaurant;
    private final String prophesy;
    private final int thinkTime = 1500;
    private final int eatTime = 2500;

    public Visitor(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.prophesy = "visitor";

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
                    Thread.sleep(thinkTime);
                    restaurant.makeOrder(Thread.currentThread());
                    restaurant.notify();
                    while (restaurant.getCompletedOrdersQueue().isEmpty()) {
                        restaurant.wait();
                    }
                    restaurant.getCompletedOrdersQueue().remove(0);
                    restaurant.notify();
                    while (restaurant.getOrderToVisitor().isEmpty()) {
                        restaurant.wait();
                    }
                    restaurant.getOrderToVisitor().remove(0);
                    restaurant.notify();
                    System.out.println("Посетитель" + Thread.currentThread().getName() + " приступил к еде");
                    Thread.sleep(eatTime);
                    System.out.println("Посетитель" + Thread.currentThread().getName() + " вышел из ресторана");
                    restaurant.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        restaurant.notifyAll();
    }
}
