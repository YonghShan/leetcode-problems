/**
 * @author YonghShan
 * @date 2/6/21 - 17:09
 */

class Solution {
    // Recursion
    /* Runtime: 1ms  O(n)
       Memory:  40MB O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}