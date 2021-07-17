import java.util.HashMap;

/**
 * @author YonghShan
 * @date 7/15/21 - 22:53
 */
public class Solution2 {
    // 将word转换为bitmask后，通过bitmask来判断是否有重复的字符
    /* Runtime: 22ms (faster than 50.14%)    O(n^2 + L), where L is the total length of all the words together
       Memory: 38.9MB (less than 73.30%)     O(n) for the hashmap
     */
    public int bitNumber(char c) {
        return (int) c - (int) 'a';
    }

    public int maxProduct(String[] words) {
        HashMap<Integer, Integer> hashmap = new HashMap<>();

        int bitmask = 0;
        for (String word : words) {    // O(L), where L is a total length of all words together
            bitmask = 0;
            for (char c : word.toCharArray()) {
                bitmask |= 1 << bitNumber(c);
            }
            hashmap.put(bitmask, Math.max(hashmap.getOrDefault(bitmask, 0), word.length()));
        }

        int maxVal = 0;
        for (int x : hashmap.keySet())
            for (int y : hashmap.keySet())    // O(n^2)
                if ((x & y) == 0) maxVal = Math.max(maxVal, hashmap.get(x) * hashmap.get(y));    // O(1)

        return maxVal;
    }
}
