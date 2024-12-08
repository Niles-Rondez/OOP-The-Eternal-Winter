package ui;

import adventure.AdventureManager;
import characters.PlayerCharacter;
import items.Item;
import items.ItemRegistry;
import quests.Quest;
import quests.QuestRegistry;

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
            System.out.println("4. Visit NPCs");
            System.out.println("5. Go on an Adventure");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> player.displayStats();
                case 2 -> checkInventory();
                case 3 -> manageEquipment();
                case 4 -> visitNPCs();
                case 5 -> goOnAdventure();
                case 6 -> {
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

    private void visitNPCs() {
        boolean visiting = true;
        while (visiting) {
            System.out.println("\n=== NPC Interactions ===");
            System.out.println("1. Visit QuestBoard (Tavern)");
            System.out.println("2. Visit Cleric (Church)");
            System.out.println("3. Visit Shop (Market)");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> visitQuestBoard();
                case 2 -> visitCleric();
                case 3 -> visitShop();
                case 4 -> visiting = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void visitQuestBoard() {
        System.out.println("\n=== QuestBoard in Tavern ===");
        // Loop through all quests in the QuestRegistry and display them
        System.out.println("Available quests:");
        int i = 1;
        for (QuestRegistry quest : QuestRegistry.values()) {
            System.out.print(i++ + ". ");
            quest.createQuest().displayQuestInfo();  // Display quest details
            System.out.println();
        }

        // Let the player choose a quest
        System.out.print("Enter quest number to accept or 'back' to go back: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("back")) {
            return;
        }

        try {
            int questChoice = Integer.parseInt(input);
            if (questChoice >= 1 && questChoice <= QuestRegistry.values().length) {
                Quest selectedQuest = QuestRegistry.values()[questChoice - 1].createQuest();
                System.out.println("You have accepted the quest: " + selectedQuest.getName());
                // Implement quest acceptance logic here
            } else {
                System.out.println("Invalid quest number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private void visitCleric() {
        System.out.println("\n=== Cleric in Church ===");
        System.out.println("The Cleric offers healing services for a price.");
        System.out.print("Do you want to heal for 10 gold? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("yes") && player.getGold() >= 10) {
            player.heal(50); // Heal the player by 50 health
            player.subtractGold(10); // Subtract gold
            System.out.println("You have been healed! Remaining gold: " + player.getGold());
        } else if (choice.equals("yes")) {
            System.out.println("You don't have enough gold!");
        } else {
            System.out.println("You chose not to heal.");
        }
    }

    private void visitShop() {
        System.out.println("\n=== Shop in Market ===");
        System.out.println("The Shop offers a variety of items for sale.");
        // Display available items in shop (this is already handled by ItemRegistry)
        System.out.println("Items available for purchase:");
        System.out.println("1. Health Potion (10 Gold)");
        System.out.println("2. Mana Potion (8 Gold)");
        System.out.print("Enter item number to buy or 'back' to go back: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("back")) {
            return;
        }

        try {
            int itemChoice = Integer.parseInt(input);
            Item itemToBuy = null;

            // Select item from ItemRegistry based on the user's choice
            switch (itemChoice) {
                case 1:
                    if (player.getGold() >= 10) {
                        itemToBuy = ItemRegistry.HEALTH_POTION.createItem(1);  // Create a Health Potion with quantity 1
                        player.subtractGold(10); // Subtract gold
                        System.out.println("You bought a Health Potion!");
                    } else {
                        System.out.println("You don't have enough gold!");
                    }
                    break;
                case 2:
                    if (player.getGold() >= 8) {
                        itemToBuy = ItemRegistry.APPLE.createItem(1); // Create an Apple with quantity 1
                        player.subtractGold(8); // Subtract gold
                        System.out.println("You bought a Mana Potion!");
                    } else {
                        System.out.println("You don't have enough gold!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            if (itemToBuy != null) {
                player.addItemToInventory(itemToBuy, 1); // Add the item to player's inventory
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
}
