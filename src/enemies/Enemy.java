package enemies;

import items.Item;
import stats.Stats;

import java.util.List;
import java.util.Random;

public class Enemy {
    private final String name;
    private final Stats stats;
    private final List<ItemDrop> possibleDrops;

    public Enemy(String name, Stats stats, List<ItemDrop> possibleDrops) {
        this.name = name;
        this.stats = stats;
        this.possibleDrops = possibleDrops;
    }

    public String getName() {
        return name;
    }

    public Stats getStats() {
        return stats;
    }

    public boolean isDefeated() {
        return stats.getHealth() <= 0;
    }

    public void takeDamage(int damage) {
        stats.setHealth(stats.getHealth() - damage);
    }

    public Item dropItem() {
        Random random = new Random();
        for (ItemDrop drop : possibleDrops) {
            if (random.nextDouble() <= drop.getDropRate()) {
                return drop.getItem().clone(); // Clone to avoid modifying the original item.
            }
        }
        return null; // No item dropped.
    }

    public static class ItemDrop {
        private final Item item;
        private final double dropRate; // Probability of this item dropping (0.0 - 1.0).

        public ItemDrop(Item item, double dropRate) {
            this.item = item;
            this.dropRate = dropRate;
        }

        public Item getItem() {
            return item;
        }

        public double getDropRate() {
            return dropRate;
        }
    }
}
