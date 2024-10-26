package main;

import ui.GameUI;
import story.Storyline;

public class Game {
    private GameUI gameUI;
    private GameState gameState;
    private Storyline storyline;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        gameState = new GameState();
        gameUI = new GameUI(this);
        storyline = new Storyline(this, gameUI, gameState);
        gameUI.showTitleScreen();
    }

    public void startGame() {
        gameUI.createGameScreen();
        storyline.startDialogue("tutorial");
    }

    public void handleChoice(String choice) {
        storyline.processChoice(choice);
    }
}
