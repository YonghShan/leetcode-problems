import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/10/21 - 17:03
 */
public class Solution2 {
    // 统计每个String中各个Character出现的次数作为Key
    /* Runtime: 15ms (faster than 29.88%)   O(NK)
       Memory: 42.6MB (less than 34.49%)    O(Nk) ？这里又不要sort，用了N个count O(26N)和一个HashMap O(N) => O(N)
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();

        int[] count = new int[26];
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            Arrays.fill(count, 0); // clear count for each String
            for (char c : s.toCharArray()) count[c - 'a']++;
//            // 不可以用int array作为Key，因为在判断是否contains时，用的是array的地址，而不是array的内容
//            if (!map.containsKey(count)) map.put(count, new ArrayList());
//            map.get(count).add(s);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < count.length; i++) {
                sb.append('#');    // 一定要加：
                // "bdddddddddd": #0#1#0#10#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0
                // "bbbbbbbbbbc": #0#10#1#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0
                // 如果不加的话，都为010100000000000000000000000
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
