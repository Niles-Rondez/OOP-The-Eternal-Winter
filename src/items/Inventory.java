package items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static final int MAX_SLOTS = 10;
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    // Method to add an item to the inventory (already exists)
    public boolean addItem(String itemName, int quantity) {
        ItemRegistry registryItem = null;
        for (ItemRegistry item : ItemRegistry.values()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                registryItem = item;
                break;
            }
        }

        if (registryItem == null) {
            System.out.println("Item not found in registry!");
            return false;
        }

        int remainingQuantity = quantity;

        for (Item existingItem : items) {
            if (existingItem.getName().equalsIgnoreCase(itemName)) {
                int maxAddable = registryItem.getMaxStackSize() - existingItem.getQuantity();
                if (maxAddable > 0) {
                    int toAdd = Math.min(maxAddable, remainingQuantity);
                    existingItem.increaseQuantity(toAdd);
                    remainingQuantity -= toAdd;
                    if (remainingQuantity <= 0) {
                        return true;
                    }
                }
            }
        }

        while (remainingQuantity > 0) {
            if (items.size() < MAX_SLOTS) {
                int toAdd = Math.min(remainingQuantity, registryItem.getMaxStackSize());
                items.add(registryItem.createItem(toAdd));
                remainingQuantity -= toAdd;
            } else {
                System.out.println("Inventory is full! Cannot add more items.");
                return false;
            }
        }

        return true;
    }

    // Method to check if an item exists in the inventory
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true; // Item exists in the inventory
            }
        }
        return false; // Item not found
    }

    // Method to remove an item from the inventory
    public boolean removeItem(Item item) {
        for (Item existingItem : items) {
            if (existingItem.equals(item)) {
                items.remove(existingItem);
                System.out.println(item.getName() + " has been removed from your inventory.");
                return true; // Item removed successfully
            }
        }
        System.out.println("Item not found in inventory.");
        return false; // Item not found in the inventory
    }

    // Other methods (displayInventory, useItem, etc.) remain unchanged
    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item item : items) {
                System.out.println(item.getName() + " (x" + item.getQuantity() + ") - " + item.getDescription());
            }
        }
    }

    public boolean useItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName) && item.getQuantity() > 0) {
                item.decreaseQuantity(1);
                System.out.println("You used a " + item.getName() + "!");
                if (item.getQuantity() == 0) {
                    items.remove(item);
                }
                return true;
            }
        }
        System.out.println("You don't have any " + itemName + " left!");
        return false;
    }

    public Item retrieveItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public void addUnequippedItem(Item item) {
        items.add(item);
    }
}
