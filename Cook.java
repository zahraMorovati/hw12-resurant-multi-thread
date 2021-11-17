package model;

import model.enumation.CookState;


public class Cook implements Runnable{

    private String name;
    private CookState state=CookState.CookStarting;

    public Cook(String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CookState getState() {
        return state;
    }

    public void setState(CookState state) {
        this.state = state;
    }

    public void finishedWorking(){
        System.out.println("cook "+name+" finished working!");
    }

    @Override
    public String toString() {
        return "name= " + name;
    }


    @Override
    public void run() {
        System.out.println("cook "+name+" starting...");
        while (Restaurant.IsOpen){
            Order order = null;

            synchronized (Restaurant.orders){
                if(Restaurant.orders.size()!=0){
                    order=Restaurant.orders.get(0);
                    Restaurant.orders.remove(0);
                }
            }

            if(order!=null){
                System.out.println("cook "+name+" received order"+order.getOrderNum()+" "+order.getFoodList().toString());
                Thread[] foodThreads = new Thread[order.getFoodList().size()];

                for (int i = 0; i < foodThreads.length; i++) {
                    if (order.getFoodList().get(i).getName().equals("sweats")) {
                        foodThreads[i] = Restaurant.machines.get(0).makeFood();
                    } else if (order.getFoodList().get(i).getName().equals("sandwich")) {
                        foodThreads[i] = Restaurant.machines.get(1).makeFood();
                    } else if (order.getFoodList().get(i).getName().equals("pizza")) {
                        foodThreads[i] = Restaurant.machines.get(2).makeFood();
                    } else if (order.getFoodList().get(i).getName().equals("pasta")) {
                        foodThreads[i] = Restaurant.machines.get(3).makeFood();
                    }
                    foodThreads[i].start();
                    System.out.println("cook "+name+" started food with order"+order.getOrderNum()+" "+order.getFoodList().toString());
                }
                for (int i = 0; i < foodThreads.length; i++) {
                    try {
                        foodThreads[i].join();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("cook "+name+" finished food with order"+order.getOrderNum()+" "+order.getFoodList().toString());
                }

                synchronized(order) {
                    System.out.println("cook "+name+" completed order"+order.getOrderNum()+" "+order.getFoodList().toString());
                    order.notifyAll();
                }

            }
        }
    }
}
