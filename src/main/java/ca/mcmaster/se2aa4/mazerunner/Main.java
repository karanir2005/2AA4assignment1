package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption(Option.builder("i")
            .longOpt("input")
            .hasArg()
            .build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;


        try {
            cmd = parser.parse(options, args);
            String inputFilePath = cmd.getOptionValue("i");

            logger.info("**** Reading the maze from file " + inputFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            StringBuilder line = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        line.append("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        line.append("PASS ");
                    }
                }
                logger.trace(line.toString());
            }
            reader.close();
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
