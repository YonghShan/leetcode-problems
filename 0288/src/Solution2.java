/**
 * @author YonghShan
 * @date 4/15/21 - 15:25
 */
class ValidWordAbbr2 {
    // O(n) for each isUnique call.
    private final String[] dict;

    public ValidWordAbbr2(String[] dictionary) {
        dict = dictionary;
    }

    public boolean isUnique(String word) {
        int n = word.length();
        for (String s : dict) {
            if (word.equals(s)) {
                continue;
            }
            int m = s.length();
            if (m == n
                    && s.charAt(0) == word.charAt(0)
                    && s.charAt(m - 1) == word.charAt(n - 1)) {
                return false;
            }
        }
        return true;
    }
}
