import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author YonghShan
 * @date 4/14/21 - 23:21
 */
public class Solution3 {
    // QuickSelect with Lomuto's Partition Algorithm [0912]
    // 如果pivot在进行完QuickSelect后的位置正好是第n-k，则说明pivot右边的元素即为所求
    // 所以，QuickSelect是继续在pivot左边还是右边进行的依据是pivot_index和n-k的关系
    /* Runtime: 9ms (faster than 82.95%)     O(N) in the average case: T(N) = T(N/2) + N.
                                             O(N^2) in the worst case of constantly bad chosen pivots, the problem is not divided by half at each step, it becomes just one element less.
                                             For the random pivot choice the probability of having such a worst-case is negligibly small.
       Memory: 41.4MB (less than 70.57%)     up to O(N) to store hash map and array of unique elements.
     */
    int[] unique;
    Map<Integer, Integer> count;

    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    public int partition(int left, int right, int pivot_index) {   // Lomuto's Partition Algorithm [0912]. Hoare's partition fails with duplicates.
        int pivot_frequency = count.get(unique[pivot_index]);
        // 1. move pivot to end
        swap(pivot_index, right);    // Lomuto的关键是第一步将pivot移至末尾
        int store_index = left;

        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivot_frequency) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }

    public void quickselect(int left, int right, int k_smallest) {
        /*
        Sort a list within left..right till kth less frequent element takes its place.
        */

        // base case: the list contains only one element
        if (left == right) return;

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);  // 避免the worst case

        // find the pivot position in a sorted list
        pivot_index = partition(left, right, pivot_index);

        // if the pivot is in its final sorted position
        if (k_smallest == pivot_index) {
            return;
        } else if (k_smallest < pivot_index) {
            // go left
            quickselect(left, pivot_index - 1, k_smallest);
        } else {
            // go right
            quickselect(pivot_index + 1, right, k_smallest);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        count = new HashMap();
        for (int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // array of unique elements
        int n = count.size();
        unique = new int[n];
        int i = 0;
        for (int num: count.keySet()) {
            unique[i] = num;
            i++;
        }

        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till (n - k)th less frequent element takes its place (n - k) in a sorted array.
        // All the elements on the left are less frequent.
        // All the elements on the right are more frequent.
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }
}
