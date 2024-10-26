package story;

import java.util.Map;
import java.util.List;

public class DialogueNode {
    private String text;
    private List<Choice> choices;

    // Default constructor for Jackson
    public DialogueNode() {
    }

    public DialogueNode(String text, List<Choice> choices) {
        this.text = text;
        this.choices = choices;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    // Inner class for Choice
    public static class Choice {
        private String text;
        private String nextNode;

        public Choice() {
        }

        public Choice(String text, String nextNode) {
            this.text = text;
            this.nextNode = nextNode;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getNextNode() {
            return nextNode;
        }

        public void setNextNode(String nextNode) {
            this.nextNode = nextNode;
        }
    }
}
