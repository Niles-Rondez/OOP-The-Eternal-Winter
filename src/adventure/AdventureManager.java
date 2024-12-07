package adventure;

import characters.PlayerCharacter;
import enemies.Enemy;
import stats.Stats;

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
        Enemy miniBoss = new Enemy("Mini-Boss", new Stats(5, 3, 2, 2, 1, 1, 1));
        combat(miniBoss);
    }

    private void encounterFinalBoss() {
        System.out.println("The Final Boss awaits!");
        Enemy finalBoss = new Enemy("Final Boss", new Stats(10, 5, 3, 3, 3, 3, 2));
        combat(finalBoss);
    }

    private void combat(Enemy enemy) {
        System.out.println("Combat starts with " + enemy.getName() + "!");
        while (enemy.getHealth() > 0 && player.getStats().getHealth() > 0) {
            System.out.println("\nChoose an action: \n1. Attack \n2. Use Health Potion");
            int choice = scanner.nextInt();
            if (choice == 1) {
                int playerDamage = random.nextInt(10) + player.getStats().getStrength();
                enemy.takeDamage(playerDamage);
                System.out.println("You dealt " + playerDamage + " damage to " + enemy.getName());
            } else if (choice == 2 && player.getInventory().hasItem("Health Potion")) {
                if (player.getInventory().useItem("Health Potion")) {
                    player.heal(20); // Heal by 20 health
                    System.out.println("You used a Health Potion! Your health is now: " + player.getStats().getHealth());
                }
            } else {
                System.out.println("Invalid action or no potions left!");
            }

            if (enemy.isDefeated()) {
                System.out.println("You defeated " + enemy.getName() + "!");
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
        return new Enemy(name, enemyStats);
    }
}
