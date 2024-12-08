package quests;

import items.Item;

public class Objective {
    private final Item item; // The item required for the objective
    private final int count; // How many of this item are needed to complete the objective

    public Objective(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public void displayObjective() {
        System.out.println("Objective: Collect " + count + " " + item.getName());
    }
}
