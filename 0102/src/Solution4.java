import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 2/15/21 - 21:36
 */

class Solution4 {
    // Iteration with Queue (FIFO) (可以换成其他FIFO的容器): 当Queue队首node移除时，将其left和right存入Queue的队尾
    /* Runtime: 1ms     O(n)
       Memory: 39.4MB   O(n)
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            res.add(new ArrayList<Integer>());

            int level_size = queue.size();
            for(int i = 0; i < level_size; i++) {
                TreeNode curr = queue.remove();
                res.get(res.size()-1).add(curr.val); // res.size()-1 表示的是res内最后一个level，也就是一进while循环新add的List<Integer>

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return res;
    }
}