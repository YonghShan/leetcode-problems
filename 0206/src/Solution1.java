/**
 * @author YonghShan
 * @date 2/5/21 - 16:31
 */

class Solution1 {
    // Iteration
    /* Runtime: 0ms    O(n)
       Memory: 39MB    O(1)
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode p1 = head;
        ListNode p2 = head.next;

        while (p1.next != null) {
            p1.next = p2.next;
            p2.next = head;
            head = p2;
            p2 = p1.next;
        }

        return head;
    }
}