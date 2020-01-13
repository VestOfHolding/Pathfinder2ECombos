package vest.pfclass;

import lombok.AllArgsConstructor;
import vest.Ability;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum PFClass {
    ALCHEMIST(Ability.INT, "Alchemist"),
    WIZARD(Ability.INT, "Wizard"),
    BARBARIAN(Ability.STR, "Barbarian"),
    BARD(Ability.CHA, "Bard"),
    SORCERER(Ability.CHA, "Sorcerer"),
    CHAMPION(Ability.STR, Ability.DEX, "Champion"),
    FIGHT(Ability.STR, Ability.DEX, "Fighter"),
    MONK(Ability.STR, Ability.DEX, "Monk"),
    RANGER(Ability.STR, Ability.DEX, "Ranger"),
    ROGUE_RUFF(Ability.STR, Ability.DEX, "Rogue(Ruffian)"),
    CLERIC(Ability.WIS, "Cleric"),
    DRUID(Ability.WIS, "Druid"),
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
