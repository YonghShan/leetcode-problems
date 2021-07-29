/**
 * @author YonghShan
 * @date 7/28/21 - 00:49
 */
public class Solution2Abbr {
    // 将Solution 2写成一个循环    最佳！！！！
    // One Pass & Two Pointers: First, find the location for both pointers; Second, one step per iteration for both pointers
    /* Runtime: 0ms     O(L-n+n) = O(L), where L is the length of the list
       Memory:  37MB    O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;   // 对应Solution 2的p2
        ListNode second = dummy;  // 对应Solution 2的p1

        int i = 0;
        while(first !=null) {
            first = first.next;
            if (i++ > n)
                second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
