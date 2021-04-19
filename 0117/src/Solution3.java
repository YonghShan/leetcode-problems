/**
 * @author YonghShan
 * @date 2/19/21 - 22:32
 */

class Solution3 {
    // 官方Iteration：leftmost沿着tree的最左边从上往下移动；curr（相当于116中的head）从leftmost开始从左往右移动
    // 和116不同的是，这里多定义了一个pointer prev：在116中，因为是perfect binary tree，head的left和right一定存在；
    // 而117是随意的binary tree，curr的child可能只有一个或者没有，prev就是指向curr的child
    // 注意prev初始为null，只有当curr有child时，有left则prev=curr.left; 有right则prev=curr.right；
    // 没有child则不update prev；有两个child则prev先为curr.left再为curr.right
    // 也因此leftmost的update不能简单地像116中leftmost=leftmost.left, 而是将第一次update的prev作为下一层的leftmost
    /* leftmost = root
       while (leftmost != null) {
           curr = leftmost
           prev = NULL
           while (curr != null) {
               → process left child
               → process right child
               → set leftmost for the next level
               curr = curr.next
           }
       }
     */
    // 思路是定义一个pointer leftmost指向每一层最左node，再定义一个pointer curr指向leftmost所在层的第一个node，依次向右移动，移动到最右边后，leftmost移向下一层
    // 因为当我们在第N层时，为第N+1层的node确定next field，当head到达第N层最右时，第N+1层所有node的next也都设置好了；
    // 接着随着leftmost下到第N+1层，利用第N+1层已经设置好的next，curr依旧可以一直向右移动并为第N+2层的node设置next。
    /* Runtime: 0ms    O(n)
       Memory: 39.1MB  O(1)
     */
    Node prev, leftmost;

    public void processChild(Node childNode) {
        if (childNode != null) {

            // If the "prev" pointer is already set i.e. if we already found at least one node on the next level,
            // setup its next pointer
            if (this.prev != null) {
                this.prev.next = childNode;
            } else {
                // Else it means this child node is the first node
                // we have encountered on the next level, so, we set the leftmost pointer
                this.leftmost = childNode;
            }

            this.prev = childNode;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // The root node is the only node on the first level and hence its the leftmost node for that level
        this.leftmost = root;

        // Variable to keep track of leading node on the "current" level
        Node curr = leftmost;

        // We have no idea about the structure of the tree, so, we keep going until we do find the last level.
        // the nodes on the last level won't have any children
        while (this.leftmost != null) {

            // "prev" tracks the latest node on the "next" level while "curr" tracks the latest node on the current level.
            this.prev = null;
            curr = this.leftmost;

            // We reset this so that we can re-assign it to the leftmost node of the next level.
            // Also, if there isn't one, this would help break us out of the outermost loop.
            this.leftmost = null;

            // Iterate on the nodes in the current level using the next pointers already established.
            while (curr != null) {

                // Process both the children and update the prev and leftmost pointers as necessary.
                this.processChild(curr.left);
                this.processChild(curr.right);

                // Move onto the next node.
                curr = curr.next;
            }
        }

        return root ;
    }
}