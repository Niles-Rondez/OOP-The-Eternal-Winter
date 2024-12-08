package characters;

import skills.Skill;
import skills.SkillsRegistry;
import stats.Stats;
import items.Inventory;
import items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerCharacter {
    private static final String DEFAULT_NAME = "Simon";
    private String name;
    private String className; // Store the class name
    private CharacterClass characterClass;
    private Stats baseStats;
    private Stats equipmentStats;
    private Inventory inventory;
    private int gold;

    private List<Skill> skills;

    public PlayerCharacter() {
        this.name = DEFAULT_NAME;
        this.characterClass = chooseClass();  // Get the character class first
        this.className = characterClass.toString(); // Set className from the characterClass
        this.baseStats = initializeStats();  // Initialize stats based on the class
        this.equipmentStats = new Stats(0, 0, 0, 0, 0, 0, 0);  // Equipment stats
        this.inventory = new Inventory();  // Empty inventory initially
        this.gold = 0;  // Start with 0 gold
        this.skills = initializeSkills(); // Initialize starting skills
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    private CharacterClass chooseClass() {
        Scanner scanner = new Scanner(System.in);
        CharacterClass chosenClass = null;
        String className = ""; // Variable to store the name of the chosen class

        // Add the class selection dialogue
        System.out.println("Choose your character class:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Ranger");
        System.out.println("4. Assassin");
        System.out.println("5. Monk");

        while (chosenClass == null) {
            System.out.print("Enter the number corresponding to your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    chosenClass = CharacterClass.WARRIOR;
                    className = "Warrior"; // Set the class name
                }
                case 2 -> {
                    chosenClass = CharacterClass.MAGE;
                    className = "Mage"; // Set the class name
                }
                case 3 -> {
                    chosenClass = CharacterClass.RANGER;
                    className = "Ranger"; // Set the class name
                }
                case 4 -> {
                    chosenClass = CharacterClass.ASSASSIN;
                    className = "Assassin"; // Set the class name
                }
                case 5 -> {
                    chosenClass = CharacterClass.MONK;
                    className = "Monk"; // Set the class name
                }
                default -> System.out.println("Invalid choice, please choose a valid class.");
            }
        }

        // Print the chosen class name
        System.out.println("You have chosen the " + className + " class.");
        return chosenClass;
    }

    private Stats initializeStats() {
        return switch (characterClass) {
            case WARRIOR -> new Stats(4, 3, 1, 1, 0, 30, 1);
            case MAGE -> new Stats(2, 0, 1, 1, 3, 30, 0);
            case RANGER -> new Stats(3, 2, 2, 2, 0, 30, 1);
            case ASSASSIN -> new Stats(2, 3, 3, 2, 0, 30, 0);
            case MONK -> new Stats(3, 2, 1, 1, 2, 30, 0);
        };
    }

    private List<Skill> initializeSkills() {
        List<Skill> startingSkills = new ArrayList<>();
        switch (characterClass) {
            case WARRIOR -> startingSkills.add(SkillsRegistry.POISON_STING);
            case MAGE -> startingSkills.add(SkillsRegistry.FIREBALL);
            case RANGER -> startingSkills.add(SkillsRegistry.POISON_STING);
            case ASSASSIN -> startingSkills.add(SkillsRegistry.POISON_STING);
            case MONK -> startingSkills.add(SkillsRegistry.HEAL);
        }
        System.out.println("Starting skill acquired: " + startingSkills.get(0).getName());
        return startingSkills;
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

    public void addItemToInventory(Item item, int quantity) {
        if (inventory.addItem(item.getName(), quantity)) {
            System.out.println(item.getName() + " has been added to your inventory.");
        } else {
            System.out.println("Inventory is full! Couldn't add " + item.getName() + ".");
        }
    }

    // Method to display character's stats including name and class
    public void displayStats() {
        System.out.println("Character Name: " + name);
        System.out.println("Class: " + className);
        System.out.println("Stats: ");
        System.out.println(baseStats); // Calls the overridden toString() method of Stats
        System.out.println("Skills: ");
        skills.forEach(skill -> System.out.println("- " + skill.getName()));
    }
}
