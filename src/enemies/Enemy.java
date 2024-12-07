package enemies;

import stats.Stats;

public class Enemy {
    private final String name;
    private final Stats stats;
    private int health;

    public Enemy(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        this.health = stats.getHealth();
    }

    public String getName() {
        return name;
    }

    public Stats getStats() {
        return stats;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isDefeated() {
        return health == 0;
    }
}
