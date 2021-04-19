import java.util.HashSet;

/**
 * @author YonghShan
 * @date 4/9/21 - 00:04
 */
public class Solution2 {
    // isHappy中判断是否出现循环且结果是否为1（已经得到1时，也会继续得1，所以最后要判断一下是否为1）
    // getNext(): 求出下一个数：19 -> 1^2 + 9^2 = 82
    // 利用Floyd's Cycle-Finding Algorithm省去HashSet的使用
    /* Runtime: 1ms     O(logn)    详细分析：https://leetcode.com/problems/happy-number/solution/
       Memory: 36.1MB   O(1)
     */
    public boolean isHappy(int n) {
        int slow = n;          // slow是🐢，每次只走一步
        int fast = getNext(n); // fast是🐰，每次走两步 这里🐰和🐢没有统一起跑线，是为了进入下面的while循环
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }

    public int getNext(int n) {
        int res = 0;
        while (n > 0) {
            int d = n % 10;
            n /= 10;
            res += d * d;
        }

        return res;
    }
}
