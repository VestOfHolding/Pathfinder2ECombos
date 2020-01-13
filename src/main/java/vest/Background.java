package vest;

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
    STREET_URCHIN(Ability.DEX, Ability.CON),
    CHARLATAN(Ability.INT, Ability.CHA),
    DETECTIVE(Ability.INT, Ability.WIS),
    EMISSARY(Ability.INT, Ability.CHA),
    ENTERTAINER(Ability.DEX, Ability.CHA),
    FIELD_MEDIC(Ability.CON, Ability.WIS),
    FORTUNE_TELLER(Ability.INT, Ability.CHA),
    GAMBLER(Ability.DEX, Ability.CHA),
    GUARD(Ability.STR, Ability.CHA),
    HERBALIST(Ability.CON, Ability.WIS),
    MARTIAL_DISCIPLE(Ability.STR, Ability.DEX),
    MERCHANT(Ability.INT, Ability.CHA),
    MINER(Ability.STR, Ability.WIS),
    NOBLE(Ability.INT, Ability.CHA),
    NOMAD(Ability.CON, Ability.WIS),
    PRISONER(Ability.STR, Ability.CON),
    SAILOR(Ability.STR, Ability.DEX),
    SCHOLAR(Ability.INT, Ability.WIS),
    SCOUT(Ability.DEX, Ability.WIS),
    TINKER(Ability.DEX, Ability.INT),
    WARRIOR(Ability.STR, Ability.CON);

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
