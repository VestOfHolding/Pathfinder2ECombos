import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum PFClass {
    ALCHEMIST_WIZARD(Ability.INT, "Alchemist, Wizard"),
    BARBARIAN(Ability.STR, "Barbarian"),
    BARD_SORCERER(Ability.CHA, "Bard, Sorcerer"),
    CHAMP_FIGHT_MONK_RANGER_ROGUE_RUFF(Ability.STR, Ability.DEX, "Champion, Fighter, Monk, Ranger, Rogue(Ruffian)"),
    CLERIC_DRUID(Ability.WIS, "Cleric, Druid"),
    ROGUE_THIEF(Ability.DEX, "Rogue(Thief)"),
    ROGUE_SCOUNDREL(Ability.DEX, Ability.CHA, "Rogue(Scoundrel)");

    private Ability keyAbilityBoost;
    private Ability alternateKeyAbilityBoost;

    private String displayName;

    PFClass(Ability keyAbilityBoost, String displayName) {
        this.keyAbilityBoost = keyAbilityBoost;
        alternateKeyAbilityBoost = null;
        this.displayName = displayName;
    }

    public Set<Ability> getBoosts() {
        return Stream.of(keyAbilityBoost, alternateKeyAbilityBoost)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public String getDisplayName() {
        return displayName;
//        return "(" + keyAbilityBoost.name() + (alternateKeyAbilityBoost == null ? ")" : ", " + alternateKeyAbilityBoost + ")");
    }
}
