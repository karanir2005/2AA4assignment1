package ca.mcmaster.se2aa4.mazerunner.CommandPattern;

import ca.mcmaster.se2aa4.mazerunner.Player;

public class PlayerForwardCommand extends Command {

    public PlayerForwardCommand(Player player) {
        super(player);
    }

    @Override
    public void execute() {
        player.moveForward();
    }
}
