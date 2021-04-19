/**
 * @author YonghShan
 * @date 3/28/21 - 16:35
 */
public class Solution2 {
    // Recursion "Top-down"
    /* Runtime: 0ms     O(n)
       Memory: 38.9MB   O(logn)
     */
    private int maxDepth = 0;
    public int maxDepth(Node root) {
        if (root == null) return 0;
        helper(root, 1);
        return maxDepth;
    }

    public void helper(Node root, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        for (Node child : root.children) {
            if (child != null) helper(child, depth+1);
        }
    }
}
