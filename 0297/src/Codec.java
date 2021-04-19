import java.util.*;


/**
 * @author YonghShan
 * @date 2/20/21 - 21:01
 */
public class Codec {
    // Preorder Traversal Recursion:
    /* Runtime: 19~48ms
                (in both serialization and deserialization functions,
                we visit each node exactly once, thus the time complexity is O(n))
       Memory: 40.6MB~52.8MB
               (in both serialization and deserialization functions, we keep the entire tree,
               either at the beginning or at the end, therefore, the space complexity is O(n).)
       The solutions with BFS or other DFS strategies normally will have the same time and space complexity.
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String res = "";
        if (root == null) return "null";
        res += root.val + ",";
        res += serialize(root.left) + ",";
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }

    public TreeNode rdeserialize(List<String> l) {
        // Recursive deserialization.
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }
}