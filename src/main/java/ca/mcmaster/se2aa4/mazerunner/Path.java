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
        int count = 1;
        
        for (int i = 1; i <= moves.size(); i++) {
            if (i < moves.size() && moves.get(i).equals(moves.get(i - 1))) {
                count++;
            } else {
                factorized.append(count > 1 ? count : "");
                factorized.append(moves.get(i - 1)).append(" ");
                count = 1;
            }
        }

        return factorized.toString().trim();
    }

    
}
