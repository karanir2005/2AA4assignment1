package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPathVerifier {

    private Maze maze;
    private Player player;
    private Path path;
    private PathVerifier verifier;

    @BeforeEach
    void setUp() throws Exception {
        maze = new Maze("examples/direct.maz.txt");
        int[] entryPoint = maze.getEntryPoint();
        player = new Player(entryPoint[0], entryPoint[1]);
        path = new Path();
        //verifier = new PathVerifier(maze, player, path, " ");
    }

    @Test
    void testVerifyCanonicalPath() {
        this.verifier = new PathVerifier(maze, player, path, 
                            "F R FF L FFF R F L F R F L FF");
        assertTrue(verifier.verifyPath());
    }

    @Test
    void testVerifyFactorizedPath() {
        this.verifier = new PathVerifier(maze, player, path, 
                            "F R 2F L 3F R F L F R F L 2F");
        assertTrue(verifier.verifyPath());
    }

    @Test
    void testVerifyReversePath() {
        this.verifier = new PathVerifier(maze, player, path, 
                            "FF R F L F R F L FFF R FF L F");
        assertTrue(verifier.verifyPath());
    }

    @Test
    void testVerifyInvalidPath() {
        this.verifier = new PathVerifier(maze, player, path, 
                            "FFFFF");
        assertFalse(verifier.verifyPath());
    }
}
