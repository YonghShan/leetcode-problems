import java.util.LinkedList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/18/21 - 19:49
 */
class Solution2 {
    // Backtracking：
    /* Runtime：16ms    O(k * C(N, k)), where C(N, k) = N! / (k! * (N-k)!)
                        add / removeLast operations are constant-time ones
                        and the only consuming part here is to append the built combination of length k to the output.
       Memory：41.9MB   O(C(N, k)) to keep all the combinations for an output.
     */
    List<List<Integer>> output = new LinkedList();
    int n;
    int k;

    public void backtrack(int first, LinkedList<Integer> curr) {
        // if the combination is done
        if (curr.size() == k)
            output.add(new LinkedList(curr));

        for (int i = first; i < n + 1; ++i) {
            // add i into the current combination
            curr.add(i);
            // use next integers to complete the combination
            backtrack(i + 1, curr);
            // backtrack
            curr.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(1, new LinkedList<Integer>()); // 这里的first也就是Solution1的idx
        return output;
    }
}