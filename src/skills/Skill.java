package skills;

import java.util.List;

public class Skill {
    private String name;
    private String description;
    private boolean classLocked;
    private List<String> allowedClasses;

    public Skill(String name, String description, boolean classLocked, List<String> allowedClasses) {
        this.name = name;
        this.description = description;
        this.classLocked = classLocked;
        this.allowedClasses = allowedClasses;
    }

    public boolean canUse(String playerClass){
        return !classLocked || allowedClasses.contains(playerClass);
    }
}
