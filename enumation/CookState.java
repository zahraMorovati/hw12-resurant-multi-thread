package model.enumation;

public enum CookState {

    CookStarting,             //cook start
    CookReceivedOrder,        //receiving order
    CookStartedFood,          //put the food into machine thread , if machine is full wait
    CookFinishedFood,         //means all machine finished food
    CookCompletedOrder;       //cook completed the order and notify customer that order finished ---> customerState=CustomerReceivedOrder
}
