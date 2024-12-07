package enemies;

import stats.Stats;
import items.Item;

import java.util.List;

public class Enemy {
    private final String name;
    private final Stats stats;
    private final List<Item> possibleDrops; // List of possible drops

    public Enemy(String name, Stats stats, List<Item> possibleDrops) {
        this.name = name;
        this.stats = stats;
        this.stats.setHealth(stats.getHealth());
        this.possibleDrops = possibleDrops; // Initialize the list of drops
    }

    public String getName() {
        return name;
    }

    public Stats getStats() {
        return stats;
    }

    public int getHealth() {
        return stats.getHealth();
    }

    public List<Item> getPossibleDrops() {
        return possibleDrops;
    }

    public void takeDamage(int damage) {
        int newHealth = stats.getHealth() - damage;
        stats.setHealth(Math.max(newHealth, 0)); // Ensure health does not go below 0
    }

    public boolean isDefeated() {
        return stats.getHealth() == 0;
    }

    // Method to retrieve a random drop upon defeat
    public Item dropItem() {
        if (!isDefeated() || possibleDrops.isEmpty()) {
            return null; // No drop if the enemy is not defeated or has no drops
        }
        int randomIndex = (int) (Math.random() * possibleDrops.size());
        return possibleDrops.get(randomIndex); // Randomly pick an item
    }
}
