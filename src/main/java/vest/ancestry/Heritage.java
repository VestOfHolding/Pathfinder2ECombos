package vest.ancestry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static vest.Constants.*;

public enum Heritage {
    ANCIENT_BLOODED(DWARF_NAME),
    DEATH_WARDEN(DWARF_NAME),
    FORGE(DWARF_NAME),
    ROCK(DWARF_NAME),
    STRONG_BLOODED(DWARF_NAME),
    ARCTIC(ELF_NAME),
    CAVERN(ELF_NAME),
    SEER(ELF_NAME),
    WHISPER(ELF_NAME),
    WOODLAND(ELF_NAME),
    CHAMELEON(GNOME_NAME),
    FEY_TOUCHED(GNOME_NAME),
    SENSATE(GNOME_NAME),
    UMBRAL(GNOME_NAME),
    WELLSPRING(GNOME_NAME),
    CHARHIDE(GOBLIN_NAME),
    IRONGUT(GOBLIN_NAME),
    RAZORTOOTH(GOBLIN_NAME),
    SNOW(GOBLIN_NAME),
    UNBREAKABLE(GOBLIN_NAME),
    GUTSY(HALFLING_NAME),
    HILLOCK(HALFLING_NAME),
    NOMADIC(HALFLING_NAME),
    TWILIGHT(HALFLING_NAME),
    WILDWOOD(HALFLING_NAME),
    HALF_ELF(HUMAN_NAME),
    HALF_ORC(HUMAN_NAME),
    SKILLED(HUMAN_NAME),
    VERSATILE(HUMAN_NAME);

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
