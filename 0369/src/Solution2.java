/**
 * @author YonghShan
 * @date 6/29/21 - 17:21
 */
public class Solution2 {
    /* Runtime: 0ms        O(n)
       Memory: 36.4MB      O(1)
     */
    public ListNode plusOne(ListNode head) {
        // sentinel head
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        // 防止Linked List初始值为99...99的情况，not-nine要从sentinel开始寻找
        ListNode notNine = sentinel;

        // find the rightmost not-nine digit
        while (head != null) {
            if (head.val != 9) notNine = head;
            head = head.next;
        }

        // increase this rightmost not-nine digit by 1
        notNine.val++;
        notNine = notNine.next;

        // rightmost not-nine digit不为最后一位：set all the following nines to zeros
        while (notNine != null) {
            notNine.val = 0;
            notNine = notNine.next;
        }

        return sentinel.val != 0 ? sentinel : sentinel.next;
    }
}
