/**
 * @author YonghShan
 * @date 7/15/21 - 22:57
 */
public class Solution1 {
    // 没有优化the number of comparisons，但是复杂度相同
    public int bitNumber(char ch) {
        return (int)ch - (int)'a';
    }

    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];

        int bitmask = 0;
        for (int i = 0; i < n; ++i) {    // O(L), where L is a total length of all words together
            bitmask = 0;
            for (char ch : words[i].toCharArray()) {
                bitmask |= 1 << bitNumber(ch);
            }
            masks[i] = bitmask;
        }

        int maxVal = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)    // O(n^2)
                if ((masks[i] & masks[j]) == 0)   // O(1)
                    maxVal = Math.max(maxVal, words[i].length() * words[j].length());

        return maxVal;
    }
}
