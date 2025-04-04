package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.mcmaster.se2aa4.mazerunner.CommandPattern.*;

public class PathSolver {

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private Player player;
    private Path path;

    public PathSolver(Maze maze, Player player, Path path) {
        this.maze = maze;
        this.player = player;
        this.path = path;
    }

    public void solve() {
        //computes the path for a simple maze
        boolean atEnd = false;
        CommandInvoker invoker = new CommandInvoker();

        while (!atEnd){
            char rightWall;
            char frontCell;

            if (player.getDirection().equalsIgnoreCase("up")){
                rightWall = maze.getCell(player.getRow(), player.getCol()+1);
                frontCell = maze.getCell(player.getRow()-1, player.getCol());
            }
            else if (player.getDirection().equalsIgnoreCase("right")){
                rightWall = maze.getCell(player.getRow()+1, player.getCol());
                frontCell = maze.getCell(player.getRow(), player.getCol()+1);
            }
            else if (player.getDirection().equalsIgnoreCase("down")){
                rightWall = maze.getCell(player.getRow(), player.getCol()-1);
                frontCell = maze.getCell(player.getRow()+1, player.getCol());
            }
            else{
                rightWall = maze.getCell(player.getRow()-1, player.getCol());
                frontCell = maze.getCell(player.getRow(), player.getCol()-1);
            }

            if (rightWall=='#'){
                if (frontCell == ' '){
                    invoker.executeCommand(new PlayerForwardCommand(player));
                    path.addMove("F");
                }
                else{
                    invoker.executeCommand(new PlayerLeftCommand(player));
                    path.addMove("L");
                }
            }
            else{
                invoker.executeCommand(new PlayerRightCommand(player));
                path.addMove("R");
                invoker.executeCommand(new PlayerForwardCommand(player));
                path.addMove("F");
            }

            if (player.getRow() == maze.getExitPoint()[0] && player.getCol() == maze.getExitPoint()[1]){
                atEnd = true;
                logger.info("Player has reached the end.");
            }
        }
    }    
}