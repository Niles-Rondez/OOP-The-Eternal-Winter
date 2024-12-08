package npcs;

import items.Item;
import characters.PlayerCharacter;
import java.util.List;

public class ShopNPC extends NPC {
    private List<Item> shopInventory;

    public ShopNPC(String name, List<Item> shopInventory) {
        super(name);
        this.shopInventory = shopInventory;
    }

    @Override
    public void interact() {
        System.out.println("Welcome to " + getName() + "'s shop!");
        System.out.println("Available items for purchase:");
        for (Item item : shopInventory) {
            System.out.println(item.getName() + " - " + item.getValue() + " gold");
        }
    }

    public void sellItem(PlayerCharacter player, String itemName) {
        for (Item item : shopInventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                if (player.getGold() >= item.getValue()) {
                    player.addGold(-item.getValue());
                    player.addItemToInventory(item, 1);
                    System.out.println("You bought " + item.getName() + " for " + item.getValue() + " gold.");
                } else {
                    System.out.println("Not enough gold!");
                }
                return;
            }
        }
        System.out.println("Item not found in the shop.");
    }

    public void sellItem(PlayerCharacter player, Item item) {
        if (player.getInventory().hasItem(item.getName())) {  // Pass the item name instead of the item object
            player.addGold(item.getValue());
            player.getInventory().removeItem(item);  // Make sure removeItem is implemented correctly
            System.out.println("You sold " + item.getName() + " for " + item.getValue() + " gold.");
        } else {
            System.out.println("You do not have this item.");
        }
    }

}
