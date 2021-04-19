import java.util.HashSet;

/**
 * @author YonghShan
 * @date 4/8/21 - 11:22
 */
public class Solution1 {
    // HashSet：如果contains，则remove；没有contains，则add
    /* Runtime: 8ms (faster than 40.89%)     O(n)
       Memory: 39.7MB (less than 15.01%)     最多O(n/2)，最终O(1)
     */
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {  // O(1)，如果换成List，List.contains()是O(n)
                set.remove((Integer) nums[i]);
            } else {
                set.add(nums[i]);
            }
        }

        // 因为题目说明了只有一个数只出现一次，所以最后set中只有一个元素
        int res = 0;
        for (Integer e : set) {
            res = e;
        }
        return res;
    }
}
