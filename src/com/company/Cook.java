package com.company;

public class Cook implements Runnable {
    private final Restaurant restaurant;
    private final String prophesy;
    private final int cookingTime = 2000;

    public Cook(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.prophesy = "Cook";
    }


    public void goInRestaurant() {
        restaurant.enterInRestaurant(Thread.currentThread(), prophesy);
    }

    @Override
    public void run() {
        goInRestaurant();
        try {
            while (restaurant.isWork()) {
            synchronized (restaurant) {

                    while (restaurant.getCookingQueue().isEmpty()) {
                        restaurant.wait();
                    }
                    //restaurant.getCookingQueue().remove(0);
                    restaurant.notify();
                    Thread whiterThread = restaurant.getCookingQueue().remove(0);
                    restaurant.notify();
                    System.out.println("Повар готовит блюдо");
                    Thread.sleep(cookingTime);
                    restaurant.takeOrderToWaiter(whiterThread);
                    System.out.println("Повар закончил готовить");
                    restaurant.notify();
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
