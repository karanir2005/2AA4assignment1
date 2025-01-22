package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private char[][] layout;
    private int[] entryPoint;
    private int[] exitPoint;

    public Maze(String filePath) throws Exception {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        layout = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            layout[i] = lines.get(i).toCharArray();
        }

        entryPoint = findEntryOrExit("entry");
        exitPoint = findEntryOrExit("exit");
    }

    private int[] findEntryOrExit(String type) {
        int col;
        if (type.equals("entry")){
            col = 0;
        }
        else{
            col = layout[0].length - 1;
        }

        for (int row = 0; row < layout.length; row++) {
            if (layout[row][col] == ' ') {
                return new int[]{row, col};
            }
        }
        throw new IllegalArgumentException("No " + type + " point found!");
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
