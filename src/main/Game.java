package main;

import ui.GameUI;
import ui.TerminalGame;
import story.Storyline;

import java.util.Scanner;

public class Game {
    private GameState gameState;
    private Storyline storyline;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select mode: (1) GUI Mode, (2) Terminal Mode");
        int mode = scanner.nextInt();

        if (mode == 1) {
            new Game(false); // Launch GUI Mode
        } else if (mode == 2) {
            new Game(true); // Launch Terminal Mode
        } else {
            System.out.println("Invalid mode selected. Exiting.");
        }
    }

    public Game(boolean terminalMode) {
        gameState = new GameState();

        if (terminalMode) {
            // Terminal Mode: Initialize terminal-based game
            new TerminalGame(); // Game starts automatically
        } else {
            // GUI Mode: Initialize GUI-specific game
            GameUI gameUI = new GameUI(this);
            storyline = new Storyline(this, gameUI, gameState);
            gameUI.showTitleScreen(); // Start the GUI game
        }
    }

    public void handleChoice(String choice) {
        if (storyline != null) {
            storyline.processChoice(choice);
        }
    }
}
