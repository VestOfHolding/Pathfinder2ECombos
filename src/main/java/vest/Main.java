package vest;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import vest.ancestry.Ancestry;
import vest.ancestry.Heritage;
import vest.pfclass.PFClass;
import vest.pfclass.Subclass;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static DecimalFormat df = new DecimalFormat("###.##");

    public static void main(String[] args) {
        CharacterIterator iterator;

//        List<Character> characters = new ArrayList<>();
        DescriptiveStatistics stats = new DescriptiveStatistics();

        System.out.println(String.join("\t", "Dupes MIN (%)", "Dupes 1st Quartile  (%)", "Dupes Mean (%)", "Dupes 3rd Quartiles (%)", "Dupes Max (%)"));
        for (Ancestry ancestry : Ancestry.values()) {
            for (Heritage heritage : ancestry.getHeritages()) {
                for (Background background : Background.values()) {
                    for (PFClass pfClass : PFClass.values()) {
                        for (Subclass subclass : pfClass.getSubclasses()) {
                            iterator = new CharacterIterator(ancestry, heritage, background, pfClass, subclass);
                            iterator.printCharacterCombos();
                            stats.addValue(iterator.getPercentDupes());
//                            characters.add(iterator.getCharacter());
                        }
                    }
                }
            }
        }

        System.out.println(String.join("\t", df.format(stats.getMin()),
                df.format(stats.getPercentile(25.0)),
                df.format(stats.getMean()),
                df.format(stats.getPercentile(75.0)),
                df.format(stats.getMax())));

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
