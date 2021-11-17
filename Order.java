package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderNum;
    private List<Food> foodList=new ArrayList<>();

    public Order(int orderNum, List<Food> foodList) {
        this.orderNum = orderNum;
        this.foodList = foodList;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
