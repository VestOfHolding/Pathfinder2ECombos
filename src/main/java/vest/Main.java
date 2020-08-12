package vest;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import vest.ancestry.Ancestry;
import vest.ancestry.Heritage;
import vest.pfclass.PFClass;
import vest.pfclass.Subclass;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("###.##'%'");
    private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("###.#");

    public static void main(String[] args) {
        printHumanIntStats();
    }

    public static void printHumanIntStats() {
        Map<PFClass, DescriptiveStatistics> statsByClass = new HashMap<>();

        Ancestry ancestry = Ancestry.HUMAN;
        Set<Background> backgrounds = new HashSet<>(Arrays.asList(Background.values()));
        Set<PFClass> pfClasses = new HashSet<>(Arrays.asList(PFClass.values()));

        System.out.println(String.join("\t", "Class", "INT Min", "INT 1st Quartile", "INT Mean", "INT 3rd Quartiles", "INT Max"));

        for (Heritage heritage : ancestry.getHeritages()) {

            backgrounds.parallelStream().forEach(background ->

                    pfClasses.parallelStream().forEach(pfClass -> {
                        statsByClass.putIfAbsent(pfClass, new DescriptiveStatistics());
                        for (Subclass subclass : pfClass.getSubclasses()) {
                            CharacterIterator iterator = new CharacterIterator(ancestry, heritage, background, pfClass, subclass);
                            iterator.printCharacterCombos();
                            iterator.getIntVals().forEach(i -> statsByClass.get(pfClass).addValue(i));
                        }
                    })
            );
        }

        for (PFClass pfClass : statsByClass.keySet()) {
            DescriptiveStatistics stats = statsByClass.get(pfClass);

            System.out.println(String.join("\t",
                    pfClass.getDisplayName(),
                    DOUBLE_FORMAT.format(stats.getMin()),
                    DOUBLE_FORMAT.format(stats.getPercentile(25.0)),
                    DOUBLE_FORMAT.format(stats.getMean()),
                    DOUBLE_FORMAT.format(stats.getPercentile(75.0)),
                    DOUBLE_FORMAT.format(stats.getMax())));
        }
    }

    public static void printDupeStats() {
        DescriptiveStatistics stats = new DescriptiveStatistics();

        Set<Ancestry> ancestries = new HashSet<>(Arrays.asList(Ancestry.values()));
        Set<Background> backgrounds = new HashSet<>(Arrays.asList(Background.values()));
        Set<PFClass> pfClasses = new HashSet<>(Arrays.asList(PFClass.values()));

        System.out.println(String.join("\t", "Dupes Min", "Dupes 1st Quartile", "Dupes Mean", "Dupes 3rd Quartiles", "Dupes Max"));

        ancestries.parallelStream().forEach(ancestry -> {
            for (Heritage heritage : ancestry.getHeritages()) {

                backgrounds.parallelStream().forEach(background ->

                        pfClasses.parallelStream().forEach(pfClass -> {
                            for (Subclass subclass : pfClass.getSubclasses()) {
                                CharacterIterator iterator = new CharacterIterator(ancestry, heritage, background, pfClass, subclass);
                                iterator.printCharacterCombos();
                                stats.addValue(iterator.getPercentDupes());
                            }
                        })
                );
            }
        });

        System.out.println(String.join("\t", PERCENT_FORMAT.format(stats.getMin()),
                PERCENT_FORMAT.format(stats.getPercentile(25.0)),
                PERCENT_FORMAT.format(stats.getMean()),
                PERCENT_FORMAT.format(stats.getPercentile(75.0)),
                PERCENT_FORMAT.format(stats.getMax())));
    }
}