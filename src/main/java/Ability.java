import java.util.HashSet;
import java.util.Set;

public enum Ability {
    STR, DEX, CON, INT, WIS, CHA, FREE;

    public static Ability nextAbility(Ability ability) {
        if (ability == null) {
            return STR;
        }
        return ability == Ability.CHA ? null : values()[ability.ordinal() + 1];
    }

    public static Set<Ability> getAbilities() {
        return new HashSet<>(Set.of(STR, DEX, CON, INT, WIS, CHA));
    }
}
