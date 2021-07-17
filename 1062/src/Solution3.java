import java.util.HashSet;

/**
 * @author YonghShan
 * @date 7/15/21 - 00:21
 */
public class Solution3 {
    // Binary Search + Hashset of Hashes of Already Seen Strings
    /* Runtime: 5ms (faster than 87.95%)    O(nlogn) in average and O(n^2) in worst case where L is close to n/2
       Memory: 39.3MB (less than 60.04%)    O(n) for HashSet
     */
    public int searchDuplicate(String S, int L) {     // O(n)
        int n = S.length();

        HashSet<Integer> seen = new HashSet();
        String tmp;
        int h;
        for(int start = 0; start < n - L + 1; ++start) {
            tmp = S.substring(start, start + L);
            h = tmp.hashCode();
            if (seen.contains(h)) return start;
            seen.add(h);
        }
        return -1;
    }

    public int longestRepeatingSubstring(String s) {    // O(nlogn)
        int n = s.length();

        // binary search, L = repeating string length
        int left = 0, right = n-1;
        while (left < right) {
            int L = left + (right - left) / 2;
            if (L == left) L++; // 防止String S的长度为2，此时L取不到1
            if (searchDuplicate(s, L) != -1) left = L;    // O(n)
            else right = L - 1;
        }

        return left;
    }
}
