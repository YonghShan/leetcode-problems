
/**
 * @author YonghShan
 * @date 3/2/21 - 17:10
 */
public class Solution5 {
    // Mathematics: Lagrange's four-square theorem + Legendre's three-square theorem
    /* Runtime: 0ms (faster than 100%)      O(sqrt(n)) = O(m)
       memory: 35.9MB (less than 98.49%)    O(1)
     */
    protected boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }

    public int numSquares(int n) {
        // 判断n是否满足4^k(8m+7)的形式
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;
        // 判断n是否自身即为perfect square
        if (this.isSquare(n))
            return 1;
        // 判断n是否为两个perfect square之和
        for (int i = 1; i * i <= n; ++i) {      // 这一步用到了O(sqrt(n))个iteration
            if (this.isSquare(n - i * i))
                return 2;
        }
        // bottom case of three-square theorem.
        return 3;
    }
}
