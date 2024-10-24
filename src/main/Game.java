package main;

import characters.CharacterClass;
import characters.Enemy;
import characters.PlayerCharacter;
import stats.Stats;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for player name
        System.out.print("Enter your character's name: ");
        String playerName = scanner.nextLine();

        // Prompt for character class selection
        System.out.println("Choose your character class:");
        for (CharacterClass characterClass : CharacterClass.values()) {
            System.out.println(characterClass.ordinal() + 1 + ". " + characterClass);
        }

        int classChoice = scanner.nextInt();
        CharacterClass chosenClass = CharacterClass.values()[classChoice - 1];

        // Create the player character with the chosen class
        PlayerCharacter player1 = new PlayerCharacter(playerName, chosenClass);
        player1.displayCharacter();

        /* Create an enemy character
        Stats enemyStats = new Stats(10, 10, 10, 10, 10, 10, 10);
        Enemy enemy = new Enemy("Goblin", enemyStats);
        enemy.displayEnemy();
         */

        // Close the scanner
        scanner.close();
    }
}
