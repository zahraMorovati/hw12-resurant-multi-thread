package model.enumation;

public enum CustomerState {

    CustomerStarting,  //customer thread starting
    CustomerEntered,   //customer come in to restaurant if restaurant state is not full and close
    CustomerPlacedOrder,  //submit thr order to Cook ,if no cook available wait
    CustomerReceivedOrder,  //wait for cook notify that the order is done
    CustomerLeaving;  //wait some time to finish food and leave the restaurant ---> thread is terminated

}
