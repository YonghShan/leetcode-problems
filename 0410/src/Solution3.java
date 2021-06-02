/**
 * @author YonghShan
 * @date 6/1/21 - 22:43
 */
public class Solution3 {
    // Binary Search:
    // 定义函数F(x)，表示：当存在一种分法使得各subarray的the maximum largest sum不超过x时，F(x)为true
    // x \in [left, right]， F(x)一开始为false，直到x=x_0时，F(x)变为true，此后F(x)一直保持为true => x_0即为answer
    // 利用BS寻找x_0， if F(mid) == false, then search [mid+1, right]
    //               if F(mid) == true, then search [left, mid-1]
    // F(mid)的值通过2.1.1和2.1.2共同得出
    /* Runtime: 1ms (faster than 57.41%)     BS costs O(log(right)),每一次BS时要判断F(mid) which costs O(n) => O(nlog(right))
       Memory: 36.5MB (less than 68.31%)     O(1)
     */
    public int splitArray(int[] nums, int m) {
        int left = nums[0];   // nums中元素最大值
        int right = nums[0];  // nums中所有元素之和
        int len = nums.length;

        // Step 1: determine the value of left and right for Binary Search
        for (int i = 1; i < len; i++) {
            left = Math.max(left, nums[i]);
            right += nums[i];
        }

        // Step 2: Binary Search
        int ans = 0;
        while (left <= right) {    // O(log(right))
            // System.out.println("left: " + left + "right: " + right);
            int mid = left + (right - left) / 2;
            int sum = 0;
            int cnt = 1; // 初始即为一个array
            // 2.1 determine whether F(mid) is true or false
            // 2.1.1 利用变量sum保证到目前为止subarray的元素和不超过mid
            for (int i = 0; i < len; i++) {    // O(n)
                if (sum + nums[i] > mid) { // 该分subarray了
                    cnt++;
                    sum = nums[i];
                } else {  // 还不用分
                    sum += nums[i];
                }
            }
            // 2.1.2 判断最终subarray的数量是否超过m，若超过，则意味着各subarray的元素和都不超过mid的分法不存在
            if (cnt <= m) { // 分法存在
                ans = mid;   // 利用BS去寻找x_0，就是把x_0作为mid去逼近，所以直接将mid赋给ans
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans; // 也可以不设mid，直接返回left，但没有返回ans好理解
    }
}
