package vest;

import vest.ancestry.Ancestry;
import vest.pfclass.PFClass;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CharacterIterator iterator;

        List<Character> characters = new ArrayList<>();

        System.out.println(String.join("\t", "Ancestry", "Background", "Class", "Num Dupes", "% Dupes"));
        for (Ancestry ancestry : Ancestry.values()) {
            for (Background background : Background.values()) {
                for (PFClass pfClass : PFClass.values()) {
                    iterator = new CharacterIterator(ancestry, background, pfClass);
                    iterator.printCharacterCombos();
                    characters.add(iterator.getCharacter());
                }
            }
        }

//        Map<String, List<vest.Character>> grouping = characters.stream()
//                .collect(Collectors.groupingBy(c -> c.getAncestry().getDisplayName()
//                        + "\t" + c.getBackground() .getDisplayName()
//                        + "\t\t" + c.getNumPossibleAbilityArrays()));
//
//        for (String key : grouping.keySet()) {
//            System.out.println(key.substring(0, key.length() - 4)
//                    + grouping.get(key).stream().map(c -> c.getPfClass().getDisplayName()).sorted().distinct().collect(Collectors.joining(", "))
//                    + key.substring(key.length() - 4));
//        }
    }
}
