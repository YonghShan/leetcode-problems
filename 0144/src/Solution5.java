import java.util.LinkedList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/15/21 - 01:55
 */

class Solution5 {
    // Morris Traversal: reduce the space complexity to O(1)
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();

        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {  // 第一步向左，走不了则记录node的值，然后node = node.right
                output.add(node.val);
                node = node.right;
            }
            else { // 第一步向左，能走
                TreeNode predecessor = node.left;
                while ((predecessor.right != null) && (predecessor.right != node)) { // 则之后只要右孩子不为none和自身，则一直向右
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) { // 如果右孩子为none，则记录node的值，并将右孩子设为自身，然后node = node.left
                    output.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                }
                else{ // 如果右孩子是自身，则断开这一联系，然后node = node.right
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
        return output;
    }
}