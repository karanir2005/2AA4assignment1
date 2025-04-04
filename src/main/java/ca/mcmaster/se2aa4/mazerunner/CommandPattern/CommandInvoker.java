package ca.mcmaster.se2aa4.mazerunner.CommandPattern;

import java.util.Stack;

public class CommandInvoker {
    private final Stack<Command> history = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command); //use stack for LIFO behavior - helps with undo
    }

    //public void undoCommand() {} //for future implementation

    public Stack<Command> getHistory() {
        return history;
    }
}
