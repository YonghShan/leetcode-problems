/**
 * @author YonghShan
 * @date 2/4/21 - 23:10
 */

public class Solution3Abbr {
    /* Runtime: 1ms     <=O(n+m) 当intersection为tail时，取等号
       Memory: 42MB       O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
            p1 = (p1 != null) ? p1.next : headB;
            p2 = (p2 != null) ? p2.next : headA;
        }
        return p1;
    }
}