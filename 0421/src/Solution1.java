import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 7/7/21 - 22:17
 */
public class Solution1 {
    // HashSet + Greedy Algorithm
    /* Runtime: 117ms (faster than 50.68%)    O(n)
       Memory: 58.3MB (less than 51.80%)      O(n) for HashSet
     */
    public int findMaximumXOR(int[] nums) {
        // Step 1: 确定异或结果最大的位数L：数组中最大值所对应的二进制位数
        int maxNum = nums[0];
        for(int num : nums) maxNum = Math.max(maxNum, num);
        int L = (Integer.toBinaryString(maxNum)).length();

        // Step 2: 从最高位开始，依次向下判断XOR的各位
        int maxXor = 0, currXor;
        Set<Integer> prefixes = new HashSet<>();
        for(int i = L - 1; i > -1; --i) {  // 这里i从L-1开始是因为异或结果要从最高位开始依次向后确定
            // 通过左移一位为当前的maxXor在最右侧增加一位，e.g. 0变为0，1变为10
            maxXor <<= 1;
            // 将最右侧新增的一位置1，接下来检验这一位到底能不能为1，e.g. 0变为1，10变为11
            currXor = maxXor | 1;
            prefixes.clear();
            // 假如现在要判断XOR的前两位的值，则需要用到数组中元素的前两位的值
            // 通过下面右移i位的操作，取到数组中各元素相应的前几位
            // e.g. 现在currXor为11，那么就需要数组中所有元素的前两位。已知数组中元素对应二进制的最长位数为L，要取前两位，只需右移3位即可（01000变为00001，11001变为00011）==>> 这也是为什么最开始for循环i从L-1开始取
            for(int num: nums) prefixes.add(num >> i);
            // 最右侧能否保留刚置的1，取决于数组中是否有两元素相对应位的异或结果==curXor
            // 变换下思路：将确定 是否有p1^p2 == currXor 变为 是否有p1 == currXor^p2.
            for(int p: prefixes) {
                if (prefixes.contains(currXor^p)) {
                    // 新增的一位可以为1，更新maxXor
                    maxXor = currXor;
                    break;
                }
            }
        }
        return maxXor;
    }
}
