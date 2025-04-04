package ca.mcmaster.se2aa4.mazerunner.CommandPattern;
import ca.mcmaster.se2aa4.mazerunner.Player;

public class PlayerLeftCommand extends Command {

    public PlayerLeftCommand(Player player) {
        super(player);
    }

    @Override
    public void execute() {
        player.turnLeft();
    }
}
