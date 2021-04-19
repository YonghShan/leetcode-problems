import java.util.HashSet;

/**
 * @author YonghShan
 * @date 4/8/21 - 23:09
 */
public class Solution1 {
    // isHappy中判断是否出现循环且结果是否为1（已经得到1时，也会继续得1，所以最后要判断一下是否为1）
    // getNext(): 求出下一个数：19 -> 1^2 + 9^2 = 82
    /* Runtime: 1ms     O(logn)    详细分析：https://leetcode.com/problems/happy-number/solution/
       Memory: 36.1MB   O(logn) for HashSet. Closely related to the time complexity, and is a measure of what numbers we're putting
                                             in the HashSet, and how big they are. For a large enough n, the most space will be taken by n itself.
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n); // 2. 每一次getNext()，都是在前面logn的基础上log => Total Time Complexity: O(logn) + O(loglogn) + O(logloglogn) + ... = O(logn)
        }

        return n == 1;
    }

    public int getNext(int n) {
        int res = 0;
        while (n > 0) { // 1. n的位数是logn（底为10 ?），所以getNext()的Time Complexity是O(logn)   the number of digits in a number is given by logn.
            int d = n % 10;
            n /= 10;
            res += d * d;
        }

        return res;
    }
}
