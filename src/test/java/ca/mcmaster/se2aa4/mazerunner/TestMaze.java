package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMaze {

    private Maze maze;

    @BeforeEach
    void setUp() throws Exception {
        maze = new Maze("examples/direct.maz.txt");
    }

    @Test
    void testGetEntryPoint() {
        int[] entryPoint = maze.getEntryPoint();
        assertEquals(1, entryPoint[0]);
        assertEquals(0, entryPoint[1]);
    }

    @Test
    void testGetExitPoint() {
        int[] exitPoint = maze.getExitPoint();
        assertEquals(5, exitPoint[0]);
        assertEquals(7, exitPoint[1]);
    }

    @Test
    void testIsWall() {
        assertTrue(maze.isWall(4, 3));
        assertFalse(maze.isWall(2, 1));
    }
}
