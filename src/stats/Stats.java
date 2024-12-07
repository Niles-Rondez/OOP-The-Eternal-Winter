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

    //getters
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

    //setters
    public void setHealth(int health) {
        this.health = health;
    }

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

    public void displayStats() {
        System.out.println("Health: " + health);
        System.out.println("Mana: " + mana);
        System.out.println("Constitution: " + constitution);
        System.out.println("Strength: " + strength);
        System.out.println("Agility: " + agility);
        System.out.println("Dexterity: " + dexterity);
        System.out.println("Intelligence: " + intelligence);
        System.out.println("Wisdom: " + wisdom);
        System.out.println("Luck: " + luck);
    }
}
