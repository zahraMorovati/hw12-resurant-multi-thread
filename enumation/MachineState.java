package model.enumation;

public enum MachineState {

    MachineStarting, //wait for cook to call
    MachineStartingFood,  //receive the order
    MachineDoneFood,      //wait some time to finish delivery to cook
    MachineEnding;  //machine end.
}
