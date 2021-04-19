import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 3/18/21 - 22:11
 */
public class Solution3 {
    // Iteration: BFS
    /* Runtime: 0ms      O(n)
       Memory: 36.4MB    O(log n) in the best case of completely balanced tree and O(n) in the worst case of completely unbalanced tree, to keep a deque.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) return q == null;
        if (q == null) return false;
        if (p.val != q.val) return false;

        Deque<TreeNode> stackP = new ArrayDeque<>();
        Deque<TreeNode> stackQ = new ArrayDeque<>();
        stackP.addFirst(p);
        stackQ.addFirst(q);
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode tmpP = stackP.poll();
            TreeNode tmpQ = stackQ.poll();
            if (tmpP.val != tmpQ.val) return false;

            // 利用LinkedList实现的Deque允许插入null：和Solution2相同，只是把queue改为deque即可
            // 利用ArrayDeque实现的Deque不允许插入null
            // 所以，先判断tmpP和tmpQ的left/right情况：
            if ((tmpP.left == null && tmpQ.left != null) || (tmpP.left != null && tmpQ.left == null)) return false;
            if ((tmpP.right == null && tmpQ.right != null) || (tmpP.right != null && tmpQ.right == null)) return false;
            // 此时，tmpP/Q的left/right都不为null或都为null，还是要继续判断
            if (tmpP.left != null) stackP.addFirst(tmpP.left);
            if (tmpP.right != null) stackP.addFirst(tmpP.right);
            if (tmpQ.left != null) stackQ.addFirst(tmpQ.left);
            if (tmpQ.right != null) stackQ.addFirst(tmpQ.right);
        }

        return true;
    }
}
