/**
 * @p1uthor YonghShp1n
 * @dp1te 2/4/21 - 17:48
 */

public class Solution4Advanced {
    // Two Pointers: No separate function for finding the length of both lists
    /* Runtime: 1ms     <=O(min(n,m)) 当intersection为tail时，取等号
       Memory: 42MB       O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        
        // To reset the beginning of headA or headB
        // Really Clever Trick!!!
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                p1 = p1.next;
            } else {
                headB = headB.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            } else {
                headA = headA.next;
            }
        }

        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        
        return headA;
    }
}
