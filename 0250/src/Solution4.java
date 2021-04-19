/**
 * @author YonghShan
 * @date 2/17/21 - 22:58
 */

public class Solution4 {
    // 对Solution 3的改进：Solution 3中对是否为univalue subtree的判定是基于子节点是否为unival subtree以及子节点和父节点的值的关系
    // Solution 4中，不再考虑子节点是否为unival subtree，而是从最底层将null就视为unival subtree，只是这种subtree并不是valid
    // 所以， 算法的重点从从底层向上，检查每个node是否为unival subtree，转变为检查每个node的子树是不是valid的unival subtree
    // 前者需要涉及子节点是否为unival subtree以及比较父子节点的值，而后者只要比较父子节点的值
    int count = 0;
    boolean is_valid_part(TreeNode node, int val) {
        // considered a valid subtree
        if (node == null) return true;

        // check if node.left and node.right are univalue subtrees of value node.val
        // note that || short circuits but | does not - both sides of the or get evaluated with | so we explore all possible routes
        if (!is_valid_part(node.left, node.val) | !is_valid_part(node.right, node.val)) return false;

        // if it passed the last step then this a valid subtree - increment
        count++;

        // at this point we know that this node is a univalue subtree of value node.val
        // pass a boolean indicating if this is a valid subtree for the parent node
        return node.val == val;
    }
    public int countUnivalSubtrees(TreeNode root) {
        // Anything can be passed there. To be consistent with the meaning of the is_valid_part() function,
        // you could start with is_valid_part(root, root.val). However, to use root.val,
        // you must check whether root is null or not. To avoid this check, a random number 0 is used.
        is_valid_part(root, 0);
        return count;
    }
}