package skills;

import stats.Stats;

@FunctionalInterface
public interface SkillEffect {
    void apply(Stats userStats, Stats targetStats);
}
