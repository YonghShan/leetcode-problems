import java.util.Arrays;

/**
 * @author YonghShan
 * @date 2/18/21 - 12:14
 */

class Solution1 {
    // Recursion
    /* Runtime: 5ms
       Memory: 39.2MB
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if (len == 0) return null;
        // Set the first element in preorder as the root of the tree
        TreeNode root = new TreeNode(preorder[0]);

        //Termination request for recursion
        if (len == 1) {
            if (preorder[0] == inorder[0]) return root;
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
        int[] leftSubPre = Arrays.copyOfRange(preorder, 1, idx+1); // left subtree of preorder
        int[] rightSubPre = Arrays.copyOfRange(preorder, idx+1, len);


        // Construct the tree
        root.left = buildTree(leftSubPre, leftSubIn);
        root.right = buildTree(rightSubPre, rightSubIn);

        return root;
    }
}

