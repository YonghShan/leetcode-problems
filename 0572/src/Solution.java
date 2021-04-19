
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        //仅比较两个node.val是否相等（作为各自（子）树的root时）
        if (s == null) {
            return t == null;
        }

        //这个判断可以被注释的原因，是因为无论是一开始传进来的t还是方法尾迭代传进来的t都不可能是null
        //不注释掉也不影响
//        if (t == null) {
//            return false;
//        }

        if (s.val == t.val && isSameTree(s, t)) {
            return true;
        }

        //此时，只剩下s!=null且s.val!=t.val的情况：
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean isSameTree(TreeNode s, TreeNode t) {
        //当两个rootNode.val相等时，比较其左右node（及左右node的左右node）是否都相同
        if (s == null) {
            return t == null;
        }

        //这个if判断不能注释掉，必须保留。
        //因为和isSubtree()不同，由该方法尾迭代所传入的t.right/t.left作为新的t时，可以为null
        //而当s!=null,而t==null时，如果没有这个判断，将会进行s.val != t.val的判断，而t.val会报java.lang.NullPointerException
        if (t == null) { //此时，s!=null，t==null说明两树不同
            return false;
        }

        if (s.val != t.val) {
            return false;
        }

        //只剩下s.val=t.val的情况，继续判断两树的左右node是否相同
        return isSameTree(s.right, t.right) && isSameTree(s.left, t.left);
    }
}