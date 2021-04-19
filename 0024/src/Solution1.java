/**
 * @author YonghShan
 * @date 3/10/21 - 00:40
 */
public class Solution1 {
    // Recursion
    /* Runtime: 0ms     O(n)
       Memory: 36.4MB   O(n)
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode temp = swapPairs(head.next.next);
        ListNode newHead = head.next;
        head.next.next = head;
        head.next = temp;
        return newHead;
    }
}
