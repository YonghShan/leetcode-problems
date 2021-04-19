/**
 * @author YonghShan
 * @date 4/5/21 - 22:50
 */
public class Solution2 {
    // Recursion
    /* Runtime: 0ms   O(logN)
       Memory: 39.3MB (less than 72.17%)  两种方法几乎无差别   O(logN)
     */
    public int findSuccessor(TreeNode root) {
        // 第一次向右，之后一直向左
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    public int findPredecessor(TreeNode root) {
        // 第一次向左，之后一直向右
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else { // 找到key所对应的Node
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) { // 当右孩子存在时，左子树中的Node是没机会替代的
                root.val = findSuccessor(root); // 用targetNode的successor代替targetNode
                root.right = deleteNode(root.right, root.val); // 将原targetNode的右子树中的successor删去
            } else { // 当右孩子不存在时，才考虑从左子树中找寻替代
                root.val = findPredecessor(root); // 用targetNode的predecessor代替targetNode
                root.left = deleteNode(root.left, root.val); // 将原targetNode的左子树中的predecessor删去
            }
        }

        return root;
    }

    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else { // 找到key所对应的Node
            if (root.left != null && root.right != null) { // root有两个孩子
                root.val = findSuccessor(root); // 用targetNode的successor代替targetNode
                root.right = deleteNode(root.right, root.val); // 将原targetNode的右子树中的successor删去
            } else { // root要么只有一个孩子，要么无孩子：此时，直接用其孩子代替它就行，不用找前驱后继，下面的实现方法也不需要去找其parent（不用trace back）
                root = root.left != null ? root.left : root.right;
            }
        }

        return root;
    }
}
