import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 2/18/21 - 11:42
 */

class Solution2 {
    // Recursion
    /* Runtime: 5ms
       Memory: 39.4MB
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = postorder.length;
        if (len == 0) return null;
        // Set the last element in postorder as the root of the tree
        TreeNode root = new TreeNode(postorder[len-1]);

        //Termination request for recursion
        if (len == 1) {
            if (inorder[0] == postorder[0]) return root;
            return null;
        }

        // Elements in inorder before the root.val is considered as the left subtree
        // Elements in inorder after the root.val is considered as the right subtree
        int idx = 0;
        for (int ele : inorder) {
            if (ele == root.val) break;
            idx++;
        }
        int[] leftSubIn = Arrays.copyOfRange(inorder, 0, idx); // left subtree of inorder
        int[] rightSubIn = Arrays.copyOfRange(inorder, idx+1, len);
        // Divide the postorder into left subtree and right subtree
        int[] leftSubPost = Arrays.copyOfRange(postorder, 0, idx); // left subtree of postorder
        int[] rightSubPost = Arrays.copyOfRange(postorder, idx, len-1);


        // Construct the tree
        root.left = buildTree(leftSubIn, leftSubPost);
        root.right = buildTree(rightSubIn, rightSubPost);

        return root;
    }
}
