package ca.mcmaster.se2aa4.mazerunner.ObserverPattern;

public class MazeDisplay implements Observer {
    private final GameEngine game;

    public MazeDisplay(GameEngine game) {
        this.game = game;
    }

    @Override
    public void update() {
        System.out.println("MazeDisplay: " + game);
    }
}