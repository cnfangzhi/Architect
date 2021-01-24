package com.fz.architect.design13.simple2;

/**
 * Created by fz on 2017/10/29.
 */

public class Buttons {
    LeftCommand leftCommand;

    RightCommand rightCommand;


    public void setLeftCommand(LeftCommand leftCommand) {
        this.leftCommand = leftCommand;
    }

    public void setRightCommand(RightCommand rightCommand) {
        this.rightCommand = rightCommand;
    }

    public void toLeft(){
        leftCommand.execute();
    }

    public void toRight(){
        rightCommand.execute();
    }
}
