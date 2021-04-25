
/**
 * @author YonghShan
 * @date 4/24/21 - 22:25
 */
public class Solution1 {
    // Binary Search
    /* Runtime: 0ms                         O(height) If it's a balanced BST, then height = logn. In worst case, height can be n, where n is the number of nodes
       Memory: 38.8MB (less than 42.86%)    O(1)
     */
    public int closestValue(TreeNode root, double target) {
        if (root.left == null && root.right == null) return root.val;
        int left = Integer.MIN_VALUE;
        int right = Integer.MAX_VALUE;
        TreeNode mid =root;
        while (mid != null) {
            if (target == mid.val) {
                return mid.val;
            } else if (target < mid.val) {
                right = mid.val;
                mid = mid.left;
            } else {
                left = mid.val;
                mid = mid.right;
            }
        }

        return (target - left) < (right - target) ? left : right;
    }
}
