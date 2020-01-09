import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class CharacterIterator {
    private static Set<Set<Ability>> fourFreeBoostCombos = Sets.combinations(Ability.getAbilities(), 4);

    @Getter
    private Character character;

    @Getter
    private Set<String> possibleArrays = new HashSet<>();

    public CharacterIterator(Ancestry ancestry, Background background, PFClass pfClass) {
        character = new Character(ancestry, background, pfClass);
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
            possibleArrays.add(character.toString());

            for (Ability ability : abilitySet) {
                character.lowerAbility(ability);
            }
        }
    }

    public void printCharacterCombos() {
        String output = String.join("\t", character.getAncestry().getDisplayName(),
                character.getBackground().getDisplayName(),
                character.getPfClass().getDisplayName(),
                Integer.toString(countPossibleAbilityArrays()));
        System.out.println(output);
    }
}
