package main;

public class GameState {
    private String position;
    private String playerClass;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
        System.out.println("Current position: " + position);
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }
}
