package model;

import model.enumation.RestaurantState;

public class Customer implements Runnable{

    private String name;
    private Order order;

    public Customer(String name, Order order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        System.out.println("customer "+name+" starting ...");
        try {
            synchronized (Restaurant.state){
                if(Restaurant.state.equals(RestaurantState.OPEN)){
                    System.out.println("customer "+name+" entered.");
                    synchronized(Restaurant.orders) {
                        System.out.println("customer "+name+" placed order.");
                        Restaurant.orders.add(this.order);
                    }
                    synchronized(this.order) {
                        this.order.wait();
                        System.out.println("customer "+name+" received order.");
                        Thread.sleep(100);
                        System.out.println("customer "+name+" is leaving!");

                    }
                }else {
                    System.out.println("customer "+name+" waiting for empty seat!");
                    wait();
                }
            }
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
    }
}
