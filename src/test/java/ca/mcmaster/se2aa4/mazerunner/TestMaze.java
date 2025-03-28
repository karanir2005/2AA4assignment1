package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

public class TestMaze {

    private Maze maze;

    @BeforeEach
    void setUp() throws Exception {
        maze = new Maze("examples/direct.maz.txt");
    }

    @AfterEach
    void tearDown() {
        maze = null;
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
    void testMazeElements() {
        assertEquals(7, maze.getHeight());
        assertEquals(8, maze.getWidth());
        assertEquals('#', maze.getCell(0, 0));
        assertEquals(' ', maze.getCell(1, 0));
        assertTrue(maze.isWall(4, 3));
        assertFalse(maze.isWall(2, 1));
    }
}
