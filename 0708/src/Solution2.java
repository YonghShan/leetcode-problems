/**
 * @author YonghShan
 * @date 2/12/21 - 22:43
 */
class Solution2 {
    // One Pass One Pointer: Consider all the cases
    /* Runtime: 0ms    O(n)
       Memory:  38MB   O(1)
     */
    public Node insert(Node head, int insertVal) {
        Node insert = new Node(insertVal);

        if (head == null) {
            insert.next = insert;
            return insert;
        }

        Node curr = head;
        do {
            // Case 1: curr.val = 1; curr.next.val = 3; insertVal = 2
            if (curr.val <= insertVal && curr.next.val > insertVal) break;
            if (curr.val > curr.next.val) {
                // Case 2.1: curr.val = 10; curr.next.val = 1; insertVal = 0
                // Case 2.2: curr.val = 10; curr.next.val = 1; insertVal = 11
                if (insertVal <= curr.next.val || insertVal >= curr.val) break;
            }
            curr = curr.next;
        } while (curr != head);

        // Case 3: curr.val = 3; curr.next.val = 3; insertVal = 10
        // Case 3不需要判定，直接做和Case 1/2 一样的处理
        insert.next = curr.next;
        curr.next = insert;

        return head;
    }
}
