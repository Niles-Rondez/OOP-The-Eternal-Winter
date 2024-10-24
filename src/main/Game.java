package main;

import characters.Enemy;
import characters.PlayerCharacter;
import stats.Stats;

public class Game {
    public static void main(String[] args) {
        Stats playerStats = new Stats(10, 10, 10, 10, 10, 10, 10);
        PlayerCharacter player1 = new PlayerCharacter("Player1", playerStats);
        player1.displayCharacter();

        Stats enemyStats = new Stats(10, 10, 10, 10, 10, 10, 10);
        Enemy enemy = new Enemy("Goblin", enemyStats);
        enemy.displayEnemy();
    }
}
