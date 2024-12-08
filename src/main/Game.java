package main;

import ui.GameUI;
import ui.TerminalGame;
import story.Storyline;

import java.util.Scanner;

public class Game {
    private GameState gameState;
    private Storyline storyline;
    private GameUI gameUI;

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
            gameUI = new GameUI(this);  // Assign to the class-level variable
            storyline = new Storyline(this, gameUI, gameState);
            gameUI.showTitleScreen(); // Start the GUI game
        }
    }

    public void startGame() {
        // When "New Game" is clicked, show the prologue
        gameUI.prologueGameScreen(); // Show the prologue screen
        storyline.startDialogue("act0scene1"); // Start the prologue dia1logue
    }

    public void handleChoice(String choice) {
        if (storyline != null) {
            storyline.processChoice(choice);
        }
    }
}
