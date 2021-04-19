/**
 * @author YonghShan
 * @date 4/9/21 - 00:23
 */
public class Solution4 {
    // Clever trick based on Solution3
    // 缺点：如果n先变为16，则要继续进行循环，直到取到4，这要比Solution3慢些
    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        while (n != 1 && n != 4) { // 既然the only cycle中有4，则只要变成4就一定会陷入the only cycle
            n = getNext(n);
        }
        return n == 1;
    }
}
