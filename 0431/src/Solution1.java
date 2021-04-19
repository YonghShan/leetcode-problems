import java.util.*;

/**
 * @author YonghShan
 * @date 3/30/21 - 22:07
 */
public class Solution1 {
    // Algorithm documented on the Wikipedia https://en.wikipedia.org/wiki/M-ary_tree#Convert_a_m-ary_tree_to_binary_tree
    // Step 1: Link all siblings of one parent node
    // Step 2: Link the first child with the parent node
    // BFS
    /* Runtime: 8ms （faster than 5.88%)     O(n)
       Memory: 40.5MB (less than 81.90%)     ATTENTION: 这个方法和官方方法不同，这里用了HashMap，Space Complexity: O(n), since each node has stored in the map.
     */
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null) return null;

        TreeNode newTreeNodeRoot = new TreeNode(root.val);
        Map<Node, TreeNode> mapping = new HashMap<>();
        mapping.put(root, newTreeNodeRoot);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node tmpNode = queue.poll();
            TreeNode tmpTreeNode = mapping.get(tmpNode);

            TreeNode headTreeNode = null;
            TreeNode prevTreeNode = null;
            for (Node child : tmpNode.children) {
                queue.add(child);
                TreeNode currTreeNode = new TreeNode(child.val);
                mapping.put(child, currTreeNode);
                // Link all siblings
                if (prevTreeNode == null) {
                    headTreeNode = currTreeNode;
                } else {
                    prevTreeNode.right = currTreeNode;
                }
                prevTreeNode = currTreeNode;
            }
            // Link parent node with its first child node
            tmpTreeNode.left = headTreeNode;
        }

        return newTreeNodeRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        // 类似encode BFS：这里的一层是沿着right斜向右下的一层
        if (root == null) return null;

        Node newNodeRoot = new Node(root.val, new ArrayList<>());
        Map<TreeNode, Node> mapping = new HashMap<>();
        mapping.put(root, newNodeRoot);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode tmpTreeNode = queue.poll();
            Node tmpNode = mapping.get(tmpTreeNode);

            TreeNode nextChildNode = tmpTreeNode.left; // the first sibling of tmpNode
            while (nextChildNode != null) {
                Node newChildNode = new Node(nextChildNode.val, new ArrayList<>());
                mapping.put(nextChildNode, newChildNode);
                tmpNode.children.add(newChildNode);
                queue.add(nextChildNode);   // 每一个nextChildNode都有可能有child，所以要加入queue中
                nextChildNode = nextChildNode.right;
            }
        }

        return newNodeRoot;
    }
}
