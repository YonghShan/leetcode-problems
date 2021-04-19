/**
 * @author YonghShan
 * @date 2/4/21 - 23:41
 */
public class Solution1 {
    // Two Pass & Two Pointers: First, find the length of the list; Second, covert the nth from the end to the (len-n)th from the beginning
    /* Runtime: 0ms     O(L+(L-n)) = O(2L-n), where L is the length of the list
       Memory:  37MB    O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode p1 = head;
        ListNode p2 = head;

        while (p1 != null) {  // O(L)
            len++;
            p1 = p1.next;
        } // 循环结束时，p1 == tail，但是依然没法从后往前遍历

        if (len == n) {
            // Case 1: 要删去的node是head
            head = head.next;
        } else {
            // Case 2：要删去的node是原list的中间node或tail
            for (int i = 0; i < len-n-1; i++) p2 = p2.next; // O(L-n) 循环结束，p2为第len-n个node的前一个node（i.e. 第len-n-1个node）
            p2.next = p2.next.next;
        }

        return head;
    }
}
