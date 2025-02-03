package ca.mcmaster.se2aa4.mazerunner;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class PathVerifier {
    //private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private Player player;
    private Path path;
    private String pathSequence;

    public PathVerifier(Maze maze, Player player, Path path, String pathSequence) {
        this.maze = maze;
        this.player = player;
        this.path = path;
        this.pathSequence = pathSequence;
    }
    
    public boolean verifyPath() { //check if the given path is valid
        pathSequence = pathSequence.replace(" ", "");

        //uses REGEX to check if all characters in path are valid
        if (!pathSequence.matches("[FRL0-9]*")) {
            throw new IllegalArgumentException("Invalid path format: Only have 'F', 'R', 'L', 0-9, and spaces.");
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
                return checkOppositePath(); //check the reverse path incase the user had entered the reverse path. returns true if reverse path is valid, false if not.
            } 
        }

        //checks if the player's final position is the same as maze endpoint.
        boolean atEndPoint = player.getRow() == maze.getExitPoint()[0] && player.getCol() == maze.getExitPoint()[1];
        return atEndPoint;
    }

    private boolean checkOppositePath() { //check if the given path is valid

        player.resetPosition(maze.getExitPoint()[0], maze.getExitPoint()[1], "left");
        
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