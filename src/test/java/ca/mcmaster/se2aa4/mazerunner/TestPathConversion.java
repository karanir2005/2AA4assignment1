package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPathConversion {

    private Path path = new Path();

    @Test
    void testExpandFactorized() {
        assertEquals("FF LL FFFF RR", path.expandFactorized("2F 2L 4F 2R"));
        assertEquals("FF LL FFFF RR", path.expandFactorized("2 F 2 L 4 F 2 R"));
    }
}
