/**
 * @author YonghShan
 * @date 2/19/21 - 22:08
 */

class Solution4 {
    // 官方Iteration：leftmost沿着tree的最左边从上往下移动；head从leftmost开始从左往右移动
    /* leftmost = root
       while (leftmost.left != null) {
           head = leftmost
           while (head.next != null) {
               1) Establish Connection 1
               2) Establish Connection 2 using next pointers
               head = head.next
           }
           leftmost = leftmost.left
       }
     */
    // 思路是定义一个pointer leftmost指向每一层最左node，再定义一个pointer head指向leftmost所在层的第一个node，依次向右移动，移动到最右边后，leftmost移向下一层
    // 因为当我们在第N层时，为第N+1层的node确定next field，当head到达第N层最右时，第N+1层所有node的next也都设置好了；
    // 接着随着leftmost下到第N+1层，利用第N+1层已经设置好的next，head依旧可以一直向右移动并为第N+2层的node设置next。
    /* Runtime: O(n)
       Memory: O(1)
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // Start with the root node. There are no next pointers that need to be set up on the first level
        Node leftmost = root;

        // Once we reach the final level, we are done
        while (leftmost.left != null) {

            // Iterate the "linked list" starting from the head node and using the next pointers,
            // establish the corresponding links for the next level
            Node head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // Progress along the list (nodes on the current level)
                head = head.next;
            }

            // Move onto the next level
            leftmost = leftmost.left;
        }

        return root;
    }
}