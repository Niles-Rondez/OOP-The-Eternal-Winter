package characters;

import stats.Stats;

public class PlayerCharacter {
    private String name;
    private Stats stats;

    public PlayerCharacter(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
    }

    public void displayCharacter(){
        System.out.println("Player Name: " + name);
        stats.displayStats();
    }
}
