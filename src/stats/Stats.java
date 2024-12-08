package stats;

public class Stats {
    private int health;
    private int mana;
    private int constitution;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
    private int wisdom;
    private int luck;

    public Stats(int constitution, int strength, int agility, int dexterity, int intelligence, int wisdom, int luck) {
        this.constitution = constitution;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.luck = luck;

        this.health = calculateHealth();
        this.mana = calculateMana();
    }

    private int calculateHealth() {
        return constitution * 5;
    }

    private int calculateMana() {
        return wisdom * 3;
    }

    // Getters
    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getLuck() {
        return luck;
    }

    // Setters
    public void setHealth(int health) {
        this.health = health;
    }

    // Method to combine base and equipment stats
    public static Stats combine(Stats base, Stats equipment) {
        return new Stats(
                base.constitution + equipment.constitution,
                base.strength + equipment.strength,
                base.agility + equipment.agility,
                base.dexterity + equipment.dexterity,
                base.intelligence + equipment.intelligence,
                base.wisdom + equipment.wisdom,
                base.luck + equipment.luck
        );
    }

    // Override the toString() method for custom string representation
    @Override
    public String toString() {
        return "Health: " + health + "\n" +
                "Mana: " + mana + "\n" +
                "Constitution: " + constitution + "\n" +
                "Strength: " + strength + "\n" +
                "Agility: " + agility + "\n" +
                "Dexterity: " + dexterity + "\n" +
                "Intelligence: " + intelligence + "\n" +
                "Wisdom: " + wisdom + "\n" +
                "Luck: " + luck;
    }
}
