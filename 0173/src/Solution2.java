import java.util.ArrayList;

/**
 * @author YonghShan
 * @date 6/4/21 - 16:05
 */
public class Solution2 {
    // 先进行一次inorder traversal，为根据遍历的结果编写iterator
    // 学习下next()和hasNext()的写法，代码非常简练
    /* Runtime: 15ms (faster than 72.76%)   O(n)
       Memory: 42.9MB (less than 27.10%)    O(n)
     */
    private int index;
    private ArrayList<Integer> res;

    public BSTIterator(TreeNode root) {
        this.index = -1;
        this.res = new ArrayList<>();
        // Call to flatten the input binary search tree
        this.inorderTraversal(root);
    }

    public void inorderTraversal(TreeNode root) {      // O(n) and O(n)
          if (root == null) return;

          this.inorderTraversal(root.left);
          this.res.add(root.val);
          this.inorderTraversal(root.right);
    }

    public int next() {  // O(1)
        return res.get(++index);
    }

    public boolean hasNext() {  // O(1)
        return index + 1 < res.size();
    }
}
