/**
 * @author YonghShan
 * @date 2/4/21 - 18:28
 */

public class Solution4 {
    // Two Pointers:
    //      Step 1: find the length of both lists
    //      Step 2: determine the start position of both pointers
    //      Step 3: one step per iteration both pointers
    // 如果p1和p2到达的tail不一样，则没有intersection
    /* Runtime: 1ms     <=O(min(n,m)) 当intersection为tail时，取等号
       Memory: 52MB        O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        int diff = lenA > lenB ? lenA - lenB : lenB - lenA;

        // To reset the beginning of headA or headB
        for (int i = 0; i < diff; i++) {
            if (lenA > lenB) {
                headA = headA.next;
            } else {
                headB = headB.next;
            }
        }

        while (headA != headB) { // 如果没有intersection的话，最后headA和headB会因为都等于null而终止循环
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    public int getLength(ListNode head) {
        int len = 0;

        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }
}

