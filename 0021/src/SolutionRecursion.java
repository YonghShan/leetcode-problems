/**
 * @author YonghShan
 * @date 1/19/21 - 20:15
 */
class SolutionRecursion {
    /* Runtime: 0ms   O(m+n)
       Memory: 38MB   O(1)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //Recursion
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}