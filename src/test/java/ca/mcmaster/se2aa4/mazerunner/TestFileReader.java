package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class TestFileReader {

    @Test
    void testReadFileInvalidFiles() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Maze("examples/invalid.maz.txt"); // Assume file has characters other than '#' and ' '
        });
        assertEquals("Error: Maze contains invalid characters. Only '#' and ' ' are allowed.", exception.getMessage());

        exception = assertThrows(FileNotFoundException.class, () -> {
            new Maze("filedne.txt"); // Assume file does not exist
        });
        assertEquals("Error: Maze file not found. Please enter valid path.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Maze("examples/empty.txt"); // Assume empty.txt is empty
        });
        assertEquals("Error: Maze file is empty.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Maze("examples/noEntry.maz.txt"); // Assume file has no entry/exit
        });
        assertEquals("Error: Maze must have a valid entry and exit point.", exception.getMessage());
    }

    @Test
    void testValidMazeFile() throws Exception {
        Maze maze = new Maze("examples/direct.maz.txt");
        assertNotNull(maze);
    }
}
