public class Main {

    public static void main(String[] args) {
        CharacterIterator iterator;

        System.out.println(String.join("\t", "Ancestry", "Background", "Class", "Num Ability Combos"));
        for (Ancestry ancestry : Ancestry.values()) {
            for (Background background : Background.values()) {
                for (PFClass pfClass : PFClass.values()) {
                    iterator = new CharacterIterator(ancestry, background, pfClass);
                    iterator.printCharacterCombos();
                }
            }
        }
    }
}
