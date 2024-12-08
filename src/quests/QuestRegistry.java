package quests;

import items.Item;
import items.ItemRegistry;
import java.util.Arrays;
import java.util.List;

public enum QuestRegistry {
    SLAY_DRAGONS("Slay the Dragons",
            "Defeat 5 dragons in the Eastern Forest.",
            "Hard",
            Arrays.asList(ItemRegistry.GOLD_COIN.createItem(10)),
            Arrays.asList(new Objective(ItemRegistry.FANG.createItem(1), 5))),
    FIND_MAGIC_SCROLL("Find the Magic Scroll",
            "Locate the ancient scroll in the Haunted Ruins.",
            "Medium",
            Arrays.asList(ItemRegistry.GOLD_COIN.createItem(50)),
            Arrays.asList(new Objective(ItemRegistry.BONE.createItem(1), 3))),
    DELIVER_MESSAGE("Deliver the Message",
            "Deliver a message to the village in the West.",
            "Easy",
            Arrays.asList(ItemRegistry.GOLD_COIN.createItem(20)),
            Arrays.asList(new Objective(ItemRegistry.APPLE.createItem(1), 1)));

    private final String name;
    private final String description;
    private final String difficulty;
    private final List<Item> rewards;
    private final List<Objective> objectives;

    // Constructor
    QuestRegistry(String name, String description, String difficulty, List<Item> rewards, List<Objective> objectives) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.rewards = rewards;
        this.objectives = objectives;
    }

    // Method to create a Quest object
    public Quest createQuest() {
        return new Quest(name, description, difficulty, rewards, objectives);
    }

    // Getters
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
}
