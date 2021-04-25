import java.util.Collections;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/24/21 - 16:25
 */
public class Solution1 {
    // 将arr按照与x的close程度重新排序，取前k个作为输出
    /* Runtime: O(nlogn) for Collections.sort()
       Memory: O(k) for generating a k length sublist
     */
    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        Collections.sort(arr, (a, b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
        arr = arr.subList(0, k);  // subList() returns a view, not a separate copy, so it only takes O(1)
        Collections.sort(arr);
        return arr;
    }
}
