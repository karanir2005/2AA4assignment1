package ca.mcmaster.se2aa4.mazerunner.CommandPattern;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    private List<Command> history = new ArrayList<>();

    public void executeCommand(Command command) {
        command.execute();
        history.add(command); // For undo or logging in future
    }

    public List<Command> getHistory() {
        return history;
    }
}
