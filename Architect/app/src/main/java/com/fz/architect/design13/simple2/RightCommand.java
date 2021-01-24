package com.fz.architect.design13.simple2;

/**
 * Created by fz on 2017/10/29.
 */

public class RightCommand implements Command{
    private TetrisMachine machine;

    public RightCommand(TetrisMachine machine){
        this.machine = machine;
    }


    @Override
    public void execute() {
        machine.toRight();
    }
}
