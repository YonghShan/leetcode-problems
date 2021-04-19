import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/15/21 - 02:31
 */

class Solution4 {
    // Morris Traversal
    /* 1. Initialize current as root
       2. While current is not NULL
            If current does not have left child
                a. Print current’s data
                b. Go to the right, i.e., current = current->right
            Else
                a. In current's left subtree, make current the right child of the rightmost node
                b. Go to this left child, i.e., current = current->left
     */
    public List< Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList< >();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {  // 左边为空，则记录curr.val, curr = curr.right
                res.add(curr.val);
                curr = curr.right;
            } else { // 左边不空，则进入左子树，将curr作为左子树中最右node的right，然后curr = curr.left
                pre = curr.left;
                while (pre.right != null) { // 找到最右node (rightmost node)
                    pre = pre.right;
                }
                pre.right = curr; // 将curr作为最右node的right
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}