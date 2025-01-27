package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
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
            String pathSequence = cmd.getOptionValue("p"); //saves path in a string variable when -p is used.
            
            Maze maze = new Maze(inputFilePath); //parses file to a 2D array in constructor.
            Player player = new Player(maze.getEntryPoint()[0], maze.getEntryPoint()[1]);
            Path path = new Path();

            if (pathSequence != null) { //if -p is used.
                logger.info("Verifying path.");
                boolean isValid = verifyPath(maze, player, pathSequence); //called only when -p is present.
                logger.info("Path validity: " + (isValid ? "VALID" : "INVALID")); //logs path validity to logger.
            } 
            else {
                logger.info("Computing Path...");
                solveMaze(maze, player, path);
                logger.info("Path: " + path.getCanonicalPath()); //outputs final path.
                //logger.info("Path: " + path.getFactorizedPath());
            }
        } 
        catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            logger.error(e.getMessage());
        }

        logger.info("End of MazeRunner");
    }

    private static void solveMaze(Maze maze, Player player, Path path) {
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





/*
private static void solveMaze(Maze maze, Player player, Path path) {
    //computes the path for a simple maze
    
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
*/
