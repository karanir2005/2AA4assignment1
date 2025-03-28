package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

public class TestPath {

    private Path path;

    @BeforeEach
    void setUp() {
        path = new Path();
    }

    @AfterEach
    void tearDown() {
        path = null;
    }

    @Test
    void testAddMove() {
        path.addMove("F");
        assertEquals("F", path.getCanonicalPath());
        path.addMove("R");
        assertEquals("F R", path.getCanonicalPath());
    }

    @Test
    void testGetCanonicalPath() {
        path.addMove("F");
        path.addMove("R");
        path.addMove("F");
        assertEquals("F R F", path.getCanonicalPath());
    }

    @Test
    void testGetFactorizedPath() {
        path.addMove("F");
        path.addMove("F");
        path.addMove("R");
        path.addMove("R");
        path.addMove("F");
        assertEquals("2F 2R F", path.getFactorizedPath());
    }

    @Test
    void testExpandFactorizedPath() {
        String expanded = path.expandFactorized("2F 2R F");
        assertEquals("FF RR F", expanded);
        assertEquals("FF LL FFFF RR", path.expandFactorized("2F 2L 4F 2R"));
        assertEquals("FF LL FFFF RR", path.expandFactorized("2 F 2 L 4 F 2 R"));
    }
}
