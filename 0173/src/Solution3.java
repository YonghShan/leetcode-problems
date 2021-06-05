import java.util.ArrayDeque;

/**
 * @author YonghShan
 * @date 6/4/21 - 16:37
 */
public class Solution3 {
    // 总的过程相当于iterative inorder traversal，但是一开始只进行最左边的遍历，之后只有在调用next()时才继续剩下（向右）的遍历
    /* Runtime: 15ms (faster than 73.08%)     O(1)  Each node gets pushed and popped exactly once in next() when iterating over all N nodes.
                                                    That comes out to 2N * O(1) over N calls to next(), making it O(1) on average, or O(1) amortized.
       Memory: 42.4MB (less than 72.18%)      O(n) for stack
     */
    private ArrayDeque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new ArrayDeque<>();
        this.leftmostInorder(root);
    }

    public void leftmostInorder(TreeNode root) {
        if (root == null) return;
        while (root != null) {
            stack.addFirst(root);
            root = root.left;
        }
    }

    public int next() { // amortized TC: O(1)
        TreeNode smallest = stack.pop(); // O(1)
        if (smallest.right != null) leftmostInorder(smallest.right);  // O(n)（但是不是每次都要走这一步，而且即使走了这一步也不是完全takes O(n)，而是一个远小于n的数）
                                                                      //     （即使是一个skewed tree，在调用leftmostInorder时takes O(n)，但同时这种情况也只需要调用leftmostInorder一次）
        return smallest.val;
    }

    public boolean hasNext() {  // O(1)
        return stack.size() > 0;
    }
}
