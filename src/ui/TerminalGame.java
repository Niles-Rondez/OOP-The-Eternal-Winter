package ui;

import adventure.AdventureManager;
import characters.PlayerCharacter;

import java.util.Scanner;

public class TerminalGame {
    private final PlayerCharacter player;
    private final AdventureManager adventureManager;
    private final Scanner scanner;

    public TerminalGame() {
        this.scanner = new Scanner(System.in);
        System.out.println("Welcome to the Terminal Adventure Game!");
        this.player = new PlayerCharacter();
        this.adventureManager = new AdventureManager(player); // Initialize AdventureManager
        mainMenu(); // Start the game immediately when an instance is created
    }

    private void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Check Stats");
            System.out.println("2. Check Inventory");
            System.out.println("3. Go on an Adventure");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> player.displayStats();
                case 2 -> checkInventory();
                case 3 -> goOnAdventure();
                case 4 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void checkStats() {
        System.out.println("\n=== Your Stats ===");
        System.out.println(player.getStats());
        System.out.println("Gold: " + player.getGold());
    }

    private void checkInventory() {
        System.out.println("\n=== Your Inventory ===");
        player.getInventory().displayInventory();
    }

    private void goOnAdventure() {
        System.out.println("\n=== Adventure Time! ===");
        adventureManager.startAdventure(); // Start the adventure
    }
}
