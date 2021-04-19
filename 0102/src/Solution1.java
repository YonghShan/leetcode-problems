import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/15/21 - 16:35
 */

class Solution1 {
    // Recursion "Bottom-up": 先得到最底层，再一层层向上填充
    /* for (List<Integer> tmp : rootLeft) {
           System.out.print("[");
           for (Integer tmps : tmp) {
               System.out.print(tmps + ", ");
           }
           System.out.println("]");
       }
     */
    /* Runtime: 2ms
       Memory: 40MB
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        // Step 1: 先存root的值 （如果要严格的"Bottom-up"，完全可以最后才加root的值，只是注意要确定root加在res的开头，见最后）
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        res.add(list);

        // Step 2: 根据实际情况，获取rootLeft和rootRight，以及完成两者对应元素的合并
        // 2.1 左右子树都存在：
        if (root.left != null && root.right != null) {
            List<List<Integer>> rootLeft = levelOrder(root.left);
            List<List<Integer>> rootRight = levelOrder(root.right);

            int sizeLeft = rootLeft.size();
            int sizeRight = rootRight.size();
            int size = sizeLeft >= sizeRight ? sizeRight : sizeLeft;

            // 合并的处理方式为：
            // 2.1.1 选择rootLeft和rootRight中较小的size进行遍历及合并对应元素
            for (int i = 0; i < size; i++) {
                List<Integer> temp = rootLeft.get(i); // 为了不改变rootLeft的内容，创建了temp
                temp.addAll(rootRight.get(i));
                res.add(temp);
            }
            // 2.1.2 然后，将长list中剩余的元素加入res
            if (sizeLeft > sizeRight) res.addAll(rootLeft.subList(size, sizeLeft));
            if (sizeLeft < sizeRight) res.addAll(rootRight.subList(size, sizeRight));
        }
        // 2.2 左子树存在：
        if (root.left != null && root.right == null) res.addAll(levelOrder(root.left));
        // 2.3 右子树存在：
        if (root.left == null && root.right != null) res.addAll(levelOrder(root.right));

//        // 如果遵循严格的"Bottom-down"规则，则将root最后放入res，只是要注意root要放在res的index 0处
//        List<Integer> list = new ArrayList<>();
//        list.add(root.val);
//        res.add(0,list);

        return res;
    }
}