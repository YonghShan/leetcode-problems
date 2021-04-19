
/**
 * @author YonghShan
 * @date 2/12/21 - 21:17
 */

class Solution1 {
    // Two Pass Two Pointers
    // Step 1: Find the node with the smallest val in the cyclic linked list;
    // Step 2: Start from the smallest node and find the position for insert node.
    // Pay attention to the case where the insertVal is smaller than min!
    /* Runtime: 0ms    O(2n)
       Memory: 38MB    O(1)
     */
    public Node insert(Node head, int insertVal) {
        Node insert = new Node(insertVal);

        // 原cyclic linked list长度等于0的情况：
        if (head == null) {
            insert.next = insert;
            return insert;
        }

        // 原cyclic linked list长度大于等于1的情况：
        Node p1 = head;
        int min = head.val;
        Node p2 = head;
        int len = 1;

        while (p2.next != head) {
            if (p2.next.val < min) {
                p1 = p2.next;
                min = p1.val;
            }
            p2 = p2.next;
            len++;
        }
        // p1即为list中val最小的node

        for (int i = 0; i < len-1; i++) {
            if (min <= insertVal && p1.next.val >= insertVal) break; //如果insertVal小于min，则应让p1指向p1.prev
            p1 = p1.next;
        }
        insert.next = p1.next;
        p1.next = insert;

        return head;
    }
}
