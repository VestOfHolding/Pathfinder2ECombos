package vest.ancestry;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import vest.Ability;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Ancestry {
    DWARF(Ability.CON, Ability.WIS, Ability.FREE, Ability.CHA),
    ELF(Ability.DEX, Ability.INT, Ability.FREE, Ability.CON),
    GNOME(Ability.CON, Ability.CHA, Ability.FREE, Ability.STR),
    GOBLIN(Ability.DEX, Ability.CHA, Ability.FREE, Ability.WIS),
    HALFLING(Ability.DEX, Ability.WIS, Ability.FREE, Ability.STR),
    HUMAN(Ability.FREE, Ability.FREE);

    private Ability boost1;
    private Ability boost2;
    private Ability boost3;

    @Getter
    private Ability flaw;

    @Getter
    private List<Heritage> heritages;

    Ancestry(Ability boost1, Ability boost2) {
        this.boost1 = boost1;
        this.boost2 = boost2;
        heritages = Heritage.getHeritagesByAncestry(name());
    }

    Ancestry(Ability boost1, Ability boost2, Ability boost3, Ability flaw) {
        this.boost1 = boost1;
        this.boost2 = boost2;
        this.boost3 = boost3;
        this.flaw = flaw;
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

    public String getDisplayName() {
        return StringUtils.capitalize(name().toLowerCase());
    }
}
