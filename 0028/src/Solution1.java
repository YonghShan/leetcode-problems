/**
 * @author YonghShan
 * @date 7/18/21 - 22:32
 */
public class Solution1 {
    /* Runtime: 295ms (faster than 26.64%)    O(h*n)
       Memory: 39.5MB (less than 11.10%)      O(n) for substring tmp
     */
    public int strStr(String haystack, String needle) {
        int h = haystack.length();
        int n = needle.length();

        if (n == 0) return 0;

        String tmp;
        for (int i = 0; i < h-n+1; i++) {  // O(h)
            tmp = haystack.substring(i, i+n);  // O(n)
            if (tmp.equals(needle)) return i;
        }

        return -1;
    }
}
