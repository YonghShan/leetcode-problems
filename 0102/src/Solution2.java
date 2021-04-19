import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/15/21 - 18:08
 */

class Solution2 {
    // Recursion: 对Solution1做些简化
    /* Runtime: 1ms
       Memory: 40MB
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        // Step 1: 先存root的值
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        res.add(list);

        // Step 2: 根据实际情况，获取rootLeft和rootRight，以及完成两者对应元素的合并
        if (root.left != null || root.right != null) {
            if (root.right == null) {
                res.addAll(levelOrder(root.left));
            } else if (root.left == null) {
                res.addAll(levelOrder(root.right));
            } else {
                List<List<Integer>> rootLeft = levelOrder(root.left);
                List<List<Integer>> rootRight = levelOrder(root.right);

                int sizeLeft = rootLeft.size();
                int sizeRight = rootRight.size();
                // 和Solution1不同，这里直接修改较长list的元素，然后将较长list直接放在res末尾
                if (sizeLeft <= sizeRight) {
                    for (int i = 0; i < sizeLeft; i++) rootRight.get(i).addAll(0, rootLeft.get(i)); // 保证同一level，左子树的值在右子树的值之前
                    res.addAll(rootRight);
                } else {
                    for (int i = 0; i < sizeRight; i++) rootLeft.get(i).addAll(rootRight.get(i));
                    res.addAll(rootLeft);
                }
            }
        }

        return res;
    }
}