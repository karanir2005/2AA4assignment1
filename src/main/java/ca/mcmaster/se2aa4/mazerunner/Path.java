package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> moves;

    public Path() {
        this.moves = new ArrayList<>();
    }

    public void addMove(String move) {
        moves.add(move);
    }

    public String getCanonicalPath(){

        StringBuilder canonical = new StringBuilder();
        
        if (!moves.isEmpty()) {
            canonical.append(moves.get(0)); // Add the first move to the canonical path
        }
        
        for (int i = 1; i < moves.size(); i++) {
            if (!moves.get(i).equals(moves.get(i - 1))) { // Only add a space when the move changes
                canonical.append(" ");
            }
            canonical.append(moves.get(i));
        }

        return canonical.toString().trim();

        /*for (int x; x<moves.size()-1; x++){
            if (moves.get(x).equals(moves.get(x+1))){
                continue;      
            }
            else{
                moves.add(x+1," ");
            }
        }
        return String.join(" ", moves);*/

    }

    public String getFactorizedPath(){
        StringBuilder factorized = new StringBuilder();
        int count = 1; //counts the number of continuous letters 
        
        for (int i = 1; i <= moves.size(); i++) { 
            if (i < moves.size() && moves.get(i).equals(moves.get(i - 1))) {
                count++;
            } else {
                factorized.append(count > 1 ? count : ""); //format the output properly with numbers and spaces
                factorized.append(moves.get(i - 1)).append(" ");
                count = 1;
            }
        }

        return factorized.toString().trim();
    }

    public String expandFactorized (String path) {
        StringBuilder pathBuilder = new StringBuilder();
        StringBuilder expandedPath = new StringBuilder();

        path = path.replaceAll("\\s+", " ");//replace multiple spaces with a single space

        //remove any spaces after numbers
        for (int i=0; i < path.length(); i++){
            char currentChar = path.charAt(i);
            if (Character.isDigit(currentChar)){
                pathBuilder.append(currentChar);
                i++;
                while (i < path.length() && path.charAt(i) == ' '){
                    i++;
                }
                pathBuilder.append(path.charAt(i));
            }
            else {
                pathBuilder.append(currentChar);
            }
        }

        path = pathBuilder.toString();
        
        for (int i=0; i < path.length(); i++){
            char currentChar = path.charAt(i);

            if (Character.isDigit(currentChar)){
                int numStart = i;
                for (; i < path.length() && Character.isDigit(path.charAt(i)); i++);
                
                int repeatCount = Integer.parseInt(path.substring(numStart, i));

                if (i < path.length()) {
                    char move = path.charAt(i);

                    for (int j = 0; j < repeatCount; j++) {
                        expandedPath.append(move);
                    }
                }

            }
            else {
                expandedPath.append(currentChar);
            }
        }
        
        return expandedPath.toString();
    }
}
