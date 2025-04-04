package ca.mcmaster.se2aa4.mazerunner.CommandPattern;

import ca.mcmaster.se2aa4.mazerunner.Player;

public abstract class Command {
    protected Player player;

    public Command(Player player) {
        this.player = player;
    }

    public abstract void execute();
    //public abstract void undo(); //for furture impelementation
}
