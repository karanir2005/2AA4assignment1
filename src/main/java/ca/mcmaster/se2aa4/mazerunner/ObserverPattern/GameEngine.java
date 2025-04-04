package ca.mcmaster.se2aa4.mazerunner.ObserverPattern;

public class GameEngine extends Subject {
    private String playerPosition;

    public GameEngine() {
        this.playerPosition = "Start";
    }

    public void movePlayer(String direction) {
        // Simulate changing position
        playerPosition = "Moved " + direction;
        System.out.println("Player moved to: " + playerPosition);
        notifyObservers();
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    @Override
    public String toString() {
        return "Player is at: " + playerPosition;
    }
}