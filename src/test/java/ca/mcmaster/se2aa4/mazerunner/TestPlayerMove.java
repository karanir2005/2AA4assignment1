package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestPlayerMove {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(0, 0);
    }

    @Test
    void testMoveForward() {
        player.moveForward();
        assertEquals(0, player.getRow());
        assertEquals(1, player.getCol());
    }

    @Test
    void testTurnAndMove() {
        player.turnLeft();
        player.moveForward();
        assertEquals(-1, player.getRow());
        assertEquals(0, player.getCol());
    }
}
