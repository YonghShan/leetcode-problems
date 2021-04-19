import java.util.*;

/**
 * @author YonghShan
 * @date 4/14/21 - 21:45
 */
public class Solution1 {
    // One HashMap for storing the num and its according count then sort the HashMap based on value
    /* Runtime: 10ms (faster than 59.62%)    O(max(n, mlogm)) for Collections.sort()
       Memory: 41.8MB (less than 31.15%)     O(m) for HashMap and list
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {    // O(n)
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // Sort the HashMap based on value
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {   // O(mlogm), m is the number of unique elements in the array
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if (o1.getValue() != o2.getValue()) {
                    return o1.getValue() > o2.getValue() ? -1 : 1;   // 降序
                } else {
                    return o1.getKey() > o2.getKey() ? 1 : -1;   // 如果value相同，则依据key的值升序排列
                }
            }
        });

        // Output the first k keys of the sorted HashMap
        int[] res = new int[k];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : list) {   // O(k), k is in the range [1, m].
            res[i++] = entry.getKey();
            if (i == k) break;
        }

        return res;
    }
}
