import java.util.LinkedList;

/**
 * @author YonghShan
 * @date 4/24/21 - 22:53
 */
public class Solution3 {
    // do a inorder traversal by iteration and check the closest node at the same time
    /* Runtime: O(k) in the average case (balanced BST), where k is the index of the closest element: k times to push into stack and then k times to pop out of stack
                O(n+k) in the worst case (completely unbalanced BST): first push H elements into stack and then pop out k elements
       Memory: up to O(k) for the stack
     */
    public int closestValue(TreeNode root, double target) {
        LinkedList<TreeNode> stack = new LinkedList();
        long pred = Long.MIN_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();

            if (pred <= target && target < root.val)
                return Math.abs(pred - target) < Math.abs(root.val - target) ? (int)pred : root.val;

            pred = root.val;
            root = root.right;
        }
        return (int)pred;
    }
}
