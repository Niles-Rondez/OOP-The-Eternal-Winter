package items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static final int MAX_SLOTS = 10;
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    // Adds an item to the inventory, if there is space
    public boolean addItem(Item item) {
        if (items.size() < MAX_SLOTS) {
            items.add(item);
            return true;
        } else {
            System.out.println("Inventory is full!");
            return false;
        }
    }

    // Removes an item from the inventory
    public void removeItem(Item item) {
        items.remove(item);
    }

    // Display all items in the inventory
    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item item : items) {
                System.out.println(item.getName() + " (x" + item.getQuantity() + ")");
            }
        }
    }

    // Check if the player has a specific item
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName) && item.getQuantity() > 0) {
                return true;
            }
        }
        return false;
    }

    // Use an item from the inventory
    public boolean useItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName) && item.getQuantity() > 0) {
                item.decreaseQuantity(1); // Use one of this item
                System.out.println("You used a " + item.getName() + "!");
                return true;
            }
        }
        System.out.println("You don't have any " + itemName + " left!");
        return false;
    }
}
