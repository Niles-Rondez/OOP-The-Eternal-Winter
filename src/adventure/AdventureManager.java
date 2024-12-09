package adventure;

import characters.PlayerCharacter;
import enemies.Enemy;
import enemies.EnemyRegistry;
import items.Item;
import skills.Skill;
import stats.Stats;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AdventureManager {
    private static final int MAX_ADVENTURES = 30;
    private int currentStep;
    private PlayerCharacter player;
    private Random random;
    private Scanner scanner;

    public AdventureManager(PlayerCharacter player) {
        this.currentStep = 0;
        this.player = player;
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    public void startAdventure() {
        System.out.println("Starting your adventure, " + player.getStats().getHealth() + " health!");

        while (currentStep <= MAX_ADVENTURES) {
            System.out.println("\nAdventure Step: " + currentStep);

            // Option to quit or continue after each step
            System.out.print("Press Enter to continue or type 'stop' to quit: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("stop")) {
                System.out.println("You decided to quit the adventure. Goodbye!");
                break; // Exit the loop and end the adventure
            }

            // Adventure logic
            if (currentStep == MAX_ADVENTURES) {
                encounterFinalBoss();
            } else if (currentStep == 10 || currentStep == 20) {
                encounterMiniBoss();
            } else {
                randomEncounter();
            }

            currentStep++;
        }

        System.out.println("Congratulations! You completed the adventure!");
    }

    private void randomEncounter() {
        int roll = random.nextInt(100); // Random value between 0 and 99
        if (roll < 50) {
            System.out.println("You encountered nothing. The path is clear.");
        } else if (roll < 85) {
            System.out.println("A wild monster appears!");
            Enemy enemy = generateRandomEnemy();
            enemy.resetStats();
            combat(enemy);
        } else {
            System.out.println("You found treasure!");
            int gold = random.nextInt(20) + 10; // Random gold between 10 and 30
            player.addGold(gold);
            System.out.println("You gained " + gold + " gold! Total gold: " + player.getGold());
        }
    }

    private void encounterMiniBoss() {
        System.out.println("A Mini-boss blocks your path!");
        Enemy miniBoss = generateEnemy("MINI_BOSS");
        miniBoss.resetStats();
        combat(miniBoss);
    }

    private void encounterFinalBoss() {
        System.out.println("The Final Boss awaits!");
        Enemy finalBoss = generateEnemy("DRAGON");
        finalBoss.resetStats();
        combat(finalBoss);
    }

    private void combat(Enemy enemy) {
        System.out.println("Combat starts with " + enemy.getName() + "!");
        while (!enemy.isDefeated() && !player.isDefeated()) {
            System.out.println("\nChoose an action: \n1. Attack \n2. Use Skill \n3. Use Item \n4. Run Away");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> {
                    int playerDamage = random.nextInt(10) + player.getStats().getStrength();
                    enemy.takeDamage(playerDamage);
                    System.out.println("You dealt " + playerDamage + " damage to " + enemy.getName());
                }
                case 2 -> useSkill(enemy);
                case 3 -> {
                    System.out.println("Available items in your inventory:");
                    player.getInventory().displayInventory(); // Display items to the player
                    System.out.print("Enter the name of the item to use or 'back' to cancel: ");
                    String itemName = scanner.nextLine().trim();

                    if (itemName.equalsIgnoreCase("back")) {
                        System.out.println("You chose not to use any item.");
                    } else {
                        boolean itemUsed = player.getInventory().useItem(itemName);
                        if (itemUsed) {
                            Item usedItem = player.getInventory().retrieveItem(itemName);
                            if (usedItem != null) {
                                usedItem.applyEffect(player, enemy);
                            }
                        } else {
                            System.out.println("Failed to use item: Either it's not available or invalid.");
                        }
                    }
                }
                case 4 -> {
                    if (attemptEscape(enemy)) {
                        System.out.println("You successfully ran away!");
                        return; // Exit combat if the player successfully escapes
                    } else {
                        System.out.println("Failed to escape! The enemy blocks your path.");
                    }
                }
                default -> System.out.println("Invalid action!");
            }

            if (enemy.isDefeated()) {
                System.out.println("You defeated " + enemy.getName() + "!");
                handleEnemyDrops(enemy);
                break;
            }

            int enemyDamage = random.nextInt(10) + enemy.getStats().getStrength();
            player.takeDamage(enemyDamage);
            System.out.println(enemy.getName() + " dealt " + enemyDamage + " damage to you!");
        }

        if (player.isDefeated()) {
            System.out.println("You were defeated by " + enemy.getName() + "! Game over.");
            System.exit(0);
        }
    }

    private boolean attemptEscape(Enemy enemy) {
        // Escape chance is 50% (you can adjust this logic)
        int escapeChance = random.nextInt(100);
        int difficultyFactor = enemy.getStats().getAgility(); // Example: Use enemy agility to affect escape difficulty

        return escapeChance > difficultyFactor;
    }


    private void useSkill(Enemy enemy) {
        List<Skill> skills = player.getSkills();
        if (skills.isEmpty()) {
            System.out.println("You have no skills to use!");
            return;
        }

        System.out.println("Choose a skill to use:");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getName() + ": " + skills.get(i).getDescription());
        }

        int skillChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (skillChoice < 1 || skillChoice > skills.size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Skill selectedSkill = skills.get(skillChoice - 1);
        if (player.getStats().getMana() < selectedSkill.getManaCost()) {
            System.out.println("Not enough mana to use " + selectedSkill.getName() + "!");
            return;
        }

        System.out.println("You used " + selectedSkill.getName() + "!");
        selectedSkill.apply(player.getStats(), enemy.getStats());
    }

    private void handleEnemyDrops(Enemy enemy) {
        Item droppedItem = enemy.dropItem();
        if (droppedItem != null) {
            player.addItemToInventory(droppedItem, 1);
        } else {
            System.out.println("No items were dropped.");
        }
    }

    // Picks a random enemy from EnemyRegistry.
    private Enemy generateRandomEnemy() {
        List<Enemy> allEnemies = EnemyRegistry.getAllEnemies();
        // Exclude bosses from random encounters
        List<Enemy> nonBossEnemies = allEnemies.stream()
                .filter(enemy -> !enemy.getName().equals("Mini-Boss") && !enemy.getName().equals("Final Boss") && !enemy.getName().equals("Dragon"))
                .toList();
        return nonBossEnemies.get(random.nextInt(nonBossEnemies.size()));
    }

    // Retrieves a specific enemy by name from EnemyRegistry.
    private Enemy generateEnemy(String enemyName) {
        return switch (enemyName) {
            case "MINI_BOSS" -> EnemyRegistry.MINI_BOSS;
            case "FINAL_BOSS" -> EnemyRegistry.FINAL_BOSS;
            case "DRAGON" -> EnemyRegistry.DRAGON;
            default -> throw new IllegalArgumentException("Invalid enemy name: " + enemyName);
        };
    }
}
