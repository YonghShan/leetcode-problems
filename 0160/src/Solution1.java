/**
 * @author YonghShan
 * @date 2/4/21 - 15:29
 */

public class Solution1 {
    // 双层遍历
    /* Runtime: 530ms   O(nm), where n is the length of Linked List 1 and m is the length of Linked List 2.
       Memory: 52MB     O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;

        if (headA == null || headB == null) return null;

        // 列举headA和headB都不为null的情况：
        while (headB != null) {
            while (p != null) {
                if (p == headB) {
                    return headB;
                } else if (p.next != null) {
                    p = p.next;
                } else {
                    break;
                }
            }
            p = headA; // 经过上面的while循环，p指向Linked List 1的tail，要回到head再和headB.next比
            headB = headB.next;
        }

        return null;
    }
}