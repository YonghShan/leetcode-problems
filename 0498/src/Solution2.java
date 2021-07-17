/**
 * @author YonghShan
 * @date 7/16/21 - 23:37
 */
public class Solution2 {
    // 和Solution 1一样，只是1中的表达式表示了思考的过程
    // 2中直接进行了规范化，但用Math.max/min(a, b)速度变慢，运行时间变为2ms
    /* Runtime: 2ms (faster than 93.58%)
       Memory: 40.8MB (less than 86.22%)
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length - 1;
        int n = mat[0].length - 1;
        int total = m + n;
        int[] res = new int[(m + 1) * (n + 1)];

        int idx = 0;
        int i = 0;
        for (int sum = 0; sum <= total; sum++) { // O((m+1) * (n+1)) 因为目标是为了把res填满
            if (sum % 2 == 0) {
                i = Math.min(sum,m);
                for (; i >= Math.max(sum-n,0); i--) res[idx++] = mat[i][sum-i];
            } else {
                i = Math.max(sum-n,0);
                for (; i <= Math.min(sum,m); i++) res[idx++] = mat[i][sum-i];
            }
        }

        return res;
    }
}
