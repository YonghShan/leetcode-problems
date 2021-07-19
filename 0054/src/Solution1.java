import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 7/17/21 - 11:47
 */
public class Solution1 {
    /* Runtime: 0ms
       Memory: 38.7MB
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int r1 = 0, r2 = matrix.length-1;
        int c1 = 0, c2 = matrix[0].length-1;

        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) res.add(matrix[r1][c]);
            for (int r = r1+1; r <= r2; r++) res.add(matrix[r][c2]);
            // 如果r1 == r2，则说明此次进while循环时，只剩一行元素，已经由第一个for循环结束，无需在进行下面的循环
            // 如果c1 == c2，则说明此次进while循环时，只剩一列元素，已经由第二个for循环结束，无需在进行下面的循环
            if (r1 < r2 && c1 < c2) {
                for (int c = c2-1; c > c1; c--) res.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) res.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }
}
