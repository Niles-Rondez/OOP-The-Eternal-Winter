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

    public Stats(int cons, int str, int agi, int dex, int inte, int wis, int luck ) {
        this.constitution = cons;
        this.strength = str;
        this.agility = agi;
        this.dexterity = dex;
        this.intelligence = inte;
        this.wisdom = wis;
        this.luck = luck;

        this.health = calculateHealth();
        this.mana = calculateMana();
    }

    public int calculateHealth() {
        return constitution * 10; // modify later
    }
    public int calculateMana() {
        return wisdom * 12; // modify after balancing
    }

    public void displayStats() {
        // Depends on how we make the game look
        // Temp
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

