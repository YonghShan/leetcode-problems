/**
 * @author YonghShan
 * @date 3/22/21 - 15:20
 */
public class Solution4 {
    // Solution1中对于每个bar都要找它的leftMax和rightMax，不如一开始从开头向右遍历，记录对于每个index所对应leftMax；再从末尾向左遍历，记录对于每个index所对应的rightMax
    /* Runtime: 1ms     O(3n) = O(n)
       Memory: 38.4MB   O(1)
     */
    public int trap(int[] height) {
        int len = height.length;
        int rainNum = 0;
        if (len == 0) return rainNum; // 这里不return的话，如果len==0，下面leftMax[0]=height[0]会报错
        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }
        int[] rightMax = new int[len];
        rightMax[len-1] = height[len-1];
        for (int i = len-2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }
        for (int i =0; i < len; i++) {
            rainNum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return rainNum;
    }
}
