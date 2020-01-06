import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum Background {
    ACOLYTE(Ability.INT, Ability.WIS),
    ACROBAT(Ability.STR, Ability.DEX),
    ANIMAL_WHISPERER(Ability.WIS, Ability.CHA),
    ARTISAN(Ability.STR, Ability.INT),
    ARTIST(Ability.DEX, Ability.CHA),
    BARKEEP(Ability.CON, Ability.CHA);

    private Ability possibleBoost1;
    private Ability possibleBoost2;

    public Set<Ability> getPossibleBoostsAsSet() {
        return Set.of(possibleBoost1, possibleBoost2);
    }
}
