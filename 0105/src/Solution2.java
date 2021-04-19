/**
 * @author YonghShan
 * @date 2/18/21 - 17:15
 */
public class Solution2 {
    // Solution 1中在从preorder首位获取root后，还需要去inorder中获取相同元素的index，再依据index对inorder进行split
    // Solution 2中通过定义的pointer stop，省去以上步骤
    // 这样保证了即使原本的tree是完全不平衡的(just a straight line to the left: inorder is the reverse of preorder, O(n^2))，
    // 也能保证Time complexity为O(n)
    private int in = 0; // 指向inorder的首位，只有当pointer pre指向的值与自身指向的值相等，才向后移动一位
    private int pre = 0; // 指向preorder的首位，每次recursion向后移动一位

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length) return null;
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]); // 为pre指向的值建立node，pre向后移
        node.left = build(preorder, inorder, node.val);
        // 这里的stop即为传进来的stop（当前node的parent）：当上面node.left结束时，pre指向当前node后面一位，即右子树的开始
        // in一定指向inorder中对应node值的元素，所以一开始inorder[in] == stop，in++也进入了右子树的范围、
        /* e.g. preorder = [3,9,20,15,7]  inorder = [9,3,15,20,7]
                当pre=1,in=0,stop=3时，new出node 9，先找9的left：pre=2，in=0，返回null，并且in=1
                此时回到stop=3，但pre=2（指向20，已经进入right subtree），in=1（指向3）
                接着进行找9的right：第一个recursion因为inorder[in]=inorder[1]=3=stop,导致返回null（即9.right=null）
                此时pre=2，in=2都进入了右子树的范围，因为stop=MIN，所以开始寻找3的right
         */
        node.right = build(preorder, inorder, stop);
        return node;
    }
}
