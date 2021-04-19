import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author YonghShan
 * @date 4/14/21 - 23:26
 */
public class Solution4 {
    // 推荐！！！好理解也好写
    // Bucket Sort: no frequencies can be more than n
    // 一共有n个桶，依据每个key的frequency来确定该key要放在哪个桶里
    /* Runtime: 8ms (faster than 92.29%)    O(N)
       Memory: 41.4MB (less than 70.57%)    O(N) for N buckets
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencies = new HashMap();
        for (int n: nums) {
            frequencies.put(n, frequencies.getOrDefault(n, 0) + 1);
        }

        LinkedList<Integer>[] buckets = bucketSort(frequencies, nums.length);  // no frequencies can be more than n (i.e. nums.length) so max = nums.length

        int[] ans = new int[k];
        int i = 0;

        for (LinkedList<Integer> bucket: buckets) {
            if (bucket == null) continue;

            for (int num: bucket) {
                ans[i++] = num;
                if (i == k) return ans;
            }
        }

        return ans;
    }

    private LinkedList<Integer>[] bucketSort(Map<Integer, Integer> frequencies, int max) {
        LinkedList<Integer>[] buckets = new LinkedList[max];

        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            int num = entry.getKey();
            int frequency = entry.getValue();
            int index = max - frequency;

            if (buckets[index] == null) buckets[index] = new LinkedList<>();
            buckets[index].addFirst(num);
        }

        return buckets;
    }
    //     key:      4 5 2 1 3   ==> max = nums.length = 5 ==> 5 buckets in total
    //  frequency:   1 4 3 3 2
    // bucket_index: 4 1 2 2 3
    // bucket_0: null (只有当nums中所有元素都为相同时，bucket_0才不为null
    // bucket_1: 5
    // bucket_2: 2 1    // 当k=3时，ans为5 2 1
    // bucket_3: 3
    // bucket_4: 4
}
