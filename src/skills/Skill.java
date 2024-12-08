package skills;

import stats.Stats;

public class Skill {
    private final String name;
    private final String description;
    private final int manaCost;
    private final int cooldown;
    private final SkillEffect effect;
    private int currentCooldown = 0; // Tracks cooldown during combat

    public Skill(String name, String description, int manaCost, int cooldown, SkillEffect effect) {
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    public boolean isAvailable() {
        return currentCooldown == 0;
    }

    public void applyCooldown() {
        currentCooldown = cooldown;
    }

    public void reduceCooldown() {
        if (currentCooldown > 0) currentCooldown--;
    }

    public void use(Stats userStats, Stats targetStats) {
        if (isAvailable()) {
            effect.apply(userStats, targetStats); // Correctly calls the `apply` method
            applyCooldown();
        } else {
            System.out.println("Skill is on cooldown!");
        }
    }

    // Remove this redundant method or make it consistent
    public void apply(Stats userStats, Stats targetStats) {
        effect.apply(userStats, targetStats); // Use `apply` from `SkillEffect`
    }
}
