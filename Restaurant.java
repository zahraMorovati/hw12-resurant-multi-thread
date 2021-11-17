package model;
import model.enumation.RestaurantState;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    public static List<Customer> customerList=new ArrayList<>();
    public static List<Cook> cooks=new ArrayList<>();
    public static List<Machine> machines=new ArrayList<>();
    public static List<Order> orders=new ArrayList<>();
    public static int capacity=10; //10 customer
    public static boolean IsOpen =true;

    public static RestaurantState state = RestaurantState.OPEN;


    public static RestaurantState getState(){

        if(IsOpen){
            if(capacity>customerList.size())
                return RestaurantState.OPEN;
            else return RestaurantState.FULL;
        }else return RestaurantState.CLOSE;
    }

    public static void main(String[] args) {
        machines.add(new Machine("machine1",FoodType.sweats));
        machines.add(new Machine("machine2",FoodType.sandwich));
        machines.add(new Machine("machine3",FoodType.pasta));
        machines.add(new Machine("machine4",FoodType.pizza));

        for (Machine machine : machines) {
            machine.startMachine();
        }

        List<Food> foodList=new ArrayList<>();
        foodList.add(FoodType.pasta);
        foodList.add(FoodType.sandwich);

        Order order=new Order(1,foodList);
        customerList.add(new Customer("ali",order));

        cooks.add(new Cook("cook1"));
        cooks.add(new Cook("cook2"));
        cooks.add(new Cook("cook3"));
        cooks.add(new Cook("cook4"));

        List<Thread> customerThreads=new ArrayList<>();
        for (Customer customer : customerList) {
            customerThreads.add(new Thread(customer));
        }

        for (Thread customerThread : customerThreads) {
            customerThread.start();
        }


        List<Thread> cooksThreads=new ArrayList<>();
        for (Cook cook : cooks) {
            cooksThreads.add(new Thread(cook));
        }

        for (Thread cooksThread : cooksThreads) {
            cooksThread.start();
        }

        for (Thread customerThread : customerThreads) {
            try {
                customerThread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        Restaurant.state=RestaurantState.CLOSE;

        for (Cook cook : cooks) {
            cook.finishedWorking();
        }

        for (Thread cooksThread : cooksThreads) {
            cooksThread.stop();
        }

        for (Thread customerThread : customerThreads) {
            customerThread.stop();
        }

        for (Machine machine : machines) {
            machine.stopMachine();
        }

        System.out.println("restaurant closed!");
    }


}
