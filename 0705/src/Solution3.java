/**
 * @author YonghShan
 * @date 4/5/21 - 16:42
 */
class MyHashSet3 {
    // BST as bucket：因为用LinkedList作bucket，在contains()可能需要scan一遍整个LinkedList，costs O(N/K)；改用BST，因为BST的search/insert/delete全为log(N/K)
    /* Runtime: 13ms    O(log(N/K))  官方的代码在BST delete部分不一样，但运行时间相同
       Memory: 45.2MB   O(k+M)
     */
    private int base = 769;
    private Bucket[] bucketArray;

    /** Initialize your data structure here. */
    public MyHashSet3() {
        bucketArray = new Bucket[769];
        for (Bucket bucket : bucketArray) {
            bucket = new Bucket();
        }
    }

    public int getBucketIndex(int key) {
        return key % base;
    }

    public void add(int key) {
        int bucketIndex = getBucketIndex(key);
        bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = getBucketIndex(key);
        bucketArray[bucketIndex].delete(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int bucketIndex = getBucketIndex(key);
        return bucketArray[bucketIndex].exists(key);
    }
}

class Bucket { // Solution2也可以这么分开写，只要将Bucket类的成员变量改为LinkedList
    private BSTree tree;

    public Bucket() {
        tree = new BSTree();
    }

    public void insert(Integer key) {
        this.tree.root = this.tree.insertIntoBST(this.tree.root, key);
    }

    public void delete(Integer key) {
        this.tree.root = this.tree.deleteNode(this.tree.root, key);
    }

    public boolean exists(Integer key) {
        TreeNode node = this.tree.searchBST(this.tree.root, key);
        return (node != null);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class BSTree {
    TreeNode root = null;

    public TreeNode searchBST(TreeNode root, int val) {   // [0700]
        if (root == null || root.val == val) return root;
        return root.val > val? searchBST(root.left, val) : searchBST(root.right, val);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) { // [0701]
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else if (val == root.val) { // 本题是允许val已经存在于root中的，所以加上相等的判断
            return root;
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    /*
     * One step right and then always left
     */
    public int findSuccessor(TreeNode root) {
        // 第一次向右，之后一直向左
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) { // [0450]
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else { // 找到key所对应的Node
            if (root.left != null && root.right != null) { // root有两个孩子
                root.val = findSuccessor(root); // 用targetNode的successor代替targetNode
                root.right = deleteNode(root.right, root.val); // 将原targetNode的右子树中的successor删去
            } else { // root要么只有一个孩子，要么无孩子：此时，直接用其孩子代替它就行，不用找前驱后继，下面的实现方法也不需要去找其parent（不用trace back）
                root = root.left != null ? root.left : root.right;
            }
        }

        return root;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */