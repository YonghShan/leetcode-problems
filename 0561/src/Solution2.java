/**
 * @author YonghShan
 * @date 7/20/21 - 11:27
 */
public class Solution2 {
    // 将nums转换为count，避免sort
    /* Runtime: 3ms (faster than 100.00%)   O(20001)
       Memory: 40.9MB (less than 71.14%)    O(20001)
     */
    public int arrayPairSum(int[] nums) {
        // 2 ≤ nums.length ≤ 20000
        int[] count = new int[2*10000+1];
        for (int num : nums) {
            count[num+10000]++; // 因为-10^4 ≤ num ≤ 10^4，所以+10000保证为非负数
        }

        int i = 0;
        int pairsNum = nums.length / 2;
        int ans = 0;
        while (pairsNum-- > 0) {
            while (count[i] == 0) i++;
            ans += i - 10000;
            count[i]--;
            while (count[i] == 0) i++;
            // 为了跳过(a,b)中的b
            count[i]--;
        }

        return ans;
    }
}
