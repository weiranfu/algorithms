import java.util.*;

public class ADTs {
    /** Returns a lower case version of the string with
      * all characters except letters removed. */
    public static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }

    /** Takes in a String inputFileName and puts every word from the input file into a list. */
    public static List<String> getWords(String inputFileName) {
        In in = new In(inputFileName);
        List<String> words = new ArrayList<>();
        while (!in.isEmpty()) {
            String nextWord = cleanString(in.readString());
            words.add(nextWord);
        }
        return words;
    }

    /** Takes in a List<String> and counts how many unique words there are in the file. */
    public static int countUniqueWords(List<String> list) {
        Set<String> wordSet = new HashSet<>(list);   // create a hashSet based on a list.
        return wordSet.size();
    }

    /**
     * Takes in a List<String> targets and a List<String> words, and
     * finds the number of times each target word appears in the word list.
     */
    public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
        Map<String, Integer> counts = new HashMap<>();
        // We have seen none of the words.
        for (String s : targets) {
            counts.put(s, 0);
        }
        for (String s : words) {
            if (counts.containsKey(s)) {
                int oldCount = counts.get(s);
                counts.put(s, oldCount + 1);
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        List<String> w = getWords("national_salt_production.txt");
        System.out.println(w);
        System.out.println(w.size());
        System.out.println(countUniqueWords(w));

        List<String> targets = new ArrayList<>();
        targets.add("niger");
        targets.add("mali");
        targets.add("oman");
        System.out.println(collectWordCount(w, targets));
    }
}

