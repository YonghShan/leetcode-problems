import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 1/29/21 - 16:02
 */
class Solution2 {
    // HashSet (No need to use HashMap because we only need to store the values themselves nothing else)
    /* Runtime: 1ms
       Memory: 38M
     */
    public boolean checkIfExist(int[] arr) {
        int len = arr.length;

        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(num*2) || (num%2==0 && set.contains(num/2))) return true;
            set.add(num);
        }

        return false;
    }
}
