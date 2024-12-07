package items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static final int MAX_SLOTS = 10;
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        for (Item existingItem : items) {
            if (existingItem.getName().equals(item.getName())) {
                existingItem.increaseQuantity(item.getQuantity());
                return true;
            }
        }

        if (items.size() < MAX_SLOTS) {
            items.add(item);
            return true;
        } else {
            System.out.println("Inventory is full!");
            return false;
        }
    }

    public void removeItem(Item item) {
        items.remove(item);
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
            if (item.getName().equals(itemName) && item.getQuantity() > 0) {
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
}
