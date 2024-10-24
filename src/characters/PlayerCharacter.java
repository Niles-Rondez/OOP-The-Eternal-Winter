package characters;

import stats.Stats;

public class PlayerCharacter {
    private String name;
    private int level;
    private CharacterClass characterClass;
    private Stats stats;



    public PlayerCharacter(String name, CharacterClass characterClass) {
        this.name = name;
        this.characterClass = characterClass;
        this.level = 1;
        this.stats = initializeStats();
    }

    private Stats initializeStats() {
        switch (characterClass){
            case WARRIOR:
                return new Stats(1,1,1,1,1,1,1); // modify
            case RANGER:
                return new Stats(2,1,1,1,1,1,1);
            case MAGE:
                return new Stats(3,1,1,1,1,1,1);
            case ASSASSIN:
                return new Stats(4,1,1,1,1,1,1);
            case MONK:
                return new Stats(5,1,1,1,1,1,1);
            default:
                return new Stats(0,0,0,0,0,0,0);
        }
    }


    public Stats getStats() {
        return stats;
    }

    public void displayCharacter(){
        System.out.println("Player Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("Character Class: " + characterClass);
        stats.displayStats();
    }
}
