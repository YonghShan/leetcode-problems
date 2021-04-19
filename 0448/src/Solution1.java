import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/2/21 - 17:00
 */

class Solution1 {
    // 新建了两个ArrayList，一个放原数据，一个放缺失的数据
    /* 会TLE
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        List<Integer> numsList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < len; i++){
            numsList.add(nums[i]);
        }

        for(int count = 1; count <= len; count++) {
            if(!numsList.contains(count)) list.add(count);
        }
        return list;
    }
}
