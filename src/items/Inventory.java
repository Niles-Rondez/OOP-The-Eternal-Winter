package items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static final int MAX_SLOTS = 10;
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(String itemName, int quantity) {
        // Fetch item details from the registry
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

        // Try to add to existing stacks first
        for (Item existingItem : items) {
            if (existingItem.getName().equalsIgnoreCase(itemName)) {
                int maxAddable = registryItem.getMaxStackSize() - existingItem.getQuantity();
                if (maxAddable > 0) {
                    int toAdd = Math.min(maxAddable, remainingQuantity);
                    existingItem.increaseQuantity(toAdd);
                    remainingQuantity -= toAdd;
                    if (remainingQuantity <= 0) {
                        return true; // Fully added
                    }
                }
            }
        }

        // Add new stacks if necessary and there's space
        while (remainingQuantity > 0) {
            if (items.size() < MAX_SLOTS) {
                int toAdd = Math.min(remainingQuantity, registryItem.getMaxStackSize());
                items.add(registryItem.createItem(toAdd));
                remainingQuantity -= toAdd;
            } else {
                System.out.println("Inventory is full! Cannot add more items.");
                return false; // Stop if no space for a new stack
            }
        }

        return true;
    }

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
                    items.remove(item); // Remove the item if quantity reaches 0
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
        return null; // Item not found
    }

    public void addUnequippedItem(Item item) {
        items.add(item);
    }
}
