/**
 * @author YonghShan
 * @date 3/21/21 - 23:05
 */
public class Solution1 {
    // Brute Force: 对每一个bar向两边找寻比它高的最多的bar分别作为leftMax和rightMax（如果哪一侧的bar都低于它本身，则对应的Max就为本身）
    //              则每个bar所能积的水就是min(height[leftMax], height[rightMax])-height[i] （木桶效应）
    /* Runtime: 94ms (faster than 5.13%)   O(n^2)
       Memory: 38.8MB (less than 42.08%)   O(1)
     */
    public int trap(int[] height) {
        int rainNum = 0;
        for (int i = 1; i < height.length; i++) {
            System.out.println(i);
            int leftMax = i;
            int rightMax = i;
            for (int l = i-1; l >=0; l--) {
                if (height[l] > height[leftMax]) leftMax = l;
            }
            for (int r = i+1; r < height.length; r++) {
                if (height[r] > height[rightMax]) rightMax = r;
            }
            System.out.println(leftMax);
            System.out.println(rightMax);
            rainNum += Math.min(height[leftMax], height[rightMax]) - height[i];
            System.out.println(rainNum);
            System.out.println("------");
        }
        return rainNum;
    }
}
