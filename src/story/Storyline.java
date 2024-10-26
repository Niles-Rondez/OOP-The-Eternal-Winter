package story;

import main.Game;
import main.GameState;
import ui.GameUI;

public class Storyline {
    private Game game;
    private GameUI gameUI;
    private GameState gameState;

    public Storyline(Game game, GameUI gameUI, GameState gameState) {
        this.game = game;
        this.gameUI = gameUI;
        this.gameState = gameState;
    }

    public void processChoice(String choice) {
        switch (gameState.getPosition()) {
            case "tutorial":
                if ("c1".equals(choice)) {
                    startTutorial2();
                }
                break;
            case "tutorial2":
                if ("c1".equals(choice)) {
                    startTutorial();
                }
                break;
        }
    }


    public void startTutorial() {
        gameState.setPosition("tutorial");
        gameUI.updateMainText("This is the start of the Tutorial");
        gameUI.updateChoices(new String[]{">"});
    }

    public void startTutorial2() {
        gameState.setPosition("tutorial2");
        gameUI.updateMainText("Storyline blahblahblah then ask for your name and then your class");
        gameUI.updateChoices(new String[]{">"});
    }
}
