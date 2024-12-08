package enemies;

import items.ItemRegistry;
import skills.SkillsRegistry; // Import the SkillsRegistry
import stats.Stats;

import java.util.Arrays;
import java.util.List;

public class EnemyRegistry {
    public static final Enemy GOBLIN = new Enemy(
            "Goblin",
            new Stats(1, 2, 1, 1, 0, 0, 1),
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.GOLD_COIN.createItem(1), 0.8), // 80% chance
                    new Enemy.ItemDrop(ItemRegistry.SMALL_HEALTH_POTION.createItem(1), 0.4) // 40% chance
            ),
            Arrays.asList(SkillsRegistry.POISON_STING) // Assign Poison Sting skill
    );

    public static final Enemy WOLF = new Enemy(
            "Wolf",
            new Stats(1, 3, 2, 1, 0, 0, 1),
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.MEAT.createItem(1), 0.7), // 70% chance
                    new Enemy.ItemDrop(ItemRegistry.FANG.createItem(1), 0.5) // 50% chance
            ),
            Arrays.asList(SkillsRegistry.POISON_STING) // Assign Poison Sting skill
    );

    public static final Enemy SKELETON = new Enemy(
            "Skeleton",
            new Stats(1, 3, 2, 1, 0, 0, 2),
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.BONE.createItem(1), 0.6), // 60% chance
                    new Enemy.ItemDrop(ItemRegistry.SHIELD.createItem(1), 0.3) // 30% chance
            ),
            Arrays.asList(SkillsRegistry.FIREBALL) // Assign Fireball skill
    );

    public static final Enemy MINI_BOSS = new Enemy(
            "Mini-Boss",
            new Stats(1, 5, 3, 3, 2, 2, 2),
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.SWORD.createItem(1), 0.5),
                    new Enemy.ItemDrop(ItemRegistry.LARGE_HEALTH_POTION.createItem(1), 0.8)
            ),
            Arrays.asList(SkillsRegistry.FIREBALL, SkillsRegistry.HEAL) // Assign multiple skills
    );

    public static final Enemy FINAL_BOSS = new Enemy(
            "Final Boss",
            new Stats(1, 8, 5, 4, 4, 3, 3),
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.LEGENDARY_SWORD.createItem(1), 1.0), // Guaranteed drop
                    new Enemy.ItemDrop(ItemRegistry.ELIXIR.createItem(1), 0.7) // 70% chance
            ),
            Arrays.asList(SkillsRegistry.FIREBALL, SkillsRegistry.HEAL, SkillsRegistry.POISON_STING) // Assign multiple powerful skills
    );

    public static List<Enemy> getAllEnemies() {
        return Arrays.asList(GOBLIN, WOLF, SKELETON, MINI_BOSS, FINAL_BOSS);
    }
}
