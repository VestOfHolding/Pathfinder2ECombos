package vest;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.apache.commons.math3.util.Precision;
import vest.ancestry.Ancestry;
import vest.ancestry.Heritage;
import vest.pfclass.PFClass;
import vest.pfclass.Subclass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharacterIterator {
    private static Set<Set<Ability>> fourFreeBoostCombos = Sets.combinations(Ability.getAbilities(), 4);

    @Getter
    private Character character;

    @Getter
    private Set<String> possibleArrays = new HashSet<>();
    @Getter
    private List<String> allPossibleArrays = new ArrayList<>();
    @Getter
    private double percentDupes;
    @Getter
    private List<Double> intVals = new ArrayList<>();

    public CharacterIterator(Ancestry ancestry, Background background, PFClass pfClass) {
        this(ancestry, null, background, pfClass, null);
    }

    public CharacterIterator(Ancestry ancestry, Heritage heritage, Background background, PFClass pfClass, Subclass subclass) {
        character = new Character(ancestry, heritage, background, pfClass, subclass);
    }

    public int countPossibleAbilityArrays() {
        possibleArrays = new HashSet<>();
        iterateAncestryBoosts();
        return possibleArrays.size();
    }

    private void iterateAncestryBoosts() {
        for (int i = 0; i < character.getAncestry().numFreeBoosts(); ++i) {
            for (Ability ancestryBoost = Ability.nextAbility(null); ancestryBoost != null; ancestryBoost = Ability.nextAbility(ancestryBoost)) {
                if (character.getAncestry().getNonFreeBoosts().contains(ancestryBoost)) {
                    continue;
                }
                character.boostAbility(ancestryBoost);
                iterateBackgroundBoosts();
                character.lowerAbility(ancestryBoost);
            }
        }
    }

    private void iterateBackgroundBoosts() {
        for (Ability bgBoost : character.getBackground().getPossibleBoostsAsSet()) {
            character.boostAbility(bgBoost);
            for (Ability bgFreeBoost = Ability.nextAbility(null); bgFreeBoost != null; bgFreeBoost = Ability.nextAbility(bgFreeBoost)) {
                if (bgFreeBoost == bgBoost) {
                    continue;
                }
                character.boostAbility(bgFreeBoost);
                iterateClassBoosts();
                character.lowerAbility(bgFreeBoost);
            }
            character.lowerAbility(bgBoost);
        }
    }

    private void iterateClassBoosts() {
        for (Ability classBoost : character.getPfClass().getBoosts()) {
            character.boostAbility(classBoost);
            iterateFreeBoosts();
            character.lowerAbility(classBoost);
        }
    }

    private void iterateFreeBoosts() {
        for (Set<Ability> abilitySet : fourFreeBoostCombos) {

            for (Ability ability : abilitySet) {
                character.boostAbility(ability);
            }

            //We're finally at the bottom!
            intVals.add((double) character.getAbilityArray().get(Ability.INT));
            possibleArrays.add(character.toString());
            allPossibleArrays.add(character.toString());

            for (Ability ability : abilitySet) {
                character.lowerAbility(ability);
            }
        }
    }

    public void printCharacterCombos() {
        int possibleAbilityArrays = countPossibleAbilityArrays();
        int dupes = allPossibleArrays.size() - possibleArrays.size();
        percentDupes = Precision.round((double)dupes / (double)allPossibleArrays.size()  * 100.0, 6);

        character.setNumPossibleAbilityArrays(possibleAbilityArrays);
    }
}
