package story;

import main.Game;
import main.GameState;
import ui.GameUI;

public class Storyline {
    private Game game;
    private GameUI gameUI;
    private GameState gameState;
    private DialogueManager dialogueManager;

    public Storyline(Game game, GameUI gameUI, GameState gameState) {
        this.game = game;
        this.gameUI = gameUI;
        this.gameState = gameState;
        this.dialogueManager = new DialogueManager("dialogues.json");
    }

    public void startDialogue(String nodeId) {
        DialogueNode node = dialogueManager.getDialogue(nodeId);
        gameState.setPosition(nodeId);
        gameUI.updateMainTextWithTypingEffect(node.getText()); // Use the new typing effect method

        String[] choices = node.getChoices().stream()
                .map(DialogueNode.Choice::getText)
                .toArray(String[]::new);
        gameUI.updateChoices(choices);
    }

    public void processChoice(String choiceText) {
        DialogueNode currentNode = dialogueManager.getDialogue(gameState.getPosition());
        for (DialogueNode.Choice choice : currentNode.getChoices()) {
            if (choice.getText().equals(choiceText)) {
                startDialogue(choice.getNextNode());
                return;
            }
        }
    }
}
