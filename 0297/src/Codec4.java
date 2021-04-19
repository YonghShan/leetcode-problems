import java.util.StringJoiner;

/**
 * @author YonghShan
 * @date 2/21/21 - 00:17
 */

public class Codec4 {
    // 在leetcode上运行不通过，leetcode更新到Java 13，StringJoiner是Java 8加入的特性
    //对Codec的改进：
    //     1. serialize(): 增加一个helper() + 改用StringJoiner
    //     2. deserialize(): 直接对得到的String array操作，不再变为Linked list

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringJoiner sb = new StringJoiner(",");
        helper1(root, sb);

        return sb.toString();
    }

    void helper1(TreeNode root, StringJoiner sb) {
        if (root == null) {
            sb.add("N");
            return;
        }
        sb.add(String.valueOf(root.val));
        helper1(root.left, sb);
        helper1(root.right, sb);
    }

    int i = 0;  // 引入了static variable，不是stateless

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        i = 0;
        String[] arr = data.split(",");

        return helper2(arr);
    }

    TreeNode helper2(String[] arr) {
        if (arr[i].equals("N")) {
            i++;
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(arr[i++]));
        root.left = helper2(arr);
        root.right = helper2(arr);

        return root;
    }
}
