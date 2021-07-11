import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author YonghShan
 * @date 7/10/21 - 23:07
 */
public class Solution1 {
    // 当需要两层嵌套的 for 循环时，考虑引入 HashSet / HashMap 转变为 one pass
    /* Runtime: 17ms      O((n-10)10) = O(n)
       Memory: 47.4MB     O(n) for HashSet
     */
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        for (int i = 0; i <= s.length()-10; i++) {    // O(n-10)
            String seg = s.substring(i, i+10);        // O(10)
            if (seen.contains(seg)) res.add(seg);
            seen.add(seg);
        }

        return new ArrayList<String>(res);
    }
}
