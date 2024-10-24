package characters;

import stats.Stats;

public class Enemy {
    private String name;
    private Stats stats;

    public Enemy(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
    }

    public void displayEnemy(){
        System.out.println("Enemy Name: "+name);
        stats.displayStats();
    }
}
