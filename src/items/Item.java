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
        if (type == ItemType.CONSUMABLE) {
            switch (name.toLowerCase()) {
                case "health potion" -> {
                    player.heal(50); // Restores 50 HP
                    System.out.println("You used a Health Potion! Restored 50 HP.");
                }
                case "small health potion" -> {
                    player.heal(20); // Restores 20 HP
                    System.out.println("You used a Small Health Potion! Restored 20 HP.");
                }
                case "large health potion" -> {
                    player.heal(100); // Restores 100 HP
                    System.out.println("You used a Large Health Potion! Restored 100 HP.");
                }
                case "apple" -> {
                    player.heal(10); // Restores 10 HP
                    System.out.println("You ate an Apple! Restored 10 HP.");
                }
                case "elixir" -> {
                    player.heal(player.getMaxHealth()); // Fully restores health
                    player.restoreMana(player.getMaxMana()); // Fully restores mana
                    System.out.println("You used an Elixir! Fully restored health and mana.");
                }
                case "meat" -> {
                    player.heal(30); // Restores 30 HP
                    System.out.println("You ate Meat! Restored 30 HP.");
                }
                default -> {
                    System.out.println("This consumable has no specific effect.");
                }
            }
        } else if (type == ItemType.EQUIPMENT) {
            System.out.println("Equipment items cannot be used mid-fight.");
        } else {
            System.out.println("This item has no effect.");
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
