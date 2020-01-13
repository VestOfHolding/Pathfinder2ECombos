package vest.ancestry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static vest.ancestry.Ancestry.*;

public enum Heritage {
    ANCIENT_BLOODED(DWARF.name()),
    DEATH_WARDEN(DWARF.name()),
    FORGE(DWARF.name()),
    ROCK(DWARF.name()),
    STRONG_BLOODED(DWARF.name()),
    ARCTIC(ELF.name()),
    CAVERN(ELF.name()),
    SEER(ELF.name()),
    WHISPER(ELF.name()),
    WOODLAND(ELF.name()),
    CHAMELEON(GNOME.name()),
    FEY_TOUCHED(GNOME.name()),
    SENSATE(GNOME.name()),
    UMBRAL(GNOME.name()),
    WELLSPRING(GNOME.name()),
    CHARHIDE(GOBLIN.name()),
    IRONGUT(GOBLIN.name()),
    RAZORTOOTH(GOBLIN.name()),
    SNOW(GOBLIN.name()),
    UNBREAKABLE(GOBLIN.name()),
    GUTSY(HALFLING.name()),
    HILLOCK(HALFLING.name()),
    NOMADIC(HALFLING.name()),
    TWILIGHT(HALFLING.name()),
    WILDWOOD(HALFLING.name()),
    HALF_ELF(HUMAN.name()),
    HALF_ORC(HUMAN.name()),
    SKILLED(HUMAN.name()),
    VERSATILE(HUMAN.name());

    String ancestry;

    Heritage(String ancestry) {
        this.ancestry = ancestry;
    }

    public static List<Heritage> getHeritagesByAncestry(String ancestry) {
        return Arrays.stream(values())
                .filter(h -> StringUtils.equalsIgnoreCase(h.ancestry, ancestry))
                .collect(Collectors.toList());
    }

    public String getDisplayName() {
        return WordUtils.capitalize(name().toLowerCase().replaceAll("_", " "));
    }
}
