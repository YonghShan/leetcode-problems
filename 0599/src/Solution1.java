import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/9/21 - 16:39
 */
public class Solution1 {
    // 官方答案实在傻逼
    // HashMap
    /* Runtime: 7ms (faster than 73.17%)    O(l1 + l2), where l1 = list1.length; l2 = list2.length
       Memory: 39.8MB (less than 32.31%)    O(l1 + x), where x = the number of common interests with the minimum index sum
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        int min = Integer.MAX_VALUE;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {  // 找到共同的interest
                int idxSum = i + map.get(list2[i]);
                if (idxSum < min) {
                    min = idxSum;
                    list.clear();
                    list.add(list2[i]);
                } else if (idxSum == min) {
                    list.add(list2[i]);
                }
            }
        }

        return list.toArray(new String[0]);
    }
}
