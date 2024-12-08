package skills;

import stats.Stats;
import java.util.Arrays;
import java.util.List;

public class SkillsRegistry {

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

    // Add more skills as needed...

    public static List<Skill> getAllSkills() {
        return Arrays.asList(FIREBALL, HEAL, POISON_STING);
    }
}
