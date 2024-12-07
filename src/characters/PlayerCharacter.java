package characters;

import stats.Stats;
import items.Inventory;
import items.Item;
import items.ItemType;
import java.util.Scanner;

public class PlayerCharacter {
    private static final String DEFAULT_NAME = "Simon";
    private String name;
    private CharacterClass characterClass;
    private Stats baseStats;
    private Stats equipmentStats;
    private Inventory inventory;
    private int gold;

    public PlayerCharacter() {
        this.name = DEFAULT_NAME;
        this.characterClass = chooseClass();
        this.baseStats = initializeStats();
        this.equipmentStats = new Stats(0, 0, 0, 0, 0, 0, 0);
        this.inventory = new Inventory();
        this.gold = 0;
    }

    private CharacterClass chooseClass() {
        Scanner scanner = new Scanner(System.in);
        CharacterClass chosenClass = null;

        while (chosenClass == null) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> chosenClass = CharacterClass.WARRIOR;
                case 2 -> chosenClass = CharacterClass.MAGE;
                case 3 -> chosenClass = CharacterClass.RANGER;
                case 4 -> chosenClass = CharacterClass.ASSASSIN;
                case 5 -> chosenClass = CharacterClass.MONK;
            }
        }
        return chosenClass;
    }

    private Stats initializeStats() {
        return switch (characterClass) {
            case WARRIOR -> new Stats(4, 3, 1, 1, 0, 0, 1);
            case MAGE -> new Stats(2, 0, 1, 1, 3, 3, 0);
            case RANGER -> new Stats(3, 2, 2, 2, 0, 0, 1);
            case ASSASSIN -> new Stats(2, 3, 3, 2, 0, 0, 0);
            case MONK -> new Stats(3, 2, 1, 1, 2, 1, 0);
        };
    }

    public Stats getStats() {
        return Stats.combine(baseStats, equipmentStats);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void takeDamage(int damage) {
        baseStats.setHealth(baseStats.getHealth() - damage);
        if (baseStats.getHealth() < 0) baseStats.setHealth(0);
    }

    public void heal(int amount) {
        baseStats.setHealth(baseStats.getHealth() + amount);
    }

    public boolean isDefeated() {
        return baseStats.getHealth() == 0;
    }

    public void addItemToInventory(Item item) {
        if (inventory.addItem(item)) {
            System.out.println(item.getName() + " has been added to your inventory.");
        } else {
            System.out.println("Inventory is full! Couldn't add " + item.getName() + ".");
        }
    }
}
