import java.util.*;

/**
 * @author YonghShan
 * @date 2/16/21 - 21:47
 */

class Solution2 {
    // Iteration: 将Solution1中不断加path上node的值，改为用targetSum减node的值，省去记录所有的和的可能
    /* Runtime: 1ms    O(n)  每个node只访问一次
       Memory: 39MB    O(log(n)) in best case; O(n) in worst case
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        Deque<TreeNode> node_stack = new ArrayDeque<>();
        // 将Solution1中的HashMap换成remain_stack，只要这两个stack同时push同时pop，那么每次pop出来的值就与pop出来的node对应
        Deque<Integer> remain_stack = new ArrayDeque<>();
        int curr_remain = targetSum - root.val;

        while (root != null) {
            if (root.left ==null && root.right == null && curr_remain == 0) return true;
            if (root.right != null) {
                node_stack.addFirst(root.right);
                remain_stack.addFirst(curr_remain - root.right.val);
            }
            root = root.left;
            if (root != null) curr_remain -= root.val;
            if (root == null && !node_stack.isEmpty()) {
                root = node_stack.pop();
                curr_remain = remain_stack.pop();
            }
        }

        return false;
    }
}
