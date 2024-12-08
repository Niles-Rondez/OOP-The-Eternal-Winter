package ui;

import adventure.AdventureManager;
import characters.PlayerCharacter;
import items.Item;

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
            System.out.println("3. Manage Equipment");
            System.out.println("4. Go on an Adventure");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> player.displayStats();
                case 2 -> checkInventory();
                case 3 -> manageEquipment();
                case 4 -> goOnAdventure();
                case 5 -> {
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

    private void manageEquipment() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n=== Equipment Management ===");
            System.out.println("1. Equip Item");
            System.out.println("2. Unequip Item");
            System.out.println("3. Display Equipment");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the name of the item to equip: ");
                    String itemName = scanner.nextLine();
                    Item itemToEquip = player.getInventory().retrieveItem(itemName); // Assuming retrieveItem exists
                    if (itemToEquip != null) {
                        boolean equipped = player.equipItem(itemToEquip); // Now you can store the result in a boolean
                        if (equipped) {
                            System.out.println("Equipped " + itemToEquip.getName() + " successfully!");
                        } else {
                            System.out.println("Failed to equip " + itemToEquip.getName() + ". Check the item type or slot availability.");
                        }
                    } else {
                        System.out.println("Item not found in inventory.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter the slot name to unequip from (e.g., Head, Body): ");
                    String slot = scanner.nextLine();
                    boolean unequipped = player.unequipItem(slot); // Assuming unequipItem returns success status
                    if (unequipped) {
                        System.out.println("Unequipped item from " + slot + " slot successfully!");
                    } else {
                        System.out.println("Failed to unequip item from " + slot + ". Check the slot name or equipment.");
                    }
                }
                case 3 -> player.displayEquipment(); // Assuming displayEquipment shows equipped items
                case 4 -> managing = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
