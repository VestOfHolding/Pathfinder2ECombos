package vest;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import vest.ancestry.Ancestry;
import vest.ancestry.Heritage;
import vest.pfclass.PFClass;
import vest.pfclass.Subclass;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static DecimalFormat df = new DecimalFormat("###.##'%'");

    public static void main(String[] args) {
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

        System.out.println(String.join("\t", df.format(stats.getMin()),
                df.format(stats.getPercentile(25.0)),
                df.format(stats.getMean()),
                df.format(stats.getPercentile(75.0)),
                df.format(stats.getMax())));
    }
}