import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Character {

    public static final int BASE_ABILITY_SCORE = 10;

    private Ancestry ancestry;
    private Background background;
    private PFClass pfClass;

    private Map<Ability, Integer> abilityArray;
    private Ability backgroundBoost;

    public Character(Ancestry ancestry, Background background, PFClass pfClass) {
        this.ancestry = ancestry;
        this.background = background;
        this.pfClass = pfClass;

        initializeAbilityArray();
    }

    public Character(Character character) {
        this.ancestry = character.getAncestry();
        this.background = character.getBackground();
        this.pfClass = character.getPfClass();
    }

    public void initializeAbilityArray() {
        abilityArray = new HashMap<>(Map.of(Ability.STR, BASE_ABILITY_SCORE,
                Ability.DEX, BASE_ABILITY_SCORE,
                Ability.CON, BASE_ABILITY_SCORE,
                Ability.INT, BASE_ABILITY_SCORE,
                Ability.WIS, BASE_ABILITY_SCORE,
                Ability.CHA, BASE_ABILITY_SCORE));

        //Only initialize the non-free ones.
        ancestry.getNonFreeBoosts().forEach(this::boostAbility);
        lowerAbility(ancestry.getFlaw());

        pfClass.getBoosts().forEach(this::boostAbility);
    }

    public void changeAbility(Ability ability, int delta) {
        if (ability != null && ability != Ability.FREE) {
            abilityArray.put(ability, abilityArray.get(ability) + delta);
        }
    }

    public void boostAbility(Ability ability) {
        changeAbility(ability, 2);
    }

    public void lowerAbility(Ability ability) {
        changeAbility(ability, -2);
    }

    @Override
    public String toString() {
        return abilityArray.values().stream().map(Object::toString).collect(Collectors.joining("-"));
    }
}
