import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static Map<Integer, List<Character>> charactersByNumPossibleArrays = new HashMap<>();

    public static void main(String[] args) {
        CharacterIterator iterator;

        System.out.println(String.join("\t", "Ancestry", "Background", "Class", "Num Ability Combos"));

        for (Ancestry ancestry : Ancestry.values()) {
            for (Background background : Background.values()) {
                for (PFClass pfClass : PFClass.values()) {
                    iterator = new CharacterIterator(ancestry, background, pfClass);
                    int numArrays = iterator.countPossibleAbilityArrays();

                    charactersByNumPossibleArrays.computeIfAbsent(numArrays, k -> new ArrayList<>());
                    charactersByNumPossibleArrays.get(numArrays).add(new Character(iterator.getCharacter()));
                }
            }
        }

        List<String> ancestries;
        List<String> backgrounds;
        List<String> pfClasses;

        for (Integer numArrays : charactersByNumPossibleArrays.keySet()) {
            List<Character> characters = charactersByNumPossibleArrays.get(numArrays);

            ancestries = characters.stream().map(c -> c.getAncestry().getDisplayName()).sorted().distinct().collect(Collectors.toList());
            backgrounds = characters.stream().map(c -> c.getBackground().getDisplayName()).sorted().distinct().collect(Collectors.toList());
            pfClasses = characters.stream().map(c -> c.getPfClass().getDisplayName()).sorted().distinct().collect(Collectors.toList());

            System.out.println(String.join("\t",
                    String.join(", ", ancestries),
                    String.join(", ", backgrounds),
                    String.join(", ", pfClasses),
                    Integer.toString(numArrays)));
        }
    }
}
