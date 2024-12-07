package items;

public class Item {
    private String name;
    private String description;
    private ItemType type;
    private int value; // Item's monetary value
    private int quantity;

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
}
