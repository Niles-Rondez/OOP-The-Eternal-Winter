package npcs;

import characters.PlayerCharacter;

public class ClericNPC extends NPC {

    public ClericNPC(String name) {
        super(name);
    }

    @Override
    public void interact() {
        System.out.println(getName() + ": I can heal you. Choose an option:");
        System.out.println("1. Basic Healing (Free)\n2. Full Healing (10 gold)");
    }

    public void heal(PlayerCharacter player, int choice) {
        if (choice == 1) {
            player.heal(10); // Heals 10 HP for free
            System.out.println("You received basic healing!");
        } else if (choice == 2 && player.getGold() >= 10) {
            player.heal(100);
            player.addGold(-10);
            System.out.println("You are fully healed!");
        } else {
            System.out.println("Not enough gold or invalid option!");
        }
    }
}
