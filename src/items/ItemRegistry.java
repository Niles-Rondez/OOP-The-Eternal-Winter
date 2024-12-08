package items;

import stats.Stats;

public enum ItemRegistry {
    // Consumables don't have slots
    HEALTH_POTION("Health Potion", "Restores 50 HP", ItemType.CONSUMABLE, 10, 5, null),
    APPLE("Apple", "Restores 10 HP", ItemType.CONSUMABLE, 5, 10, null),
    GOLD_COIN("Gold Coin", "Shiny gold coin", ItemType.CURRENCY, 1, 1, null),
    SMALL_HEALTH_POTION("Small Health Potion", "Restores 20 HP", ItemType.CONSUMABLE, 5, 3, null),
    LARGE_HEALTH_POTION("Large Health Potion", "Restores 100 HP", ItemType.CONSUMABLE, 30, 2, null),
    ELIXIR("Elixir", "Fully restores health and mana", ItemType.CONSUMABLE, 100, 1, null),
    MEAT("Meat", "Restores 30 HP", ItemType.CONSUMABLE, 10, 3, null),

    // Miscellaneous items (no slots)
    FANG("Fang", "A sharp fang, can be sold", ItemType.MISC, 15, 1, null),
    BONE("Bone", "A sturdy bone, can be sold", ItemType.MISC, 10, 2, null),

    // Equipment items with designated slots
    SWORD("Sword", "A basic sword", ItemType.EQUIPMENT, 50, 1, "Weapon"),
    SHIELD("Shield", "Provides basic protection", ItemType.EQUIPMENT, 40, 1, "Body"),
    LEGENDARY_SWORD("Legendary Sword", "A sword of great power", ItemType.EQUIPMENT, 500, 1, "Weapon");

    private final String name;
    private final String description;
    private final ItemType type;
    private final int value;
    private final int maxStackSize;
    private final String slot; // Slot where the item can be equipped (null for non-equippable items)

    // Constructor
    ItemRegistry(String name, String description, ItemType type, int value, int maxStackSize, String slot) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
        this.maxStackSize = maxStackSize;
        this.slot = slot; // Assign the slot value (null for non-equippable items)
    }

    // Creates an Item object with the specified quantity
    public Item createItem(int quantity) {
        Stats defaultStats = new Stats(0, 0, 0, 0, 0, 0, 0);  // Default empty Stats object for consumables
        // For equipment items, set default bonus stats (e.g., 10 Strength, 5 Dexterity)
        Stats equipmentStats = type == ItemType.EQUIPMENT ? new Stats(10, 5, 0, 0, 0, 0, 0) : defaultStats;

        return new Item(name, description, type, value, quantity, equipmentStats, slot);  // Pass the slot value to the item
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public String getSlot() {
        return slot;
    }
}
