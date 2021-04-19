/**
 * @author YonghShan
 * @date 2/6/21 - 16:39
 */

class Solution1 {
    // 从左到右遍历
    /* Runtime: 1ms   O(n)
       Memory:  40MB  O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0, head);
        ListNode p = dummy;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }

        return dummy.next;
    }
}
