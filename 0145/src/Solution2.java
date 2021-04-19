import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/14/21 - 17:12
 */

class Solution2 {
    // Iteration： 三种遍历方式iteration实现中最麻烦的一种！！！（不考虑技巧，完全按照left-right-root的遍历顺序来说）
    //       1
    //     /   \
    //    4     4      当p回到1时，left的TreeNode 4已经被存入stored中，right的TreeNode 4不会被认为和左边的4相同，
    //   / \   / \     那么此时判定!stored.contains(p.right)，结果为true！
    //  5   6  5  6    即使left 4和right 4的val、left、right fields都相同，因为它俩地址不同
    // 这样的原因是：Collection的contains()是调用Object的equals()，而除String的equals()被重写为比内容以外，其余都是比地址，和==/!=等效
    /* Runtime: 0ms     O(n + m) (不仅每个node找了一遍，还额外将p赋为null，m为p赋为null的次数)
       Memory: 37.2MB   <O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Deque<TreeNode> stack = new ArrayDeque<>();
        List<TreeNode> stored = new ArrayList<>();
        TreeNode p = root;
        while (p != null) {
            if (p.left != null && !stored.contains(p.left)) {
                stack.addFirst(p);
                p = p.left;
            } else if (p.right != null && !stored.contains(p.right)){
                stack.addFirst(p);
                p = p.right;
            } else {
                list.add(p.val);
                stored.add(p); // 防止进入局部死循环
                p = null;
            }
            while (p == null && !stack.isEmpty()) {
                p = stack.pop();
            }
        }

        return list;
    }
}
