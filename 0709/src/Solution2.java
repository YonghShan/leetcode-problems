/**
 * @author YonghShan
 * @date 6/6/21 - 22:55
 */

class KthLargest2 {
    /* Runtime: O(n^2) in worst case and O(nlogn) in average case
       Memory:
     */
    // insert a node into the BST
    private Node insertNode(Node root, int num) {  // O(height)
        if (root == null) {
            return new Node(num, 1);
        }
        if (root.val < num) {
            root.right = insertNode(root.right, num);
        } else {
            root.left = insertNode(root.left, num);
        }
        root.cnt++;
        return root;
    }

    private int searchKth(Node root, int k) {    // O(height)
        // m = the size of right subtree
        int m = root.right != null ? root.right.cnt : 0;
        // root is the m+1 largest node in the BST
        if (k == m + 1) {
            return root.val;
        }
        if (k <= m) {
            // find kth largest in the right subtree
            return searchKth(root.right, k);
        } else {
            // find (k-m-1)th largest in the left subtree
            return searchKth(root.left, k - m - 1);
        }
    }

    private Node root;
    private int m_k;

    public KthLargest2(int k, int[] nums) {
        root = null;
        for (int i = 0; i < nums.length; ++i) {  // O(n*height)
            root = insertNode(root, nums[i]);
        }
        m_k = k;
    }

    public int add(int val) {  // O(height)
        root = insertNode(root, val);
        return searchKth(root, m_k);
    }
}

class Node {    // the structure for the tree node
    Node left;
    Node right;
    int val;
    int cnt;    // the size of the subtree rooted at the node
    public Node (int v, int c) {
        left = null;
        right = null;
        val = v;
        cnt = c;
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
