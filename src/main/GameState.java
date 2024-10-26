package main;

public class GameState {
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
        System.out.println("Current position: " + position);
    }
}
