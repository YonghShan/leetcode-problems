import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 2/18/21 - 00:08
 */

class Solution {
    // Recursion but with so many redundant operations
    /* Runtime: 325ms
       Memory: 41.7MB
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
        HashMap<Integer, Integer> indexTable = new HashMap<>();
        int idx = 0;
        for (int ele : inorder) {
            indexTable.put(ele, idx);
            idx++;
        }
        // Cannot use int[] to be leftSub and rightSub since you don't know how long they will be and array is static
        ArrayList<Integer> leftSubIn = new ArrayList<>(); // left subtree of inorder
        ArrayList<Integer> rightSubIn = new ArrayList<>();
        for (int i = 0; i < indexTable.get(root.val); i++) leftSubIn.add(inorder[i]);
        for (int i = indexTable.get(root.val)+1; i < len; i++) rightSubIn.add(inorder[i]);

        // Divide the postorder into left subtree and right subtree
        ArrayList<Integer> leftSubPost = new ArrayList<>(); // left subtree of postorder
        ArrayList<Integer> rightSubPost = new ArrayList<>();
        for (int i = 0; i < leftSubIn.size(); i++) leftSubPost.add(postorder[i]);
        for (int i = leftSubIn.size(); i < len-1; i++) rightSubPost.add(postorder[i]);

        // Construct the tree
        root.left = buildTree(leftSubIn.stream().mapToInt(Integer::intValue).toArray(), leftSubPost.stream().mapToInt(Integer::intValue).toArray());
        root.right = buildTree(rightSubIn.stream().mapToInt(Integer::intValue).toArray(), rightSubPost.stream().mapToInt(Integer::intValue).toArray());

        return root;
    }
}