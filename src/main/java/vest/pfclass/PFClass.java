package vest.pfclass;

import lombok.Getter;
import vest.Ability;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static vest.Constants.*;

public enum PFClass {
    ALCHEMIST(Ability.INT, ALCHEMIST_NAME),
    WIZARD(Ability.INT, WIZARD_NAME),
    BARBARIAN(Ability.STR, BARBARIAN_NAME),
    BARD(Ability.CHA, BARD_NAME),
    SORCERER(Ability.CHA, SORCERER_NAME ),
    CHAMPION(Ability.STR, Ability.DEX, CHAMPION_NAME),
    FIGHT(Ability.STR, Ability.DEX, FIGHTER_NAME),
    MONK(Ability.STR, Ability.DEX, MONK_NAME ),
    RANGER(Ability.STR, Ability.DEX, RANGER_NAME ),
    ROGUE_RUFF(Ability.STR, Ability.DEX, ROGUE_RUFF_NAME),
    CLERIC(Ability.WIS, CLERIC_NAME),
    DRUID(Ability.WIS, DRUID_NAME),
    ROGUE_THIEF(Ability.DEX, ROGUE_THIEF_NAME),
    ROGUE_SCOUNDREL(Ability.DEX, Ability.CHA, ROGUE_SCOUNDREL_NAME);

    private Ability keyAbilityBoost;
    private Ability alternateKeyAbilityBoost;

    private String displayName;

    @Getter
    private List<Subclass> subclasses;

    PFClass(Ability keyAbilityBoost, String displayName) {
        this(keyAbilityBoost, null, displayName);
    }

    PFClass(Ability keyAbilityBoost, Ability alternateKeyAbilityBoost, String displayName) {
        this.keyAbilityBoost = keyAbilityBoost;
        this.alternateKeyAbilityBoost = alternateKeyAbilityBoost;
        this.displayName = displayName;
        subclasses = Subclass.getSubclassByClass(displayName);
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
