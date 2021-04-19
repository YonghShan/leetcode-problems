import java.util.*;

/**
 * @author YonghShan
 * @date 2/16/21 - 16:56
 */

class Solution1 {
    // Brute Force： 记录所有path的sum
    /* Runtime: 2ms    O(n)  每个node只访问一次
       Memory: 38.6MB  O(log(n)) in best case; O(n) in worst case
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        ArrayList<Integer> sumSet = new ArrayList<>();
        int sum = root.val;

        while (root != null) {
            if (root.left ==null && root.right == null) sumSet.add(sum);
            if (root.right != null) {
                stack.addFirst(root.right);
                map.put(root.right, sum+root.right.val);
            }
            root = root.left;
            // 题目要求的是：root-leaf的一条path，leaf要没有child。所以不能当root = root.left之后此时的root等于null就更新sumSet，
            // 因为此时root的parent可能还有右孩子，所以要在一进while循环，这个node无左右孩子时，才更新sumSet
            if (root != null) sum += root.val;
            if (root == null && !stack.isEmpty()) {
                root = stack.pop();
                sum = map.get(root);
            }
        }

        return sumSet.contains(targetSum);
    }
}