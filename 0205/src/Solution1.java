import java.util.Arrays;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/9/21 - 15:54
 */
public class Solution1 {
    // "egg" -> HashMap<>(<'e', 0>, <'g', 1>) -> [0, 1, 1]
    // "add" -> HashMap<>(<'a', 0>, <'d', 1>) -> [0, 1, 1]
    /* Runtime: 7ms (faster than 60.52%)   O(n+m), where n = s.length(); m = t.length()
       Memory: 39.4MB (less than 15.73%)   O(n+m)
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        int[] numS = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (!map1.containsKey(s.charAt(i))) map1.put(s.charAt(i), i); // 如果numS和numsT的赋值用单独的循环来完成，则这里if判断可省去
            numS[i] = map1.get(s.charAt(i));
        }
        // System.out.println(Arrays.toString(numS));

        HashMap<Character, Integer> map2 = new HashMap<>();
        int[] numT = new int[t.length()];
        for (int i = 0; i < s.length(); i++) {
            if (!map2.containsKey(t.charAt(i))) map2.put(t.charAt(i), i);
            numT[i] = map2.get(t.charAt(i));
        }
        // System.out.println(Arrays.toString(numT));

        return Arrays.equals(numS, numT);
    }
}
