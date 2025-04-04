package ca.mcmaster.se2aa4.mazerunner.CommandPattern;
import ca.mcmaster.se2aa4.mazerunner.Player;

public class PlayerRightCommand extends Command {

    public PlayerRightCommand(Player player) {
        super(player);
    }

    @Override
    public void execute() {
        player.turnRight();
    }
}
