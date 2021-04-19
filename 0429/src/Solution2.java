import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/28/21 - 15:19
 */
public class Solution2 {
    // Iteration: make a new list on each iteration instead of using a single queue. 关注点是layer
    // This makes the code slightly simpler because we lose the size variable and the counting loop, which are a potential source of off-by-one errors.
    /* Runtime: O(n)
       Memory: O(n)
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<Node> previousLayer = Arrays.asList(root);

        while (!previousLayer.isEmpty()) {
            List<Node> currentLayer = new ArrayList<>();
            List<Integer> previousVals = new ArrayList<>();
            for (Node node : previousLayer) {
                previousVals.add(node.val);
                currentLayer.addAll(node.children);
            }
            result.add(previousVals);
            previousLayer = currentLayer;
        }

        return result;
    }
}
