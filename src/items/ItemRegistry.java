package items;

public enum ItemRegistry {
    HEALTH_POTION("Health Potion", "Restores 50 HP", ItemType.CONSUMABLE, 10, 5),
    SWORD("Sword", "A basic sword", ItemType.EQUIPMENT, 50, 1),
    SHIELD("Shield", "Provides basic protection", ItemType.EQUIPMENT, 40, 1),
    APPLE("Apple", "Restores 10 HP", ItemType.CONSUMABLE, 5, 10),
    GOLD_COIN("Gold Coin", "Shiny gold coin", ItemType.CURRENCY, 1, 1),
    SMALL_HEALTH_POTION("Small Health Potion", "Restores 20 HP", ItemType.CONSUMABLE, 5, 3),
    LARGE_HEALTH_POTION("Large Health Potion", "Restores 100 HP", ItemType.CONSUMABLE, 30, 2),
    LEGENDARY_SWORD("Legendary Sword", "A sword of great power", ItemType.EQUIPMENT, 500, 1),
    ELIXIR("Elixir", "Fully restores health and mana", ItemType.CONSUMABLE, 100, 1),
    MEAT("Meat", "Restores 30 HP", ItemType.CONSUMABLE, 10, 3),
    FANG("Fang", "A sharp fang, can be sold", ItemType.MISC, 15, 1),
    BONE("Bone", "A sturdy bone, can be sold", ItemType.MISC, 10, 2);

    private final String name;
    private final String description;
    private final ItemType type;
    private final int value;
    private final int maxStackSize;

    // Constructor
    ItemRegistry(String name, String description, ItemType type, int value, int maxStackSize) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
        this.maxStackSize = maxStackSize;
    }

    // Creates an Item object with the specified quantity
    public Item createItem(int quantity) {
        return new Item(name, description, type, value, quantity);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }
}
