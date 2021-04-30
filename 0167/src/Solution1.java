import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/30/21 - 11:29
 */
public class Solution1 {
    // 和[0001] Solution2相同
    /* Runtime: 1ms      O(n)
       Memory: 39.2MB    O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) return new int[]{map.get(target-numbers[i]), i+1};
            map.put(numbers[i], i+1);
        }

        return new int[0];
    }
}
