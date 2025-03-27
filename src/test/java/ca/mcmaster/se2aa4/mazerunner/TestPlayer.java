package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    private Player player;
    private Maze maze;
    private int[] entryPoint;

    @BeforeEach
    void setUp() throws Exception {
        maze = new Maze("examples/direct.maz.txt");
        entryPoint = maze.getEntryPoint();
        player = new Player(entryPoint[0], entryPoint[1]);
    }

    @Test
    void testPlayerInitialization() {
        assertEquals(entryPoint[0], player.getRow());
        assertEquals(entryPoint[1], player.getCol());
    }

    @Test
    void testPlayerMoveAndTurn() {
        player.moveForward();
        assertEquals(entryPoint[0], player.getRow());
        assertEquals(entryPoint[1]+1, player.getCol());

        player.turnLeft();
        assertEquals("up", player.getDirection());

        player.turnRight();
        player.turnRight();
        assertEquals("down", player.getDirection());
    }

    @Test
    void testPlayerResetPosition() {
        player.resetPosition(2, 3, "left");
        assertEquals(2, player.getRow());
        assertEquals(3, player.getCol());
        assertEquals("left", player.getDirection());

        player.setPosition(2, 3);
        assertEquals(2, player.getRow());
        assertEquals(3, player.getCol());

        player.setDirection("right");
        assertEquals("right", player.getDirection());
    }
}
