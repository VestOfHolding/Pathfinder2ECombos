import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.text.WordUtils;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum Background {
    ACOLYTE(Ability.INT, Ability.WIS),
    ACROBAT(Ability.STR, Ability.DEX),
    ANIMAL_WHISPERER(Ability.WIS, Ability.CHA),
    ARTISAN(Ability.STR, Ability.INT),
    ARTIST(Ability.DEX, Ability.CHA),
    BARKEEP(Ability.CON, Ability.CHA),
    BARRISTER(Ability.INT, Ability.CHA),
    BOUNTY_HUNTER(Ability.STR, Ability.WIS),
    CRIMINAL(Ability.DEX, Ability.INT),
    FARMHAND(Ability.CON, Ability.WIS),
    GLADIATOR(Ability.STR, Ability.CHA),
    HERMIT(Ability.CON, Ability.INT),
    HUNTER(Ability.DEX, Ability.WIS),
    LABORER(Ability.STR, Ability.CON),
    STREET_URCHIN(Ability.DEX, Ability.CON);

    private Ability possibleBoost1;
    private Ability possibleBoost2;

    public Set<Ability> getPossibleBoostsAsSet() {
        return Set.of(possibleBoost1, possibleBoost2);
    }

    public String getDisplayName() {
        return WordUtils.capitalize(name().toLowerCase().replaceAll("_", " "));
//        return "(" + possibleBoost1.name() + ", " + possibleBoost2 + ")";
    }
}
