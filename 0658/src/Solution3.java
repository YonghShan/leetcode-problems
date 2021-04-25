import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/24/21 - 16:42
 */
public class Solution3 {
    // Binary Search Template 3: normal way that a normal person will do(binary search + 2 pointers)
    /* Runtime: 3ms (faster than 95.78%)    O(logn+k). O(logn) is for the time of binary search, while O(k) is for shrinking the index range to k elements.
       Memory: 40.4MB (less than 90.37%)    O(k) for generating a k length sublist
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // find the closest element and expand the range: Template 3
        int closestIdx = 0;
        int i = 0;
        int j = arr.length - 1;
        while(i+1 < j){
            int mid = i + (j - i)/2;
            if(arr[mid] > x) j = mid;
            else i = mid;
        } // after the loop, i and j point to the elements that are closest to x
          // [5,7,7,7,8,9,9,10], k=2, x=7 ==> i points to the last 7, and j points to the first 8
        // post-processing
        closestIdx = Math.abs(arr[i] - x) <= Math.abs(arr[j] - x) ? i : j;

        List<Integer> list = new ArrayList<>();
        int count = 1;
        i = closestIdx - 1;
        j = closestIdx + 1;
        while(count < k){
            if(i < 0) j++;
            else if(j >= arr.length) i--;
            else if(Math.abs(arr[i] - x) <= Math.abs(arr[j] - x)) i--;
            else j++;
            count++;
        }

        for(int m = i + 1; m <= j - 1; m++) list.add(arr[m]);
        return list;
    }
}
