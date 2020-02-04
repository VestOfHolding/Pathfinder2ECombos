package vest.ancestry;

import lombok.Getter;
import vest.Ability;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static vest.Constants.*;

public enum Ancestry {
    DWARF(Ability.CON, Ability.WIS, Ability.FREE, Ability.CHA, DWARF_NAME),
    ELF(Ability.DEX, Ability.INT, Ability.FREE, Ability.CON, ELF_NAME),
    GNOME(Ability.CON, Ability.CHA, Ability.FREE, Ability.STR, GNOME_NAME),
    GOBLIN(Ability.DEX, Ability.CHA, Ability.FREE, Ability.WIS, GOBLIN_NAME),
    HALFLING(Ability.DEX, Ability.WIS, Ability.FREE, Ability.STR, HALFLING_NAME);
//    HUMAN(Ability.FREE, Ability.FREE, HUMAN_NAME);

    private Ability boost1;
    private Ability boost2;
    private Ability boost3;

    @Getter
    private Ability flaw;

    @Getter
    private String displayName;

    @Getter
    private List<Heritage> heritages;

    Ancestry(Ability boost1, Ability boost2, String displayName) {
        this(boost1, boost2, null, null, displayName);
    }

    Ancestry(Ability boost1, Ability boost2, Ability boost3, Ability flaw, String displayName) {
        this.boost1 = boost1;
        this.boost2 = boost2;
        this.boost3 = boost3;
        this.flaw = flaw;
        this.displayName = displayName;

        heritages = Heritage.getHeritagesByAncestry(displayName);
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
}
