/**
 * @author YonghShan
 * @date 7/18/21 - 22:34
 */
public class Solution2 {
    // Rabin-Karp Algorithm    详见[1044]的代码
    /* Runtime: 3ms (faster than 48.08%)    O(max(h, n))
       Memory: 39MB (less than 34.09%)      O(h+n) for arrays numsH & numsN
     */
    public int strStr(String haystack, String needle) {
        int h = haystack.length();
        int n = needle.length();

        if (n == 0) return 0;
        if (h == 0 || n > h) return -1;

        // convert string to array of integers
        int[] numsH = new int[h];
        int[] numsN = new int[n];
        for (int i = 0; i < h; i++) numsH[i] = (int)haystack.charAt(i) - (int)'a';
        for (int i = 0; i < n; i++) numsN[i] = (int)needle.charAt(i) - (int)'a';

        int base = 26;
        long modulus = (long)Math.pow(2,32);

        // compute the hash of the first sequence h_0 of haystack
        long hashH = 0;
        for (int i = 0; i < n; i++) hashH = (hashH * base + numsH[i]) % modulus;
        // compute the hash of needle
        long hashN = 0;
        for (int i = 0; i < n; i++) hashN = (hashN * base + numsN[i]) % modulus;

        if (hashH == hashN) return 0;

        long adjustedWeight = 1;
        for (int i = 1; i <= n; ++i) adjustedWeight = (adjustedWeight * base) % modulus;

        for (int start = 1; start < h-n+1; start++) {
            hashH = (hashH * base - numsH[start-1] * adjustedWeight + numsH[start + n -1]) % modulus;
            while (modulus < 0) hashH += modulus;
            if (hashH == hashN) return start;
        }

        return -1;
    }
}
