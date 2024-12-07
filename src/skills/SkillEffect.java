package skills;

import characters.PlayerCharacter;
import enemies.Enemy;

public interface SkillEffect {
    void apply(PlayerCharacter player, Enemy enemy);
}
