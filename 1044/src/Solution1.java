import java.util.HashSet;

/**
 * @author YonghShan
 * @date 7/13/21 - 16:33
 */
class Solution1 {
    /*
    Rabin-Karp with polynomial rolling hash.
        Search a substring of given length
        that occurs at least 2 times.
        Return start position if the substring exits and -1 otherwise.
        */
    public int search(int L, int a, long modulus, int n, int[] nums) {
        // compute the hash of string S[:L]
        long h = 0;
        for(int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;

        // already seen hashes of strings of length L
        HashSet<Long> seen = new HashSet();
        seen.add(h);
        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for(int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[start + L - 1]) % modulus;
            if (seen.contains(h)) return start;
            seen.add(h);
        }
        return -1;
    }

    public String longestDupSubstring(String S) {
        int n = S.length();
        // convert string to array of integers
        // to implement constant time slice
        int[] nums = new int[n];
        for(int i = 0; i < n; ++i) nums[i] = (int)S.charAt(i) - (int)'a';
        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 32);

        // binary search, L = repeating string length
        int left = 0, right = n-1;
        int L;
        while (left < right) {
            System.out.println("left: " + left + " right: " + right);
            L = left + (right - left) / 2;
            if (L == left) L++; // 防止String S的长度为2，此时L取不到1
            System.out.println("L: " + L);
            int r = search(L, a, modulus, n, nums);
            System.out.println("r: " + r);
            if (r != -1) left = L;
            else right = L - 1;
            System.out.println("leftN: " + left + " rightN: " + right);
        }

        if (left != 0) {
            int start = search(left, a, modulus, n, nums);
            return S.substring(start, start + left);
        } else {
            return "";
        }
    }
}
