import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author YonghShan
 * @date 2/15/21 - 02:09
 */

public class Solution3 {
    // Iteration2: 更简洁
    public List< Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList< >();
        Stack< TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // 思路：一直向左，当左边为空时，记录root的值，然后通过curr = curr.right进入右边，再重复前面操作
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}