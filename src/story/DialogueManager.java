package story;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DialogueManager {
    private Map<String, DialogueNode> dialogues;

    public DialogueManager(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + filePath);
            }
            dialogues = mapper.readValue(inputStream, new TypeReference<Map<String, DialogueNode>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DialogueNode getDialogue(String nodeId) {
        return dialogues.get(nodeId);
    }
}
