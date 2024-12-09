package enemies;

import items.ItemRegistry;
import skills.SkillsRegistry; // Import the SkillsRegistry
import stats.Stats;

import java.util.Arrays;
import java.util.List;

public class EnemyRegistry {

    public static final Enemy GOBLIN = new Enemy(
            "Goblin",
            new Stats(2, 2, 1, 1, 0, 0, 1), // Balanced starter enemy
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.GOLD_COIN.createItem(1), 0.8), // 80% chance
                    new Enemy.ItemDrop(ItemRegistry.SMALL_HEALTH_POTION.createItem(1), 0.4) // 40% chance
            ),
            Arrays.asList(SkillsRegistry.POISON_STING, SkillsRegistry.BASIC_ATTACK_ENEMY) // Basic attack skill
    );

    public static final Enemy WOLF = new Enemy(
            "Wolf",
            new Stats(2, 4, 3, 2, 0, 0, 2), // High agility and dexterity
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.MEAT.createItem(1), 0.7), // 70% chance
                    new Enemy.ItemDrop(ItemRegistry.FANG.createItem(1), 0.5) // 50% chance
            ),
            Arrays.asList(SkillsRegistry.EVASION, SkillsRegistry.BASIC_ATTACK_ENEMY) // Defensive skill
    );

    public static final Enemy SKELETON = new Enemy(
            "Skeleton",
            new Stats(3, 3, 2, 1, 0, 0, 3), // Higher health and resilience
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.BONE.createItem(1), 0.6), // 60% chance
                    new Enemy.ItemDrop(ItemRegistry.LEATHER_ARMOR.createItem(1), 0.3) // 30% chance
            ),
            Arrays.asList(SkillsRegistry.ICE_SHARD, SkillsRegistry.BASIC_ATTACK_ENEMY) // Slowing skill
    );

    public static final Enemy BANDIT = new Enemy(
            "Bandit",
            new Stats(3, 3, 4, 3, 0, 1, 3), // Agile and dangerous
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.GOLD_COIN.createItem(2), 0.9), // 90% chance
                    new Enemy.ItemDrop(ItemRegistry.SWORD.createItem(1), 0.4) // 40% chance
            ),
            Arrays.asList(SkillsRegistry.SHADOW_STRIKE, SkillsRegistry.BASIC_ATTACK_ENEMY) // High-damage skill
    );

    public static final Enemy ELEMENTAL = new Enemy(
            "Elemental",
            new Stats(4, 2, 2, 1, 5, 4, 2), // High intelligence and wisdom
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.LEATHER_ARMOR.createItem(1), 0.8), // 80% chance
                    new Enemy.ItemDrop(ItemRegistry.SWORD.createItem(1), 0.5) // 50% chance
            ),
            Arrays.asList(SkillsRegistry.FIREBALL, SkillsRegistry.ICE_SHARD, SkillsRegistry.BASIC_ATTACK_ENEMY) // Elemental magic skills
    );

    public static final Enemy MINI_BOSS = new Enemy(
            "Mini-Boss",
            new Stats(5, 5, 4, 4, 2, 2, 4), // Strong and versatile
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.SWORD.createItem(1), 0.5),
                    new Enemy.ItemDrop(ItemRegistry.LARGE_HEALTH_POTION.createItem(1), 0.8)
            ),
            Arrays.asList(SkillsRegistry.FIREBALL, SkillsRegistry.HEAL, SkillsRegistry.BASIC_ATTACK_ENEMY) // Mixed offensive and healing skills
    );

    public static final Enemy FINAL_BOSS = new Enemy(
            "Final Boss",
            new Stats(8, 8, 6, 5, 6, 5, 5), // Extremely powerful stats
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.LEGENDARY_SWORD.createItem(1), 1.0), // Guaranteed drop
                    new Enemy.ItemDrop(ItemRegistry.ELIXIR.createItem(1), 0.7) // 70% chance
            ),
            Arrays.asList(SkillsRegistry.FIREBALL, SkillsRegistry.HEAL, SkillsRegistry.POISON_STING, SkillsRegistry.TAUNT, SkillsRegistry.BASIC_ATTACK_ENEMY) // Powerful skillset
    );

    public static final Enemy DRAGON = new Enemy(
            "Dragon",
            new Stats(10, 10, 7, 6, 7, 6, 6), // The ultimate challenge
            Arrays.asList(
                    new Enemy.ItemDrop(ItemRegistry.LEGENDARY_SWORD.createItem(1), 0.5), // 50% chance
                    new Enemy.ItemDrop(ItemRegistry.ELIXIR.createItem(1), 0.3) // 30% chance
            ),
            Arrays.asList(SkillsRegistry.FIREBALL, SkillsRegistry.SLAM, SkillsRegistry.EVASION, SkillsRegistry.BASIC_ATTACK_ENEMY) // Devastating attacks
    );

    public static List<Enemy> getAllEnemies() {
        return Arrays.asList(GOBLIN, WOLF, SKELETON, BANDIT, ELEMENTAL, MINI_BOSS, FINAL_BOSS, DRAGON);
    }
}
