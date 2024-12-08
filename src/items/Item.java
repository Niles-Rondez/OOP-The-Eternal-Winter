package items;

import characters.PlayerCharacter;
import enemies.Enemy;
import stats.Stats;

public class Item implements Cloneable {
    private String name;
    private String description;
    private ItemType type;
    private int value; // Item's monetary value
    private int quantity;
    private Stats bonusStats; // Bonus stats for equipment items
    private String slot;

    public Item(String name, String description, ItemType type, int value, int quantity, Stats bonusStats, String slot) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
        this.quantity = quantity;
        this.bonusStats = bonusStats;
        this.slot = slot;
    }

    // Getters
    public String getSlot() {
        return slot;
    }

    public Stats getBonusStats() {
        return bonusStats;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int amount) {
        quantity -= amount;
        if (quantity < 0) quantity = 0;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public ItemType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }


    /**
     * Applies the effect of this item.
     * @param player the player's stats that the item affects.
     * @param enemy  the enemy's stats, if applicable (null for non-combat effects).
     */
    public void applyEffect(PlayerCharacter player, Enemy enemy) {
        switch (type) {
            case CONSUMABLE -> {
                if (name.equalsIgnoreCase("Health Potion")) {
                    player.heal(20); // Example: Heal player by 20 HP
                    System.out.println("You used a Health Potion! Restored 20 HP.");
                } else if (name.equalsIgnoreCase("Mana Potion")) {
                    player.restoreMana(15); // Example: Restore 15 Mana
                    System.out.println("You used a Mana Potion! Restored 15 Mana.");
                } else if (name.equalsIgnoreCase("Bomb")) {
                    if (enemy != null) {
                        enemy.takeDamage(30); // Example: Deal 30 damage to the enemy
                        System.out.println("You used a Bomb! Dealt 30 damage to the enemy.");
                    }
                }
            }
            case EQUIPMENT -> {
                System.out.println("Equipment items cannot be used mid-fight.");
            }
            default -> {
                System.out.println("This item has no effect.");
            }
        }
    }

    // Override clone method
    @Override
    public Item clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported for Item", e);
        }
    }

    @Override
    public String toString() {
        return "Item{name='" + name + "', description='" + description + "', type=" + type +
                ", value=" + value + ", quantity=" + quantity + "}";
    }
}
