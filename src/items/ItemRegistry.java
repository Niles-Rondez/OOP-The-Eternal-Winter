package items;

import stats.Stats;

public enum ItemRegistry {
    // Consumables (no specific stats, use default Stats)
    HEALTH_POTION("Health Potion", "Restores 50 HP", ItemType.CONSUMABLE, 10, 5, null, new Stats(0, 0, 0, 0, 0, 0, 0)),
    APPLE("Apple", "Restores 10 HP", ItemType.CONSUMABLE, 5, 10, null, new Stats(0, 0, 0, 0, 0, 0, 0)),
    GOLD_COIN("Gold Coin", "Shiny gold coin", ItemType.CURRENCY, 1, 1, null, new Stats(0, 0, 0, 0, 0, 0, 0)),
    SMALL_HEALTH_POTION("Small Health Potion", "Restores 20 HP", ItemType.CONSUMABLE, 5, 3, null, new Stats(0, 0, 0, 0, 0, 0, 0)),
    LARGE_HEALTH_POTION("Large Health Potion", "Restores 100 HP", ItemType.CONSUMABLE, 30, 2, null, new Stats(0, 0, 0, 0, 0, 0, 0)),
    ELIXIR("Elixir", "Fully restores health and mana", ItemType.CONSUMABLE, 100, 1, null, new Stats(0, 0, 0, 0, 0, 0, 0)),
    MEAT("Meat", "Restores 30 HP", ItemType.CONSUMABLE, 10, 3, null, new Stats(0, 0, 0, 0, 0, 0, 0)),

    // Miscellaneous (no specific stats, use default Stats)
    FANG("Fang", "A sharp fang, can be sold", ItemType.MISC, 15, 1, null, new Stats(0, 0, 0, 0, 0, 0, 0)),
    BONE("Bone", "A sturdy bone, can be sold", ItemType.MISC, 10, 2, null, new Stats(0, 0, 0, 0, 0, 0, 0)),

    // Equipment with specific stats
    SWORD("Sword", "A basic sword", ItemType.EQUIPMENT, 50, 1, "Weapon", new Stats(10, 5, 0, 0, 0, 0, 0)),
    LEATHER_ARMOR("Leather Armor", "Provides basic protection", ItemType.EQUIPMENT, 40, 1, "Body", new Stats(0, 0, 20, 0, 0, 0, 0)),
    LEGENDARY_SWORD("Legendary Sword", "A sword of great power", ItemType.EQUIPMENT, 500, 1, "Weapon", new Stats(50, 20, 0, 0, 0, 0, 10));

    private final String name;
    private final String description;
    private final ItemType type;
    private final int value;
    private final int maxStackSize;
    private final String slot; // Slot where the item can be equipped (null for non-equippable items)
    private final Stats stats; // Stats for the item

    // Constructor
    ItemRegistry(String name, String description, ItemType type, int value, int maxStackSize, String slot, Stats stats) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
        this.maxStackSize = maxStackSize;
        this.slot = slot;
        this.stats = stats; // Assign the stats
    }

    // Creates an Item object with the specified quantity
    public Item createItem(int quantity) {
        return new Item(name, description, type, value, quantity, stats, slot); // Use the specific stats
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public String getSlot() {
        return slot;
    }

    public Stats getStats() {
        return stats;
    }
}
