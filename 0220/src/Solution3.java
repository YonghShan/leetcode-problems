import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/7/21 - 23:46
 */
public class Solution3 {
    // !!! Clever Trick: Inspired by Bucket Sort
    /* Runtime: 18ms (faster than 68.26%)    O(n)
       Memory: 42MB (less than 19.98%)       O(min(n, k))
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        // Use a HashMap<bucketID, elements> to act as all the buckets
        HashMap<Long, Long> buckets = new HashMap<>();
        // capacity of one bucket
        long w = t + 1;
        for (int i = 0; i < nums.length; i++) {    // O(n)：循环内HashMap的操作都是constant time
            long bucketID = getBucketID(nums[i], w);
            if (buckets.containsKey(bucketID)) return true; // 说明该bucket已经被放过element了，同一个bucket内的element之间一定相差小于t; 也保证了每个bucket最多只有一个element
            if (buckets.containsKey(bucketID - 1) && Math.abs(buckets.get(bucketID-1)-nums[i]) <= t) return true;
            if (buckets.containsKey(bucketID + 1) && Math.abs(buckets.get(bucketID+1)-nums[i]) <= t) return true;

            buckets.put(bucketID, (long) nums[i]);
            if (i >= k) buckets.remove(getBucketID(nums[i-k], w));
        }

        return false;
    }

    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    public long getBucketID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }
}
