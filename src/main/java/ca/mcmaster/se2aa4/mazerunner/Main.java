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

            PathSolver solver = new PathSolver(maze, player, path);
            PathVerifier verifier = new PathVerifier(maze, player, path, pathSequence);

            if (pathSequence != null) { //if -p is used.
                logger.info("Verifying path.");
                boolean isValid = verifier.verifyPath(); //called only when -p is present.
                System.out.println("Path validity: " + (isValid ? "VALID" : "INVALID")); //logs path validity to logger.
            } 
            else {
                logger.info("Computing Path...");
                solver.solve();
                //logger.info("Canonical Path: " + path.getCanonicalPath()); //outputs final path in canonical.
                System.out.println("Factorized Path: " + path.getFactorizedPath()); //outputs factorized path.
            }
        } 
        catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            System.out.println(e.getMessage());
        }

        logger.info("End of MazeRunner");
    }
}