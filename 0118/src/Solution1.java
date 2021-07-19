import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/10/21 - 12:20
 */
public class Solution1 {
    // Iteration:
    /* Runtime: 0ms (faster than 100%)       O(numRows^2)
       Memory: 36.7MB (less than 80.56%)
     */
    // TC Analysis: 很明显outer loop runs numRows times；而每一次inner loop循环的次数等于rowNum (因为帕斯卡三角第一行有一个元素，第二行有两个元素，...)
    // ==>> 总的次数为1+2+3+...+numRows = numRows(numRows+1)/2 = numRows^2/2 + numRows/2 = O(numRows^2)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);  // the first digit is always 1

            for (int j = 1; j < i; j++) {
                row.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
            }

            if (i > 0) row.add(1); // Once the length of this row is bigger than 1, then the last digit is always 1
            res.add(row);
        }

        return res;
    }
}
