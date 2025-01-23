package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
//import java.io.BufferedReader;
//import java.io.File;
import java.util.*;
//import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main { //main class used to run/manage the game.

    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Starting Maze Runner");

        Options options = new Options();
        options.addOption(Option.builder("i")
            .longOpt("input")
            .hasArg()
            .desc("Path to the maze file")
            .build());

        options.addOption(Option.builder("p")
            .longOpt("path")
            .hasArg()
            .desc("Path sequence to verify")
            .build());

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            String inputFilePath = cmd.getOptionValue("i");//saves file path value.
            String pathSequence = cmd.getOptionValue("p");

            Maze maze = new Maze(inputFilePath); //parses file to a 2D array in constructor.
            Player player = new Player(maze.getEntryPoint()[0], maze.getEntryPoint()[1]);
            Path path = new Path();

            if (pathSequence != null) {
                logger.info("Verifying path.");
                boolean isValid = verifyPath(maze, player, pathSequence); //called only when -p is present.
                logger.info("Path validity: " + (isValid ? "VALID" : "INVALID"));
            } 
            else {
                logger.info("Computing Path...");
                solveMaze(maze, player, path);
                logger.info("Path: " + path.getCanonicalPath());
            }
        } 
        catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        logger.info("End of MazeRunner");
    }

    private static void solveMaze(Maze maze, Player player, Path path) {
        //process user inputs here
        //take user input and move the player on screen
        Scanner inp = new Scanner(System.in);
        String move;
        while (true){
            logger.info("Enter Move (q to quit): ");
            move = inp.nextLine();
            if (move.equalsIgnoreCase("F")){
                player.moveForward();
            }
            else if (move.equalsIgnoreCase("R")){
                player.turnRight();
            }
            else if (move.equalsIgnoreCase("L")){
                player.turnLeft();
            }
            else if (move.equalsIgnoreCase("q")){
                break;
            }
            else{
                logger.info("invalid input");
            }
        }
        inp.close();
    }
    
    private static boolean verifyPath(Maze maze, Player player, String pathSequence) { //check if the given path is valid
        String path = pathSequence.replace(" ", "");

        //uses REGEX to check if all characters in path are valid
        if (!path.matches("[FRL]*")) {
            return false;
        }
        
        for (char move : path.toCharArray()) { //conducts the sequence 
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
        boolean atEndPoint = player.getRow() == maze.getExitPoint()[0] && player.getCol() == maze.getExitPoint()[1];
        return atEndPoint;
    }

}
