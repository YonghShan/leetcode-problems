import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/10/21 - 22:25
 */
public class Solution1 {
    // 参考[0049] Solution2
    // 依次两两char相减作为Key: "abc": 'b' - 'a' = 1; 'c' - 'b' = 1 ==> Key: #1#1
    //                       "xyz": 'y' - 'x' = 1; 'z' - 'y' = 1 ==> Key: #1#1
    /* Runtime: 2ms (faster than 61.67%)   O(50n) = O(n), where n is the length of strings
       Memory: 40.4MB (less than 5.60%)    O(n): 用了一个HashMap O(n)
     */
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strings) {   // O(n)
            int len = s.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < len; i++) {    // O(50) because 1 <= strings[i].length <= 50
                sb.append('#');
                int d = s.charAt(i) - s.charAt(i - 1);  // e.g. "ez" = 'z' - 'e' = 122 - 101 = 21 ==> Key: #21
                if (d < 0) d += 26;                     //      "fa" = 'f' - 'a' = 97 - 102 = -5 = -5 + 26 = 21 ==> Key: #21
                sb.append(d);
            }
            String key = len == 1 ? "##" : sb.toString(); // "a""z"不能用"#0"作为Key，因为"aa""bb"是以"#0"作为Key
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
