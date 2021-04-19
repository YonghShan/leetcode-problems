/**
 * @author YonghShan
 * @date 3/22/21 - 10:29
 */
public class Solution2 {
    // Solution1中leftMax，rightMax都要找：先找出global maximum的index maxIndex，让global maximum作为左半部分（start->maxIndex）的rightMax
    //                                                                                          右半部分（maxIndex->end）的leftMax
    /* Runtime: 0ms     O(2n) = O(n)
       Memory: 38.9MB   O(1)
     */
    public int trap(int[] height) {
        int rainNum = 0;
        int maxIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > height[maxIndex]) maxIndex = i;
        }
        int leftMaxIndex = 0;
        for (int i = 0; i < maxIndex; i++) {
            if (height[i] > height[leftMaxIndex]) {
                leftMaxIndex = i;
            } else {
                rainNum += height[leftMaxIndex] - height[i];
            }
        }
        int rightMaxIndex = height.length-1;
        for (int i = height.length-1; i > maxIndex; i--) {
            if (height[i] > height[rightMaxIndex]) {
                rightMaxIndex = i;
            } else {
                rainNum += height[rightMaxIndex] - height[i];
            }
        }
        return rainNum;
    }
}
