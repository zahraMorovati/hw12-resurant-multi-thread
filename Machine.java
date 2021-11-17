package model;

import model.enumation.MachineState;

public class Machine  {

    private String name;
    private Food foodType;
    private int capacity;
    private boolean full;
    private MachineState state;

    public Machine(String name,Food foodType) {
        this.name = name;
        this.foodType=foodType;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public void startMachine() {
        System.out.println("machine "+name+" start ...");
    }


    public void stopMachine() {
        System.out.println("machine "+name+" stop working!");
    }

    public Thread makeFood(){

        Runnable runnable= () -> {
            full=true;
            System.out.println("machine "+name+" starting ...");
            System.out.println("machine "+name+" MachineStartingFood ...");
            try {
                Thread.sleep(foodType.getCookTime());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("machine "+name+" MachineDoneFood ...");
            full=false;
        };

        return new Thread(runnable);
    }
}
