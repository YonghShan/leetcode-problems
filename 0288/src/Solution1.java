import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 4/15/21 - 15:00
 */
class ValidWordAbbr {
    // HashMap<abbr, HashSet<word>>
    /* Runtime: 56ms (faster than 91.67%)   O(n), n is the length of dictionary
       Memory: 48.9MB (less than 51.48%)    O(m), m is the number of unique words in dictionary
     */
    private HashMap<String, HashSet<String>> map;

    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary) {
            String abbr = s.length() == 1 ? s : getAbbr(s);
            if (!map.containsKey(abbr)) map.put(abbr, new HashSet<>());
            map.get(abbr).add(s);
        }
    }

    public boolean isUnique(String word) {
        String abbr = word.length() == 1 ? word : getAbbr(word);
//        if (!map.containsKey(abbr)) {
//            return true;
//        } else {
//            if (map.get(abbr).size() == 1 && map.get(abbr).contains(word)) return true; // isUnique是要求即使已经有这种abbr，所对应的原词也和自己相同，所以要用HashSet以及判定size()==1
//        }
//        return false;
        Set<String> set = map.get(abbr);
        return set == null || (set.size() == 1 && set.contains(word));
    }

    public String getAbbr(String word) {
        int len = word.length();
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0));
        sb.append(len-2);
        sb.append(word.charAt(len-1));
        return sb.toString();
    }
}
