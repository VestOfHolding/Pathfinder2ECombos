import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum PFClass {
    ALCHEMIST_WIZARD(Ability.INT, "Alchemist, Wizard"),
    BARBARIAN_ROGUE_RUFF(Ability.STR, "Barbarian, Rogue(Ruffian)"),
    BARD_ROGUE_SCOUNDREL_SORCERER(Ability.CHA, "Bard, Rogue(Scoundrel), Sorcerer"),
    CHAMP_FIGHT_MONK_RANGER(Ability.STR, Ability.DEX, "Champion, Fighter, Monk, Ranger"),
    CLERIC_DRUID(Ability.WIS, "Cleric, Druid"),
    ROGUE_THIEF(Ability.DEX, "Rogue(Thief)");

    private Ability keyAbilityBoost;
    private Ability alternateKeyAbilityBoost;

    @Getter
    private String displayName;

    PFClass(Ability keyAbilityBoost, String displayName) {
        this.keyAbilityBoost = keyAbilityBoost;
        this.displayName = displayName;
    }

    public Set<Ability> getBoosts() {
        return Stream.of(keyAbilityBoost, alternateKeyAbilityBoost)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
