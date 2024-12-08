package npcs;

import characters.PlayerCharacter;

public abstract class NPC {
    private String name;

    public NPC(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void interact(); // Abstract method for NPC interaction
}
