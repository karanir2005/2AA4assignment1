package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPathSolver {

    private Maze maze;
    private Player player;
    private Path path;
    private PathSolver solver;

    @BeforeEach
    void setUp() throws Exception {
        maze = new Maze("examples/direct.maz.txt");
        int[] entryPoint = maze.getEntryPoint();
        player = new Player(entryPoint[0], entryPoint[1]);
        path = new Path();
        solver = new PathSolver(maze, player, path);
    }

    @Test
    void testSolve() {
        solver.solve();
        assertEquals("F R FF L FFF R F L F R F L FF", path.getCanonicalPath()); // Adjust based on the actual solution
    }
}
