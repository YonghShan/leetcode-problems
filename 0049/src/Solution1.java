import java.util.*;

/**
 * @author YonghShan
 * @date 4/10/21 - 15:56
 */
public class Solution1 {
    // 对每个String先进行按字符排序：eat -> aet; tea -> aet; nat -> ant; ...
    // 再存入HashMap<String, List<String>>中：<"aet", {"eat", "tea", "ate"}>
    /* Runtime: O(NKlogK), where N = strs.length; K is the maximum length of a String in the strs
       Memory: O(KN) comes from sorting: for each char[] c, it takes space complexity of O(c.length).
                                         Since the maximum length of c is K, we have to sort N c => O(KN)
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        /* Runtime: 12ms (faster than 33.77%)
           Memory: 43.1MB (less than 22.86%)
        */
        List<List<String>> res = new ArrayList<>();

        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            List<String> tmp = map.getOrDefault(Arrays.toString(c), new ArrayList<>());
            tmp.add(s);
            map.put(Arrays.toString(c), tmp);
        }

        Set<String> set = map.keySet();
        for (String sc : set) {
            res.add(map.get(sc));
        }
        return res;
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        /* Runtime: 7ms (faster than 45.79%)
           Memory: 42.8MB (less than 29.20%)
        */
        if (strs.length == 0) return new ArrayList();

        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            if (!map.containsKey(String.valueOf(c))) map.put(String.valueOf(c), new ArrayList<>());
            map.get(String.valueOf(c)).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
