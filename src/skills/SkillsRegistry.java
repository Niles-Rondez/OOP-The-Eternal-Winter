package skills;

import stats.Stats;
import java.util.Arrays;
import java.util.List;

public class SkillsRegistry {


    public static final Skill BASIC_ATTACK_WARRIOR = new Skill(
            "Basic Attack (Warrior)",
            "A powerful strike with raw physical strength.",
            0, // Mana cost
            0, // No cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getStrength() * 2; // Scales with Strength
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Warrior's Basic Attack hits for " + damage + " damage!");
            }
    );

    public static final Skill BASIC_ATTACK_MAGE = new Skill(
            "Basic Attack (Mage)",
            "A weak magical pulse that harms the enemy.",
            0, // Mana cost
            0, // No cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getIntelligence(); // Scales with Intelligence
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Mage's Basic Attack hits for " + damage + " damage!");
            }
    );

    public static final Skill BASIC_ATTACK_RANGER = new Skill(
            "Basic Attack (Ranger)",
            "A precise arrow shot that pierces the enemy.",
            0, // Mana cost
            0, // No cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getDexterity() * 2; // Scales with Dexterity
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Ranger's Basic Attack hits for " + damage + " damage!");
            }
    );

    public static final Skill BASIC_ATTACK_ASSASSIN = new Skill(
            "Basic Attack (Assassin)",
            "A quick and deadly jab.",
            0, // Mana cost
            0, // No cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getDexterity() * 2; // Scales with Dexterity
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Assassin's Basic Attack hits for " + damage + " damage!");
            }
    );

    public static final Skill BASIC_ATTACK_MONK = new Skill(
            "Basic Attack (Monk)",
            "A spiritual strike that balances physical and magical power.",
            0, // Mana cost
            0, // No cooldown
            (userStats, targetStats) -> {
                int damage = (userStats.getStrength() + userStats.getWisdom()); // Scales with Strength and Wisdom
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Monk's Basic Attack hits for " + damage + " damage!");
            }
    );

    public static final Skill BASIC_ATTACK_ENEMY = new Skill(
            "Basic Attack (Enemy)",
            "A savage attack from the enemy.",
            0, // Mana cost
            0, // No cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getStrength(); // Scales with Strength for simplicity
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Enemy's Basic Attack hits for " + damage + " damage!");
            }
    );

    public static final Skill FIREBALL = new Skill(
            "Fireball",
            "A blazing fireball that scorches enemies.",
            20, // Mana cost
            3,  // Cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getIntelligence() * 3; // Scales with Intelligence
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Fireball hits for " + damage + " damage!");
            }
    );

    public static final Skill HEAL = new Skill(
            "Heal",
            "Restores health to the target.",
            15, // Mana cost
            2,  // Cooldown
            (userStats, targetStats) -> {
                int healing = userStats.getWisdom() * 2; // Scales with Wisdom
                targetStats.setHealth(targetStats.getHealth() + healing);
                System.out.println("Heal restores " + healing + " health!");
            }
    );

    public static final Skill POISON_STING = new Skill(
            "Poison Sting",
            "A venomous attack that deals damage over time.",
            10, // Mana cost
            1,  // Cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getDexterity() * 2; // Scales with Dexterity
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Poison Sting deals " + damage + " damage!");
                // Additional poison over time logic could go here.
            }
    );

    public static final Skill SLAM = new Skill(
            "Slam",
            "A powerful blow that deals massive damage.",
            15, // Mana cost
            2,  // Cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getStrength() * 4;
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Slam hits for " + damage + " damage!");
            }
    );

    public static final Skill TAUNT = new Skill(
            "Taunt",
            "Forces enemies to attack the user for a duration.",
            10, // Mana cost
            3,  // Cooldown
            (userStats, targetStats) -> System.out.println("Enemies are taunted and must attack!")
    );

    public static final Skill ICE_SHARD = new Skill(
            "Ice Shard",
            "Hurls a shard of ice, slowing and damaging the target.",
            20, // Mana cost
            4,  // Cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getIntelligence() * 2;
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Ice Shard deals " + damage + " damage and slows the target!");
            }
    );

    public static final Skill MULTI_SHOT = new Skill(
            "Multi-Shot",
            "Fires multiple arrows, hitting multiple enemies.",
            15, // Mana cost
            3,  // Cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getDexterity() * 3;
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Multi-Shot hits for " + damage + " damage!");
            }
    );

    public static final Skill EVASION = new Skill(
            "Evasion",
            "Increases the user's agility temporarily, reducing incoming damage.",
            10, // Mana cost
            4,  // Cooldown
            (userStats, targetStats) -> System.out.println("Evasion increases agility for the next turn!")
    );

    public static final Skill SHADOW_STRIKE = new Skill(
            "Shadow Strike",
            "A stealthy attack that deals critical damage.",
            15, // Mana cost
            2,  // Cooldown
            (userStats, targetStats) -> {
                int damage = userStats.getAgility() * 5;
                targetStats.setHealth(targetStats.getHealth() - damage);
                System.out.println("Shadow Strike critically hits for " + damage + " damage!");
            }
    );

    public static final Skill HARMONIZE = new Skill(
            "Harmonize",
            "Balances the user's health and mana, redistributing them.",
            20, // Mana cost
            4,  // Cooldown
            (userStats, targetStats) -> {
                int average = (userStats.getHealth() + userStats.getMana()) / 2;
                userStats.setHealth(average);
                userStats.setMana(average);
                System.out.println("Harmonize balances health and mana to " + average + ".");
            }
    );



    public static List<Skill> getAllSkills() {
        return Arrays.asList(FIREBALL, HEAL, POISON_STING);
    }
}
