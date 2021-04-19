/**
 * @author YonghShan
 * @date 2/5/21 - 23:33
 */

class Solution2 {
    // Recursion!!!!!!!!: 当head==1时，找到head==2的list翻转得到的reversed list，然后把head==1作为新的tail加入reversed list；
    //                    当head==2时，找到head==3的list翻转得到的reversed list，然后把head==2作为新的tail加入reversed list；
    //                    ...
    /* Runtime: 0ms    O(n)
       Memory: 39MB    O(n): The extra space comes from implicit stack space due to recursion. The recursion could go up to nn levels deep.
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head; // 当head.next==null时，说明传进来的head是原list中最后一个node，也就是翻转后的head
        ListNode p = reverseList(head.next); // 不然，就对 以 传进来的head的next 作为head的list 的翻转list（p表示的是翻转后list的head，就是原list的最后一个node）
        // 将现在的head放入以p为head的reversed list作为新的tail
        head.next.next = head;
        head.next = null;

        return p; // !!! p从第一次被赋上一个明确的node时，就表示为翻转后list的head，之后一直不变
    }
}
