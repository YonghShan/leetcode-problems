import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/9/21 - 22:46
 */
public class Solution1 {
    // HashMap<s..charAt(i), times>
    /* Runtime: 24ms (faster than 26.60%)   O(n)
       Memory: 39.2MB (less than 90.88%)    O(26) = O(1) because English alphabet contains 26 letters.
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
//            if (map.containsKey(s.charAt(i))) {
//                map.put(s.charAt(i), map.get(s.charAt(i))+1);
//            } else {
//                map.put(s.charAt(i), 1);
//            }
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }

        return -1;
    }
}
