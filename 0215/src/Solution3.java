import java.util.PriorityQueue;

/**
 * @author YonghShan
 * @date 3/25/21 - 23:15
 */
public class Solution3 {
    // 建立一个smallest first的heap（即：小在前，大在后）
    // 依次向heap中加入nums的元素，但保证heap的大小不超过k，如果超过，则poll() (移出head，但是因为smallest first，所以head是当前heap中的最小值)
    // nums中的元素加完后，此时heap的head即为第k大的元素
    /* Runtime: 4ms (faster than 62.74%)    O(n log k), The time complexity of adding an element in a heap of size k is O(logk),
                                            and we do it n times that means O(nlogk) time complexity for the algorithm.
       Memory: 39.1MB (less than 83.45%)    O(k) for heap
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);   // 因为pq默认是升序，所以可以不用写
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();
    }
}
