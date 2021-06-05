
/**
 * @author YonghShan
 * @date 6/3/21 - 23:53
 */
public class Solution1 {
    // next()参考了[0285] Solution 3：调用next()时，就是在寻找当前pointer的inorder successor
    /* Runtime: 20ms (faster than 10.59%)  O(n)
       Memory: 49.1MB (less than 5.09%)    O(1)
     */
    private TreeNode root;
    private TreeNode pointer = new TreeNode(-1);   // 初始pointer的值要比BST最小node还小
    private int maxValue;  // 找到BST中最大node的值，用以判断hasNext()

    public BSTIterator(TreeNode root) {
        this.root = root;
        this.maxValue = maxNodeValue(root);
    }

    public int maxNodeValue(TreeNode root) {    // 只在constructor中执行一次：O(n) in worst case
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

    public int next() {     // O(n) in worst case
        if (pointer.val == -1) {
            pointer = root;
            while (pointer.left != null) {
                pointer = pointer.left;
            }
        } else {
            TreeNode next = null;
            TreeNode currRoot = root;
            while (currRoot != null) {
                if (pointer.val >= currRoot.val) {
                    currRoot = currRoot.right;
                } else {
                    next = currRoot;
                    currRoot = currRoot.left;
                }
            }
            pointer = next;
        }
        // if (pointer == null) pointer = new TreeNode(-2);
        return pointer.val;
    }

    public boolean hasNext() {  // O(1)
        if (pointer.val == maxValue) return false;
        return true;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
