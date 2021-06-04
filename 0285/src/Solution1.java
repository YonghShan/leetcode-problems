
/**
 * @author YonghShan
 * @date 6/3/21 - 00:58
 */
public class Solution1 {
    // 写完就是很后悔，不如直接看答案
    // 看完答案Update：不需要Step 1，LeetCode Approach # 1就是Step 2部分的优化，见Solution1Advanced
    /* Runtime: 5ms (faster than 7.03%)      O(n^2)
       Memory: 46.2MB (less than 5.15%)      O(1)
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // Step 1: 利用BST的特性，快速定位p在tree中的位置    worst case: completely unbalanced BST: O(n)
        TreeNode pointer = root;
        while (pointer != null) {
            if (pointer.val > p.val) {
                pointer = pointer.left;
            } else if (pointer.val < p.val){
                pointer = pointer.right;
            } else {
                break;
            }
        }

        // Step 2: 找p的successor，两种情况：
        //    1. p有右子树：successor为其右子树的leftmost
        //    2. p无右子树：
        //       2.1 p在root的左子树中：找p的root，若p.root.val > p.val，则返回p.root，否则返回p.root.root ...，直到大于p.val
        //       2.2 p在root的右子树中：找p的root，若p.root.val > p.val，则返回p.root，否则返回null
        //       2.3 p为root：此时tree中只有root一个node，返回null
        if (pointer.right != null) {            // case 1       worst case: completely unbalanced BST: O(n)
            pointer = pointer.right;
            while (pointer.left != null) {
                pointer = pointer.left;
            }
            return pointer;
        } else if (pointer.val == root.val) {   // case 2.3     O(1)
            return null;
        } else if (pointer.val < root.val){     // case 2.1     worst case: completely unbalanced BST: O(n^2)
            TreeNode pRoot = findRoot(root, pointer);
            while (pRoot.val < pointer.val) {
                pRoot = findRoot(root, pRoot);
            }
            return pRoot;
        } else {                                // case 2.1     worst case: completely unbalanced BST: O(n)
            TreeNode pRoot = findRoot(root, pointer);
            return pRoot.val > pointer.val ? pRoot : null;
        }
    }

    public TreeNode findRoot(TreeNode root, TreeNode node) {    // worst case: completely unbalanced BST: O(n)
        TreeNode pointer = root;
        while (pointer != null) {
            if ((pointer.left != null && pointer.left.val == node.val) || (pointer.right != null && pointer.right.val == node.val)) {   // 防止nullPointer error
                return pointer;
            } else if (pointer.val > node.val) {
                pointer = pointer.left;
            } else if (pointer.val < node.val) {
                pointer = pointer.right;
            }
        }

        return null;
    }
}
