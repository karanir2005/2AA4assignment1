package ca.mcmaster.se2aa4.mazerunner.ObserverPattern;

public class MoveLogger implements Observer {
    @Override
    public void update() {
        System.out.println("MoveLogger: Player has moved!");
    }
}