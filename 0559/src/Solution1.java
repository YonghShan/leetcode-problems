/**
 * @author YonghShan
 * @date 3/28/21 - 16:34
 */
public class Solution1 {
    // Recursion "Bottom-up"
    /* Runtime: 0ms   O(n)
       Memory: 39MB   O(logn)
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        if (root.children.size() == 0) return 1;
        int max = 0;
        for (Node child : root.children) {
            max = Math.max(max, maxDepth(child));
        }
        return max+1;
    }
}
