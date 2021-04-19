/**
 * @author YonghShan
 * @date 3/22/21 - 15:41
 */
public class Solution5 {
    // Two pointer: left和right两个指针一头一尾，初始leftMax和rightMax都为0，假设左边初始指向的值大于右边的值（第一个bar高于最后一个bar），
    // 则right向左移动（并更新rightMax），期间遇到比rightMax低的bar就更新rainNum直到指向的值大于left的值，然后开始移动left向右移动，更新leftMax和计算rainNum
    /* Runtime: 0ms     O(n)
       Memory: 38.2MB   O(1)
     */
    public int trap(int[] height) {
        int len = height.length;
        int left = 0, right = len-1;
        int leftMax= 0, rightMax = 0;
        int rainNum = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    rainNum += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    rainNum += rightMax - height[right];
                }
                right--;
            }
        }
        return rainNum;
    }
}
