
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 3/18/21 - 21:34
 */
public class Solution2 {
    // Iteration: BFS
    /* Runtime: 0ms      O(n)
       Memory: 36.2MB    O(log n) in the best case of completely balanced tree and O(n) in the worst case of completely unbalanced tree, to keep a queue.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (!check(p, q)) return false;

        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();
        queueP.add(p);
        queueQ.add(q);
        while (!queueP.isEmpty() && !queueQ.isEmpty()) {
            TreeNode tmpP = queueP.poll();
            TreeNode tmpQ = queueQ.poll();
            if (!check(tmpP, tmpQ)) return false;

            // 可以把null放入queue中，但是queue在弹出元素时，如果调用的是poll()，当弹出失败（比如queue此时为空）时，也会返回null，就不知道到底是弹出的元素是加进去的null，还是因为其他因素弹出失败
            // queue在弹出元素时，如果调用的是remove()，当弹出失败（比如queue此时为空）时，会抛出异常
            // add()/remove()/element(): 抛出异常
            // offer()/poll()/peek(): 返回false/null
            // 应当是避免将null加入queue的，但是这里while的条件是都不为空，不用担心用poll()，弹出失败
            if(tmpP != null) {
                queueP.add(tmpP.left);
                queueP.add(tmpP.right);
            }
            if (tmpQ != null) {
                queueQ.add(tmpQ.left);
                queueQ.add(tmpQ.right);
            }
        }

        return true;
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null) return q == null;
        if (q == null) return false;
        if (p.val != q.val) return false;
        return true;
    }
}
