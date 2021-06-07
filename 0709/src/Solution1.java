import java.util.PriorityQueue;

/**
 * @author YonghShan
 * @date 6/6/21 - 22:50
 */
class KthLargest {
    // Priority Queue 维护一个min heap
    /* Runtime: 13ms (faster than 95.38%)
       Memory: 44.1MB (less than 77.38%)
     */
    private PriorityQueue<Integer> pq;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<>();
        this.k = k;
        for (int i : nums) {
            pq.offer(i);
            if (pq.size() > k) pq.poll();
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) pq.poll();
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
