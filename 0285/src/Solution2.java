import java.util.*;

/**
 * @author YonghShan
 * @date 6/3/21 - 16:52
 */
public class Solution2 {
    // 并没有利用BST inorder traversal得到的结果为升序的特性 （采用ArrayList，原因见notes）  可以为任一Binary Tree中的node寻找successor
    /* Runtime: 5ms (faster than 7.08%)    O(n)
       Memory: 39.7MB (less than 37.36%)   O(n)
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        ArrayList<Integer> res = new ArrayList<>();

        // Step 1: inorder traversal (iteration)        O(n) & O(n)
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pointer = root;
        while (!stack.isEmpty() || pointer != null) {
            while (pointer != null) {
                stack.addFirst(pointer);
                pointer = pointer.left;
            }
            pointer = stack.pop();
            res.add(pointer.val);
            pointer = pointer.right;
        }

        // Step 2: get the successor
        int idx = res.indexOf(p.val);    // O(n)
        return idx == res.size() - 1 ? null : new TreeNode(res.get(idx + 1)); // 因为res中存的是val，所以最后要new一个TreeNode     O(1)
    }

    // res中存的是TreeNode，实际运行时间更久
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        List<TreeNode> res = new ArrayList<>();

        // Step 1: inorder traversal (iteration)
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pointer = root;
        while (!stack.isEmpty() || pointer != null) {
            while (pointer != null) {
                stack.addFirst(pointer);
                pointer = pointer.left;
            }
            pointer = stack.pop();
            res.add(pointer);
            pointer = pointer.right;
        }

        // Step 2: get the successor
        int idx = res.indexOf(p);
        return idx == res.size() - 1 ? null : res.get(idx + 1);
    }
}
