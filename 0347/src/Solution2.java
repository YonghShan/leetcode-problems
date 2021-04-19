import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 4/14/21 - 22:53
 */
public class Solution2 {
    // 和Solution1的思路一样，只是建了一个Heap来实现按照value的值排序
    /* Runtime: 11ms (faster than 38.20%)      O(Nlogk) if k < N
                                           and O(N) in the particular case of N = k. That ensures time complexity to be better than O(NlogN).
       Memory: 41.6MB (less than 56.35%)       O(N+k) to store the hash map with not more N elements and a heap with k elements.
     */
    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>(          // 根据其value的值，来决定key在heap中的排序
                (n1, n2) -> count.get(n1) - count.get(n2)); // 这里是依据value升序排列，即'the less frequent element first'

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll(); // 将最不frequent的element移出
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }
}
