package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMazeConstructor {
    private Maze maze;

    @BeforeEach
    void setUp() throws Exception {
        maze = new Maze("examples/direct.maz.txt");
    }

    @Test
    void testMazeElements() {
        assertEquals(7, maze.getHeight());
        assertEquals(8, maze.getWidth());
        assertEquals('#', maze.getCell(0, 0));
        assertEquals(' ', maze.getCell(1, 0));
    }    
}
