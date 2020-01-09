import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum Ancestry {
    DWARF(Ability.CON, Ability.WIS, Ability.FREE, Ability.CHA),
    ELF(Ability.DEX, Ability.INT, Ability.FREE, Ability.CON),
    GNOME(Ability.CON, Ability.CHA, Ability.FREE, Ability.STR),
    GOBLIN(Ability.DEX, Ability.CHA, Ability.FREE, Ability.WIS),
    HUMAN(Ability.FREE, Ability.FREE);

    private Ability boost1;
    private Ability boost2;
    private Ability boost3;

    private Ability flaw;

    Ancestry(Ability boost1, Ability boost2) {
        this.boost1 = boost1;
        this.boost2 = boost2;
    }

    public Set<Ability> getBoosts() {
        return Stream.of(boost1, boost2, boost3)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Set<Ability> getNonFreeBoosts() {
        return getBoosts().stream()
                .filter(a -> a != Ability.FREE)
                .collect(Collectors.toSet());
    }

    public int numFreeBoosts() {
        return (int) getBoosts().stream()
                .filter(a -> a == Ability.FREE)
                .count();
    }

    public Ability getFlaw() {
        return flaw;
    }

    public String getDisplayName() {
        return StringUtils.capitalize(name().toLowerCase());
    }
}
