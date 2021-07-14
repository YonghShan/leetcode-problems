import java.util.*;

/**
 * @author YonghShan
 * @date 7/11/21 - 23:09
 */
public class Solution2 {
    // [Rabin-Karp]: Constant-time Slice Using Rolling Hash
    /* Runtime: 31ms      O(n-10) = O(n)
       Memory: 50MB       O(n) for HashSet
     */
    public List<String> findRepeatedDnaSequences(String s) {
        int L = 10, n = s.length();
        if (n <= L) return new ArrayList();

        // rolling hash parameters: 见Note -> Concept -> Rabin-Karp Algorithm
        int base = 4;
        int adjustedWeight = (int)Math.pow(base, L);  // 详见Notes：对于任一sequence，其首位字符的权重应为base^(L-1)，但因为本题中采用的是Rolling Hash的第二种方式，故首位字符的权重调整为base^L

        // convert string to array of integers
        Map<Character, Integer> toInt = new
                HashMap() {{put('A', 0); put('C', 1); put('G', 2); put('T', 3); }};
        int[] nums = new int[n];
        for(int i = 0; i < n; ++i) nums[i] = toInt.get(s.charAt(i));

        int h = 0;
        Set<Integer> seen = new HashSet();
        Set<String> output = new HashSet();
        // iterate over all sequences of length L
        for (int start = 0; start < n - L + 1; ++start) {
            // compute hash of the current sequence in O(1) time
            if (start != 0)  // 根据h_{i-1}的hash值，计算出h_i的hash值 （i≥1）
                h = h * base - nums[start - 1] * adjustedWeight + nums[start + L - 1];
                // compute hash of the first sequence in O(L) time
            else // 根据hash function计算出h_0
                for(int i = 0; i < L; ++i) h = h * base + nums[i]; // 这里参考Note中Step 1公式的第三行
            // update output and hashset of seen sequences
            if (seen.contains(h)) output.add(s.substring(start, start + L));
            seen.add(h);
        }
        return new ArrayList<String>(output);
    }
}
