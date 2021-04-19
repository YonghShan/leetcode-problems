/**
 * @author YonghShan
 * @date 2/13/21 - 21:59
 */

class Solution5 {
    // 将原list连成cycle，再从head开始第(len-k%n-1)个和第(len-k%len)个node之间断开，第(len-k%len)个node即为新的head
    /* Runtime: 0ms      O(len)
       Memory: 39.9MB    O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        // close the linked list into the ring, meantime, recode the len of list
        ListNode old_tail = head;
        int len;
        for(len = 1; old_tail.next != null; len++) old_tail = old_tail.next;
        old_tail.next = head;

        // find new tail : (len - k % len - 1)th node
        // and new head : (len - k % len)th node
        ListNode new_tail = head;
        for (int i = 0; i < len - k % len - 1; i++) new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;

        return new_head;
    }
}