package vest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.text.WordUtils;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum Background {
    // CORE
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
    WARRIOR(Ability.STR, Ability.CON),
    //SOCIETY
    DEMON_SLAYER(Ability.STR, Ability.CON),
    EARLY_EXPLORER(Ability.STR, Ability.WIS),
    FACTION_OPPORTUNIST(Ability.INT, Ability.WIS),
    FORMER_ASPIS_AGENT(Ability.INT, Ability.CHA),
    RUBY_PHOENIX_ENTHUSIAST(Ability.STR, Ability.CON),
    SAVIOR_OF_AIR(Ability.DEX, Ability.CHA),
    SCHOLAR_OF_THE_SKY_KEY(Ability.CON, Ability.INT),
    SHADOW_LODGE_DEFECTOR(Ability.DEX, Ability.INT),
    SHADOW_WAR_SURVIVOR(Ability.WIS, Ability.CHA),
    TAPESTRY_REFUGEE(Ability.CON, Ability.WIS),
    THASSILONIAN_DELVER(Ability.CON, Ability.INT),
    //ADVENTURES
    LESSER_SCION(Ability.DEX, Ability.INT),
    LOST_AND_ALONE(Ability.STR, Ability.CHA),
    MISSIONARY(Ability.CON, Ability.WIS),
    REFUGEE(Ability.CON, Ability.INT),
    TEAMSTER(Ability.STR, Ability.WIS),
    //AGE OF ASHES
    DRAGON_SCHOLAR(Ability.STR, Ability.CHA),
    EMANCIPATED(Ability.DEX, Ability.CHA),
    HAUNTING_VISION(Ability.CON, Ability.WIS),
    HELLKNIGHT_HISTORIAN(Ability.STR, Ability.INT),
    LOCAL_SCION(Ability.CON, Ability.CHA),
    OUT_OF_TOWNER(Ability.STR, Ability.INT),
    REPUTATION_SEEKER(Ability.DEX, Ability.INT),
    RETURNING_DESCENDANT(Ability.DEX, Ability.WIS),
    TRUTH_SEEKER(Ability.STR, Ability.WIS),
    DROSKARI_DISCIPLE(Ability.CON, Ability.INT),
    //EXTINCTION CURSE
    AERIALIST(Ability.DEX, Ability.CHA),
    ANIMAL_WRANGLER(Ability.STR, Ability.WIS),
    BARKER(Ability.CON, Ability.CHA),
    BLOW_IN(Ability.DEX, Ability.CHA),
    BUTCHER(Ability.CON, Ability.INT),
    CIRCUS_BORN(Ability.INT, Ability.CHA),
    CLOWN(Ability.DEX, Ability.CHA),
    MYSTIC_SEER(Ability.INT, Ability.WIS),
    RIGGER(Ability.STR, Ability.DEX),
    DREAMER_OF_THE_VERDANT_MOON(Ability.STR, Ability.WIS);

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
