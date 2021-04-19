/**
 * @author YonghShan
 * @date 1/19/21 - 23:56
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {

    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
