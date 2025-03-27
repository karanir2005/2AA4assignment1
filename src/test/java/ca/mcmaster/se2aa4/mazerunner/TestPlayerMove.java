package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerMove {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(0, 0);
    }

    @Test
    void testPlayerMoveForward() {
        player.moveForward();
        assertEquals(0, player.getRow());
        assertEquals(1, player.getCol());
    }

    @Test
    void testPlayerTurnLeft() {
        player.turnLeft();
        assertEquals("up", player.getDirection());
    }

    @Test
    void testPlayerTurnRight() {
        player.turnRight();
        assertEquals("down", player.getDirection());
    }
}
