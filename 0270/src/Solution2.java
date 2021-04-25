import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/24/21 - 22:50
 */
public class Solution2 {
    // First, do a inorder traversal by recursion to get the list of all node values
    // Second, define a compare() which bases on the difference between node value and target.
    /* Runtime: O(n) for inorder traversal and linear search
       Memory: O(n) for the list of all node values
     */
    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int closestValue(TreeNode root, double target) {
        List<Integer> nums = new ArrayList();
        inorder(root, nums);
        return Collections.min(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });
    }
}
