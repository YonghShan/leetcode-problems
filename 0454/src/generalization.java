import java.util.HashMap;
import java.util.Map;

/**
 * @author YonghShan
 * @date 4/14/21 - 21:23
 */
public class generalization {
    // kSum II: 前一半的arrays用来构建HashMap，后一半的arrays用来find是否存在complement of the key in the HashMap
    /* Runtime: O(n^k) ~ O(n^(k/2))
                If the number of arrays is odd, the time complexity will be O(n^(k/2)). We will pass k/2 arrays to addToHash, and (k+1)/2 arrays to kSumCount to keep the space complexity O(n^(k/2)).
                If we are tight on memory, we can move some arrays from the first group to the second. This, of course, will increase the time complexity.
       Memory: O(1) ~ O(n^(k/2)) accordingly
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        return kSumCount(new int[][]{A, B, C, D});
    }
    public int kSumCount(int[][] lists) {
        Map<Integer, Integer> m = new HashMap<>();
        addToHash(lists, m, 0, 0);
        return countComplements(lists, m, lists.length / 2, 0);
    }
    void addToHash(int[][] lists, Map<Integer, Integer> m, int i, int sum) {  // 前一半的arrays用来构建HashMap：两两arrays之间，所有elements要做到全连接
        if (i == lists.length / 2)
            m.put(sum, m.getOrDefault(sum, 0) + 1);
        else
            for (int a : lists[i])
                addToHash(lists, m, i + 1, sum + a);
    }
    int countComplements(int[][] lists, Map<Integer, Integer> m, int i, int complement) {  // 后一半的arrays用来find the complements
        if (i == lists.length)
            return m.getOrDefault(complement, 0);
        int cnt = 0;
        for (int a : lists[i])
            cnt += countComplements(lists, m, i + 1, complement - a);
        return cnt;
    }
}
