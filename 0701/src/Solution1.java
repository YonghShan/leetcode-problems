/**
 * @author YonghShan
 * @date 4/5/21 - 16:55
 */
public class Solution1 {
    // Recursion
    /* Runtime: 0ms   T(N) = T(N/2) + \Theta (N^0): a = 1, b = 2, d = 0 ==> O(logN) in the average case and O(N) in the worst case
       Memory: 39.5MB (less than 80.95%)   O(logN) in the average case and O(N) in the worst case
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else { // 这里因为题目的限制，保证了val一定不存在于BST，所以少一个相等的判断
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }
}
