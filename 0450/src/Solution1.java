/**
 * @author YonghShan
 * @date 4/5/21 - 19:38
 */
public class Solution1 {
    // 最后有问题没解决
    // Algorithm: delete Node z from the BST
    // case 1: z has no child => let z.parent point to null
    // case 2: z has one child => let z.parent point to z.child
    // case 3: z has two child => Step 1: find the successor y of z
    //                            Step 2: case 1: y is z.right => replace z by y
    //                                    case 2: y is in the right subtree of z but not z.right => replace y by its own child and then replace z by y
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root.val == key ? null : root;

        // Step 1: find the target Node in the BST   Attention: 没有必要找target Node的parent！
        TreeNode target = root;
        while (target != null && key != target.val) target = (key < target.val) ? target.left : target.right;

        // Step 2: based on the number of its child to determine the operation
        if (target.left == null && target.right == null) {      // case 1: no child
            target = null;
        } else if (target.left != null && target.right == null) {  // case 2.1: only has left child
            target = target.left;
        } else if (target.right != null && target.left == null) {  // case 2.2: only has right child
            target = target.right;
        } else { // case 3: has two children
            // find the successor
            TreeNode tmp = target.right;
            while (tmp.left != null) tmp = tmp.left;
            // replace target by its successor
            target.val = tmp.val;
            // replace successor by its own child
            // tmp = tmp.right;  直接这样就行
            tmp = (tmp.right == null) ? null : tmp.right; // successor只可能有右孩子   // 只是修改了tmp，并没有修改到BST中对应successor
            // 这里没有修改到BST中对应的Node，是因为只是修改了tmp的指向，tmp由指向BST中的Node，改为指向null
            }

        return root;
    }
}
