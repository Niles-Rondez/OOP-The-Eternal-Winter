package adventure;

import characters.PlayerCharacter;
import enemies.Enemy;
import items.Item;
import items.ItemType;
import stats.Stats;

import java.util.Arrays;
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

    private List<Item> generateMiniBossDrops() {
        return Arrays.asList(
                new Item("Iron Sword", "A basic sword", ItemType.EQUIPMENT, 100, 1),
                new Item("Large Health Potion", "Restores 30 HP", ItemType.CONSUMABLE, 150, 1)
        );
    }

    private List<Item> generateFinalBossDrops() {
        return Arrays.asList(
                new Item("Legendary Sword", "A sword of great power", ItemType.EQUIPMENT, 500, 1),
                new Item("Elixir", "Fully restores health and mana", ItemType.CONSUMABLE, 300, 1)
        );
    }

    public void startAdventure() {
        System.out.println("Starting your adventure!");

        while (currentStep <= MAX_ADVENTURES) {
            System.out.println("\nAdventure Step: " + currentStep);
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
        Enemy miniBoss = new Enemy(
                "Mini-Boss",
                new Stats(5, 3, 2, 2, 1, 1, 1),
                generateMiniBossDrops()
        );
        combat(miniBoss);
    }

    private void encounterFinalBoss() {
        System.out.println("The Final Boss awaits!");
        Enemy finalBoss = new Enemy(
                "Final Boss",
                new Stats(10, 5, 3, 3, 3, 3, 2),
                generateFinalBossDrops()
        );
        combat(finalBoss);
    }

    private void combat(Enemy enemy) {
        System.out.println("Combat starts with " + enemy.getName() + "!");
        while (enemy.getStats().getHealth() > 0 && player.getStats().getHealth() > 0) {
            System.out.println("\nChoose an action: \n1. Attack \n2. Use Health Potion");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    int playerDamage = random.nextInt(10) + player.getStats().getStrength();
                    enemy.takeDamage(playerDamage);
                    System.out.println("You dealt " + playerDamage + " damage to " + enemy.getName());
                }
                case 2 -> {
                    if (player.getInventory().useItem("Health Potion")) {
                        player.heal(20); // Heal by 20 health
                        System.out.println("You used a Health Potion! Your health is now: " + player.getStats().getHealth());
                    } else {
                        System.out.println("You don't have any Health Potions!");
                    }
                }
                default -> System.out.println("Invalid action!");
            }

            if (enemy.isDefeated()) {
                System.out.println("You defeated " + enemy.getName() + "!");
                Item drop = enemy.dropItem();
                if (drop != null) {
                    System.out.println("The enemy dropped: " + drop.getName() + "!");
                    player.getInventory().addItem(drop);
                }
                break;
            }

            int enemyDamage = random.nextInt(10) + enemy.getStats().getStrength();
            System.out.println(enemy.getName() + " dealt " + enemyDamage + " damage to you!");
            player.takeDamage(enemyDamage);
        }

        if (player.getStats().getHealth() <= 0) {
            System.out.println("You were defeated by " + enemy.getName() + "! Game over.");
            System.exit(0);
        }
    }

    private Enemy generateRandomEnemy() {
        String[] enemyNames = {"Goblin", "Wolf", "Skeleton"};
        String name = enemyNames[random.nextInt(enemyNames.length)];
        Stats enemyStats = new Stats(3, 2, 1, 1, 0, 0, 1);
        List<Item> drops = Arrays.asList(
                new Item("Gold Coin", "Shiny gold coin", ItemType.CURRENCY, 10, 1),
                new Item("Small Health Potion", "Restores 10 HP", ItemType.CONSUMABLE, 50, 1)
        );
        return new Enemy(name, enemyStats, drops);
    }
}
