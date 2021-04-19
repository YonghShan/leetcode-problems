import java.util.*;

/**
 * @author YonghShan
 * @date 4/15/21 - 15:25
 */
class ValidWordAbbr3 {
    // HashMap<word, isUnique>   喜欢这种implement！
    // TC: O(n)    SC: O(n)
    private final Map<String, Boolean> abbrDict = new HashMap<>();
    private final Set<String> dict;

    public ValidWordAbbr3(String[] dictionary) {
        dict = new HashSet<>(Arrays.asList(dictionary));
        for (String s : dict) {
            String abbr = toAbbr(s);
            abbrDict.put(abbr, !abbrDict.containsKey(abbr));
        }
    }

    public boolean isUnique(String word) {
        String abbr = toAbbr(word);
        Boolean hasAbbr = abbrDict.get(abbr);
        return hasAbbr == null || (hasAbbr && dict.contains(word));
    }

    private String toAbbr(String s) {
        int n = s.length();
        if (n <= 2) {
            return s;
        }
        return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
    }
}
