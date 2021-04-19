/**
 * @author YonghShan
 * @date 2/5/21 - 14:47
 */

public class Solution2 {
    // Two Pass & Two Pointers: First, find the location for both pointers; Second, one step per iteration for both pointers
    /* Runtime: 0ms     O(L-n+n) = O(L), where L is the length of the list
       Memory:  37MB    O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode p1 = dummy;
        ListNode p2 = dummy;

        for (int i = 0; i < n+1; i++) {  // O(L-n)
            p2 = p2.next;
        }

        while (p2 != null) { // O(n)
            p1 = p1.next;
            p2 = p2.next;
        } // 循环结束时，p2 == null, p1指向从后往前数第n+1个数。 如果n==len，则p2==null, p1==dummy

        p1.next = p1.next.next;

        return dummy.next; // 不能return head! 这里对p1/p2的修改都只会对dummy造成影响，而不会对head造成影响
    }
}
