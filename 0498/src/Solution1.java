/**
 * @author YonghShan
 * @date 7/16/21 - 21:52
 */
public class Solution1 {
    // 依据同一条对角线上idx相加之和固定
    /* Runtime: 1ms (faster than 100%)      O((m+1) * (n+1)) 因为目标是为了把res填满
       Memory: 41MB (less than 73.88%)      O((m+1) * (n+1)) 因为目标是为了把res填满
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length - 1;
        int n = mat[0].length - 1;
        int total = m + n;
        int[] res = new int[(m+1) * (n+1)];

        int idx = 0;
        int i = 0;
        for (int sum = 0; sum <= total; sum++) { // O((m+1) * (n+1)) 因为目标是为了把res填满
            if (sum % 2 == 0) {  // sum为偶数
                // 此时，对角线从左下向右上，所以i（表示行的idx）先大后小，范围要保证不可以越界
                i = sum <= m ? sum : m;
                for (; sum-i <= n && i >= 0; i--) res[idx++] = mat[i][sum-i];
            } else {  // sum为奇数
                // 此时，对角线从右上向左下，所以i（表示行的idx）先小后大，范围要保证不可以越界
                i = sum <= n ? 0 : sum-n;
                for (; i <= Math.min(m, sum); i++) res[idx++] = mat[i][sum-i];
            }
        }

        return res;
    }
}
