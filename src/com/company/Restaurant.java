package com.company;

import java.util.*;

public class Restaurant {
    private static final List<Thread> ordersQueue = new ArrayList<>();
    private static final List<Thread> cookingQueue = new ArrayList<>();
    private static final List<Thread> completedOrdersQueue = new ArrayList<>();
    private static final List<Thread> ordersToVisited = new ArrayList<>();
    private final int orderLimit;
    private int totalOrders = 0;


    public Restaurant(int ordersLimit) {
        this.orderLimit = ordersLimit;
    }

    public boolean isWork() {
        return totalOrders <= orderLimit;
    }

    public List<Thread> getOrdersQueue() {
        return ordersQueue;
    }

    public void enterInRestaurant(Thread thread, String prophesy) {
        String worker = prophesy.toLowerCase(Locale.ROOT);
        if (worker.equals("cook")) {
            cookInJob(thread);
        } else if (worker.equals("waiter")) {
            waiterInJob(thread);
        } else if (worker.equals("visitor")) {
            visitorInRestaurant(thread);
        }
    }

    private void visitorInRestaurant(Thread thread) {
        System.out.println("Посетитель" + thread.getName() + " в ресторане");
    }

    private void waiterInJob(Thread thread) {
        System.out.println("Официант" + thread.getName() + " на работе!");
    }

    private void cookInJob(Thread thread) {
        System.out.println("Повар на работе!");

    }

    public void takeOrderToCooker(Thread thread) {
        if (!ordersQueue.isEmpty()) {
            ordersQueue.remove(0);
            System.out.println("Официант" + thread.getName() + " взял заказ");
            cookingQueue.add(thread);
        }

    }

    public void makeOrder(Thread thread) {
        ordersQueue.add(thread);
    }


    public List<Thread> getCookingQueue() {
        return cookingQueue;
    }

    public List<Thread> getCompletedOrdersQueue() {
        return completedOrdersQueue;
    }
    public List<Thread> getOrderToVisitor() {
        return ordersToVisited;
    }

    public void takeOrderToWaiter(Thread whiterThread) {
        completedOrdersQueue.add(whiterThread);
    }

    public void takeOrderToVisitor(Thread thread) {
        ordersToVisited.add(thread);
    }
    public void visitorExit() {
        totalOrders++;
    }
}
