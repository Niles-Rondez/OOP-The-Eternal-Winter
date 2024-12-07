package skills;

public class Skill {
    private final String name;
    private final String description;
    private final int manaCost;
    private final int cooldown;
    private final SkillEffect effect;

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

    public SkillEffect getEffect() {
        return effect;
    }
}
