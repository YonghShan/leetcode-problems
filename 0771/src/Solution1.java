import java.util.HashSet;

/**
 * @author YonghShan
 * @date 4/11/21 - 22:19
 */
public class Solution1 {
    // HashSet存jewels，再去遍历stones
    /* Runtime: 1ms (faster than 66.65%)    O(n+m), where n = jewels.length, m = stones.length
       Memory: 37.5MB (less than 40.84%)    O(n), actually n is smaller than 26...
     */
    public int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> set = new HashSet<>();
        for (Character c : jewels.toCharArray()) {
            set.add(c);
        }
        int count = 0;
        for (Character c : stones.toCharArray()) {
            if (set.contains(c)) count++;
        }

        return count;
    }
}
