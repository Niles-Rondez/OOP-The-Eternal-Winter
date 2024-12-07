package items;

public class Item {
    private final String name;
    private final String description;
    private final ItemType type;
    private final int value;
    private int quantity; // New field for quantity

    public Item(String name, String description, ItemType type, int value, int quantity) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
    }

    // Decreases the quantity when the item is used
    public void decreaseQuantity(int amount) {
        if (quantity - amount >= 0) {
            quantity -= amount;
        }
    }

    // Increases the quantity, useful for potions or items dropped by enemies
    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public void displayItem() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Type: " + type);
        System.out.println("Value: " + value);
        System.out.println("Quantity: " + quantity); // Display the quantity
    }
}
