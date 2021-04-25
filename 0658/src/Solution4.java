import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/24/21 - 16:49
 */
public class Solution4 {
    // Binary Search Template 2: genius solution
    /* Runtime: 3ms (faster than 95.78%)   O(logn+k)
       Memory: 40.4MB (less than 90.37%)   O(k) for generating a k length sublist
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // O(log(n-k)) = O(logn)
        int lo = 0, hi = arr.length - k;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (x - arr[mid] > arr[mid+k] - x)
                lo = mid + 1;
            else
                hi = mid;
        } // after the loop, lo points to the first element that should be put into result.

        // O(k)
        List<Integer> list = new ArrayList<>();
        for(int i = lo; i < lo + k; i++) list.add(arr[i]);
        return list;
    }
}
