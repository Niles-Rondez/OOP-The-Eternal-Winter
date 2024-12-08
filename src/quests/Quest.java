package quests;

import items.Item;
import java.util.List;

public class Quest {
    private final String name;
    private final String description;
    private final String difficulty; // e.g., Easy, Medium, Hard
    private final List<Item> rewards;
    private final List<Objective> objectives;

    public Quest(String name, String description, String difficulty, List<Item> rewards, List<Objective> objectives) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.rewards = rewards;
        this.objectives = objectives;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<Item> getRewards() {
        return rewards;
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void displayQuestInfo() {
        System.out.println("Quest Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Objectives: ");
        for (Objective obj : objectives) {
            obj.displayObjective();
        }
        System.out.println("Rewards: ");
        for (Item reward : rewards) {
            System.out.println(" - " + reward.getName());
        }
    }
}
