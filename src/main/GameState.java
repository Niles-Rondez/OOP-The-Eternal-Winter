package main;

public class GameState {
    private String position;

    public String getPosition() {
        System.out.println("Returning position: " + position);
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
        System.out.println("Current position: " + position);
    }
}
