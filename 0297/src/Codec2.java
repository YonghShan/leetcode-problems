import java.util.*;

/**
 * @author YonghShan
 * @date 2/20/21 - 22:45
 */
public class Codec2 {
    // serialize()实现了Inorder Traversal Iteration的String输出，但是不知道怎么Deserialization

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String res = "";
        if (root == null) return "null";

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (p != null) {
            if (p.left != null) {
                stack.addFirst(p);
                p = p.left;
            } else {
                res += "null" + ",";
                res += p.val + ",";
                p = p.right;
            }
            while (p == null && !stack.isEmpty()) {
                res += "null" + ",";
                p = stack.pop();
                res += p.val + ",";
                p = p.right;
            }
        }
        res += "null";

        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

    }
}
