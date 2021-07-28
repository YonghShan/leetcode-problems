/**
 * @author YonghShan
 * @date 2/4/21 - 15:29
 */

public class Solution1 {
    // 双层遍历
    /* Runtime: 1022ms   O(nm), where n is the length of Linked List 1 and m is the length of Linked List 2.
       Memory: 41.7MB    O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // 列举headA和headB都不为null的情况：
        while (headB != null) {
            ListNode p = headA;
            while (p != null) {
                if (p == headB) return headB;
                p = p.next;
            }
            headB = headB.next;
        }

        return null;
    }
}