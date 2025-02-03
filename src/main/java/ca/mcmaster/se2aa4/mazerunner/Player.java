package ca.mcmaster.se2aa4.mazerunner;

public class Player {

    private int row;
    private int col;
    private String direction;

    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
        this.direction = "right";
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int[] getPosition(){
        return new int[]{row, col};
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void moveForward() {
        if (direction.equalsIgnoreCase("up")){
            row--;
        }
        else if (direction.equalsIgnoreCase("down")){
            row++;
        }
        else if (direction.equalsIgnoreCase("right")){
            col++;
        }
        else{
            col--;
        }
    }

    public void turnLeft() {
        if (direction.equalsIgnoreCase("up")){
            direction="left";
        }
        else if (direction.equalsIgnoreCase("down")){
            direction="right";
        }
        else if (direction.equalsIgnoreCase("right")){
            direction="up";;
        }
        else{
            direction="down";
        }
    }

    public void turnRight() {
        if (direction.equalsIgnoreCase("up")){
            direction="right";
        }
        else if (direction.equalsIgnoreCase("down")){
            direction="left";
        }
        else if (direction.equalsIgnoreCase("right")){
            direction="down";;
        }
        else{
            direction="up";
        }
    }
    
}
