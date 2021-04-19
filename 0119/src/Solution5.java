import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/11/21 - 22:31
 */
public class Solution5 {
    // 公式：第i行第j个元素的值等于第i行第j-1个元素的值乘以(i-j+1)/j
    /* Runtime: 0ms     O(rowIndex)
       Memory: 36.4MB   O(rowIndex)
     */
    public List<Integer> getRow(int rowIndex) {
        int i = rowIndex;
        List<Integer> res = new ArrayList<>();;
        res.add(1);
        for (int j = 0; j < rowIndex; j++) {
            res.add((int) ((res.get(j) * (long) (rowIndex-(j+1)+1)) / (j+1)));
        }

        return res;
    }
}
