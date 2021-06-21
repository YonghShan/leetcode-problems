/**
 * @author YonghShan
 * @date 6/20/21 - 16:26
 */
public class Solution1 {
    // Top-down Recursion: 利用getHeight(TreeNode node)获取每个node的左右子树的root的height
    // 缺点：isBalanced(node.left/right) 会对较低层的node重复调用 getHeight()      (类似Fibonacci Sequence的recursion实现)
    /* Runtime: 1ms (faster than 53.04%)  O(nlogn) 见note
       Memory: 39.3MB (less than 27.36%)  O(n) for the recursion stack
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        return Math.abs(getHeight(root.left) - getHeight(root.right)) < 2 && isBalanced(root.left) && (isBalanced(root.right));
    }

    public int getHeight(TreeNode node) {
        if (node == null) return -1;

        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
}
