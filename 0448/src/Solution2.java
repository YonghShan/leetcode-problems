import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/2/21 - 17:20
 */

class Solution2 {
    // 新建一个数组arr[nums.length]放原数据（顺序且distinct），一个List放arr中值为0的index（missing numbers）
    /* Runtime: 6ms   O(n)
       Memory: 64MB   O(n)
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        int[] arr = new int[len];
        List<Integer> list = new ArrayList<>();

        for (int num : nums) arr[num-1] = num;
        for (int i = 0; i< len; i++) {
            if (arr[i] == 0) list.add(i+1);
        }

        return list;
    }
}