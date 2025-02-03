package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private Player player;
    private Path path;

    public Game(Maze maze, Player player, Path path) {
        this.maze = maze;
        this.player = player;
        this.path = path;
    }

    public void solveMaze(Maze maze, Player player, Path path) {
        //computes the path for a simple maze
        
        boolean atEnd = false;

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
                    player.moveForward();
                    path.addMove("F");
                }
                else{
                    player.turnLeft();
                    path.addMove("L");
                }
            }
            else{
                player.turnRight();
                player.moveForward();
                path.addMove("R");
                path.addMove("F");
            }
            
            if (player.getRow() == maze.getExitPoint()[0] && player.getCol() == maze.getExitPoint()[1]){
                atEnd = true;
                logger.info("Player has reached the end.");
            }
        }
    }
    
    public boolean verifyPath(Maze maze, Player player, String pathSequence, Path path) { //check if the given path is valid
        pathSequence = pathSequence.replace(" ", "");

        //uses REGEX to check if all characters in path are valid
        if (!pathSequence.matches("[FRL0-9]*")) {
            return false;
        }

        //use regex to check for factorized form and convert to canonical
        if (pathSequence.matches(".*[0-9].*")){
            pathSequence = path.expandFactorized(pathSequence);
        }
        
        for (char move : pathSequence.toCharArray()) { //conducts the sequence 
            if (move=='F'){
                player.moveForward();
            }
            else if (move=='R'){
                player.turnRight();
            }
            else if (move=='L'){
                player.turnLeft();
            }
            
            // returns false right away if wall is encountered.
            if (maze.isWall(player.getRow(), player.getCol())) {
                return checkOppositePath(maze, player, pathSequence); //check the reverse path incase the user had entered the reverse path. returns true if reverse path is valid, false if not.
            } 
        }

        //checks if the player's final position is the same as maze endpoint.
        boolean atEndPoint = player.getRow() == maze.getExitPoint()[0] && player.getCol() == maze.getExitPoint()[1];
        return atEndPoint;
    }

    private boolean checkOppositePath(Maze maze, Player player, String pathSequence) { //check if the given path is valid

        player.setDirection("left");
        player.setPosition(maze.getExitPoint()[0], maze.getExitPoint()[1]);
        
        for (char move : pathSequence.toCharArray()) { //conducts the sequence 
            if (move=='F'){
                player.moveForward();
            }
            else if (move=='R'){
                player.turnRight();
            }
            else if (move=='L'){
                player.turnLeft();
            }
            
            // returns false right away if wall is encountered.
            if (maze.isWall(player.getRow(), player.getCol())) {
                return false;
            } 
        }

        //checks if the player's final position is the same as maze endpoint.
        boolean atEndPoint = player.getRow() == maze.getEntryPoint()[0] && player.getCol() == maze.getEntryPoint()[1];
        return atEndPoint;
    }
}