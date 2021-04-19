import java.util.Arrays;

/**
 * @author YonghShan
 * @date 1/30/21 - 22:06
 */

class Solution1 {
    // 尝试用in-place的做法，但是只能满足部分情况
    // 完整思路是：因为题目要求return the minimum number，对于不符合non-decreasing的元素，要将其和其右边最小的元素互换（如果最小值有多个，一定要取最右边的！！）
    public int heightChecker(int[] heights) {
        int len = heights.length;
        int i = 0;
        // 交换的次数。其实更准确的应该是记录发生交换的元素数。
        // 这里用次数只是简单地假设了第一次交换涉及两个数，之后每次涉及一个新数（错误思想！！21211221每次都涉及两个数）
        int times = 0;
        boolean isOrdered = true; // 默认不需要排序

        while (i < len-1) {
            if (heights[i] > heights[i+1]) {
                int smallestIndex = findSmallest(heights, i); // 找到其右边最小（且index最大）的数
                // 交换这两个元素
                int temp = heights[i];
                heights[i] = heights[smallestIndex];
                heights[smallestIndex] = temp;
                // 要想满足所有的testcases（包括上面的21211221（交换次数两次，每次涉及2个新数，一共涉及4个数）），
                // 要记录每次交换的数的index。交换结束后，统计有多少个unique的index，就是一共涉及几个数
                times++;
                i = 0;
                isOrdered = false; // 只要进入这个循环，说明有交换过程发生，即原数组并不是ordered
            } else {
                i++;
            }
        }

        return isOrdered? 0 : times+1; // 0是Input array不需要排序的情况
    }

    public int findSmallest(int[] heights, int i){
        int smallestIndex = i+1;
        int min = heights[smallestIndex];
        for (int j = i+1; j < heights.length; j++) {
            if (heights[j] <= min) { // 这里取<=，是为了保证右边有多个相同的最小值时，可以取到最右边的（即index最大的）
                smallestIndex = j;
            }
        }
        return smallestIndex;
    }
}
