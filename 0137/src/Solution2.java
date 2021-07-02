/**
 * @author YonghShan
 * @date 6/29/21 - 22:58
 */
public class Solution2 {
    /* Runtime: 0ms                          O(n)
       Memory: 38.6MB (less than 58.38%)     O(1)
     */
    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            // first appearence: add num to seen_once
            // second appearance: remove num from seen_once, add num to seen_twice
            // third appearance: remove num from seen_twice
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
            // 上述操作见note
        }

        return seenOnce;
    }
}
