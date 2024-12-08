package npcs;

import quests.Quest;
import characters.PlayerCharacter;
import java.util.List;

public class QuestBoardNPC extends NPC {
    private List<Quest> availableQuests;

    public QuestBoardNPC(String name, List<Quest> availableQuests) {
        super(name);
        this.availableQuests = availableQuests;
    }

    @Override
    public void interact() {
        System.out.println(getName() + ": Here are the quests available:");
        for (int i = 0; i < availableQuests.size(); i++) {
            System.out.println((i + 1) + ". " + availableQuests.get(i).getDescription());
        }
    }

    public void assignQuest(PlayerCharacter player, int questIndex) {
        if (questIndex < 1 || questIndex > availableQuests.size()) {
            System.out.println("Invalid quest selection.");
            return;
        }
        Quest selectedQuest = availableQuests.get(questIndex - 1);
        player.addQuest(selectedQuest);
        System.out.println("Quest assigned: " + selectedQuest.getDescription());
    }
}
