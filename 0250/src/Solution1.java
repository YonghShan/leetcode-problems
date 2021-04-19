/**
 * @author YonghShan
 * @date 2/17/21 - 20:51
 */
public class Solution1 {
    // 不能通过所有testcase
    // 这个写法的漏洞在于count的取值：不能仅仅因为root和root.left、root.right的值都相等，就把root的count设为1
    /* e.g.     1           当root=1时，root.val = root.left.val = root.right.val = 1
               / \          按照下面的方法：返回的值为count + countUnivalSubtrees(root.right) + countUnivalSubtrees(root.left)， (count = 1)
              1   1         但正确的答案应该是countUnivalSubtrees(root.right) + countUnivalSubtrees(root.left)，即count应该为0
             / \   \        所以，只有当root.left和root.right都为univalue subtrees，且root和root.left、root.right的值都相等时，root的count才为1
            5  5    5
       还有一种处理方式是：只要子node中有一个的count = 0，则父node的count也为0；如果子node的count都为1，则父node的count由父node和子node的值的关系决定
     */
    public int countUnivalSubtrees(TreeNode root) {
        int count = 1;
        if (root == null) return 0;
        if (root.left == null && root.right == null) return count;
        if (root.left != null && root.val != root.left.val) count = 0;
        if (root.right != null && root.val != root.right.val) count = 0;
        return count + countUnivalSubtrees(root.right) + countUnivalSubtrees(root.left);
    }
}
