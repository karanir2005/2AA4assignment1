package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private char[][] layout;
    private int[] entryPoint;
    private int[] exitPoint;

    public Maze(String filePath) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            readFile(reader);
        } catch (IOException e) {
            throw new FileNotFoundException("Error: Maze file not found. Please enter valid path.");
        }
    
        entryPoint = findEntryOrExit("entry");
        exitPoint = findEntryOrExit("exit");
    
        if (entryPoint[0] == -1 || exitPoint[0] == -1) {
            throw new IllegalArgumentException("Error: Maze must have a valid entry and exit point.");
        }
    }

    public void readFile(BufferedReader reader) throws Exception {
        List<String> lines = new ArrayList<>();
        String line;
    
        while ((line = reader.readLine()) != null) {
            if (!line.matches("[# ]*")) { // Ensures only '#' or ' ' characters exist
                throw new IllegalArgumentException("Error: Maze contains invalid characters. Only '#' and ' ' are allowed.");
            }
            lines.add(line);
        }
    
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Error: Maze file is empty.");
        }
    
        int width = lines.get(0).length();
        layout = new char[lines.size()][width];
    
        for (int i = 0; i < lines.size(); i++) {
            char[] row = lines.get(i).toCharArray();
            for (int j = 0; j < width; j++) {
                layout[i][j] = (j < row.length) ? row[j] : ' ';
            }
        }
    }

    private int[] findEntryOrExit(String type) {
        int col;
        if (type.equals("entry")){ //searches through col 0 for entry point
            col = 0;
        }
        else{
            col = layout[0].length - 1; //searches through col len-1 for exit.
        }

        for (int row = 0; row < layout.length; row++) {
            if (layout[row][col] == ' ') { //loops thorugh col and checks for space (entry point)
                return new int[]{row, col};
            }
        }
        return new int[]{-1,-1}; //if no point is found, return -1,-1
    }

    public int[] getEntryPoint() {
        return entryPoint;
    }

    public int[] getExitPoint() {
        return exitPoint;
    }

    public char getCell(int row, int col) {
        return layout[row][col];
    }

    public boolean isWall(int row, int col) {
        return layout[row][col] == '#';
    }

    public int getHeight() {
        return layout.length;
    }

    public int getWidth() {
        return layout[0].length;
    }
}
