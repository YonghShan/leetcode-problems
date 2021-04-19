import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 1/31/21 - 00:09
 */

class Solution3 {
    // Again, it's not an in-place operation!!!!
    // You just CREATED a HashSet, the size of which can be the same as the Input Array!!!!
    // Time Complexity: O(N^2)     Space Complexity: O(N)
    /* Runtime: 2ms
       Memory: 37MB
     */
    // 完整思路是：因为题目要求return the minimum number，
    // 对于不符合non-decreasing的第一个元素（如果有多个相同的，一定要从最左边的开始，e.g. [1,2,2,2,2,2,1,2,2,1] 要从index=1的2开始交换），
    // 要将其和其右边最小的元素互换（如果最小值有多个，一定要取最右边的！！e.g. 上例中index=1的2要和index=9的1交换）
    public int heightChecker(int[] heights) {
        int len = heights.length;
        int i = 0;
        Set<Integer> num = new HashSet<>();
        boolean isOrdered = true; // 默认不需要排序

        while (i < len-1) {
            if (heights[i] > heights[i+1]) {
                isOrdered = false; // 只要进入这个循环，说明有交换过程发生，即原数组并不是ordered
                // 这里的判断，是为了保证有多个相同的值时，可以取到最左边的（即index最小的）
                for(int k = 0; k<i; k++) {
                    if (heights[k] == heights[i]) {
                        i = k;
                        break;
                    }
                }
                int smallestIndex = findSmallest(heights, i); // 找到其右边最小（且index最大）的数
                // 交换这两个元素
                int temp = heights[i];
                heights[i] = heights[smallestIndex];
                heights[smallestIndex] = temp;
                // 记录每次交换的数的index -> 交换结束后，统计有多少个unique的index，就是一共涉及几个数
                num.add(i);
                num.add(smallestIndex);
                i = 0;
            } else {
                i++;
            }
        }

        return isOrdered? 0 : num.size(); // 0是Input array不需要排序的情况
    }

    public int findSmallest(int[] heights, int i){
        int index = i+1;
        int min = heights[index];
        for (int j = i+1; j < heights.length; j++) {
            if (heights[j] <= min) { // 这里取<=，是为了保证右边有多个相同的最小值时，可以取到最右边的（即index最大的）
                min = heights[j];
                index = j;
            }
        }
        return index;
    }
}

