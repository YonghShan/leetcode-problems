/**
 * @author YonghShan
 * @date 6/3/21 - 18:18
 */
public class Solution1Advanced {
    // ==推荐==
    // 不需要在BST中定位p，题目描述中：Given the root of a binary search tree and a node p ==in it==
    // 确实对应两种case，一个是p有右孩子，一个是p无右孩子。但对于p无右孩子的情况，不需要再细分为三
    /* Runtime: 1ms (faster than 100%)    O(n)
       Memory: 39.5MB (less than 75.90%)  O(n) stack for case 2 with the worst case
     */
    private TreeNode previous;   // inorder traversal的过程中，排在当前node前面一个的node
    private TreeNode inorderSuccessorNode;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // Case 1: 对应Solution1 Step2 case1         worst case: completely unbalanced BST: O(n)
        if (p.right != null) {
            TreeNode leftmost = p.right;
            while (leftmost.left != null) {
                leftmost = leftmost.left;
            }
            this.inorderSuccessorNode = leftmost;
        } else {
            // Case 2: We need to perform the standard inorder traversal and keep track of the previous node.
            this.inorderCase2(root, p);
        }

        return this.inorderSuccessorNode;
    }

    // ==记住==
    // 当p有右子树且其右子树中只有一个node时，也可通过inorderCase2得到successor，但其他情况不可
    private void inorderCase2(TreeNode node, TreeNode p) {      // worst case: completely unbalanced BST: O(n)
        // 不可省略
        if (node == null) return;

        // Recurse on the left side
        this.inorderCase2(node.left, p);

        // Check if previous is the inorder predecessor of node
        if (this.previous == p && this.inorderSuccessorNode == null) {
            this.inorderSuccessorNode = node;
            return;
        }

        // Keeping previous up-to-date for further recursions
        this.previous = node;

        // Recurse on the right side
        this.inorderCase2(node.right, p);
    }
}
