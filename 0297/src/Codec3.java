import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 2/20/21 - 23:01
 */
public class Codec3 {
    // BFS (Level-Order Traversal)
    // Encodes a tree to a single string.
    /* Runtime: 21ms
       Memory: 51.8MB
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            TreeNode first = q.poll();
            sb.append((first!=null ? first.val : "null") + ",");
            if(first!=null){
                q.add(first.left);
                q.add(first.right);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = getTreeNode(nodes[0]); //it will always have one value at least
        q.add(root);
        int i = 1;

        while(!q.isEmpty()){
            TreeNode first = q.poll();
            if(first!=null){
                first.left = getTreeNode(nodes[i++]);
                first.right = getTreeNode(nodes[i++]);
                // 上一步生成了root的孩子，就把它们再放进q中，下一个回合为他们生成孩子
                if(first.left!=null) q.add(first.left);
                if(first.right!=null) q.add(first.right);
            }
        }
        return root;
    }

    private TreeNode getTreeNode(String val){
        if(val.equals("null")) return null;
        return new TreeNode(Integer.valueOf(val));
    }
}