import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 7/13/21 - 16:33
 */
class Solution1 {
    // Rabin-Karp Algorithm + Binary Search
    /* Runtime: 329ms (faster than 41.98%)
       Memory: 51.5MB (less than 25.43%)
     */
    public int searchDuplicate(String S, int L) {
        int n = S.length();

        // convert string to array of integers
        // to implement constant time slice
        int[] nums = new int[n];
        for(int i = 0; i < n; ++i) nums[i] = (int)S.charAt(i) - (int)'a';

        // base value for the rolling hash function
        int base = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 32);

        // compute the hash of the first sequence h_0: 分析见Notes
        long h = 0;
        for(int i = 0; i < L; ++i) h = (h * base + nums[i]) % modulus;

        // already seen hashes of strings of length L
        HashMap<Long, ArrayList<Integer>> seen = new HashMap<>();  // 要想判断是否是因为hash collision而导致的伪重复，必须记录每个hash值对应sequence的起始位置，因此必须用HashMap
        seen.put(h, new ArrayList<>());
        seen.get(h).add(0);

        // 详见Notes：a^L % modulus, which is the adjusted weight of the first digit in any sequence
//        long adjustedWeight = 1;
//        for (int i = 1; i <= L; ++i) adjustedWeight = (adjustedWeight * base) % modulus;
        // 更习惯下面这种写法
         long adjustedWeight = (long) (Math.pow(base, L) % modulus);

        for(int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time：计算公式为啥变这样，详见Notes
//            h = (h * base - nums[start - 1] * adjustedWeight % modulus + modulus) % modulus;
//            h = (h + nums[start + L - 1]) % modulus;
            // 更习惯下面这种写法，也更好理解，runtime也更快
            h = (h * base - nums[start - 1] * adjustedWeight + nums[start + L - 1]) % modulus;
            if (h < 0) h += modulus;
            // ***IMPORTANT***
            // Avoid the hash collision
            if (seen.containsKey(h)) {
                for (Integer prevStart : seen.get(h)) {
                    if (verify(start, prevStart, S, L))
                        return start; // 这里没必要返回S.substring(start, start+L)
                }
            }
            seen.put(h, new ArrayList<>());
            seen.get(h).add(start);
        }
        return -1;
    }

    private boolean verify(int x, int y, String s, int L) {
        for (int i = 0; i < L; i++) {
            if (s.charAt(x++) != s.charAt(y++)) return false;
        }
        return true;
    }

    public String longestDupSubstring(String S) {
        int n = S.length();

        // binary search, L = repeating string length
        int left = 0, right = n-1;
        while (left < right) {
            int L = left + (right - left) / 2;
            if (L == left) L++; // 防止String S的长度为2，此时L取不到1
            if (searchDuplicate(S, L) != -1) left = L;
            else right = L - 1;
        }

        if (left == 0) return "";
        int start = searchDuplicate(S, left);
        return S.substring(start, start + left);
    }

    public String longestDupSubstring2(String S) {  // 在最后return时，少进行一次searchDuplicate()的执行，实际runtime无区别
        int n = S.length();

        // binary search, L = repeating string length
        int left = 0, right = n-1;
        int idx = -1;
        while (left < right) {
            int L = left + (right - left) / 2;
            if (L == left) L++; // 防止String S的长度为2，此时L取不到1
            int foundIdx = searchDuplicate(S, L);
            if (foundIdx != -1) {
                idx = foundIdx;
                left = L;
            }
            else right = L - 1;
        }

        if (left == 0) return "";
        return S.substring(idx, idx + left);
    }
}
