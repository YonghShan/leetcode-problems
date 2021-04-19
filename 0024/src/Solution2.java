/**
 * @author YonghShan
 * @date 3/10/21 - 11:08
 */
public class Solution2 {
    // Iteration：每三个为一组（prevNode, firstNode, secondNode），更改三者的next后，将firstNode设为新的prevNode，firstNode.next设为新的head
    /* Runtime: 0ms      O(n)
       Memory: 36.2MB    O(1)
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;  // 防止head.next=null，进不去while循环的情况
        ListNode prevNode = dummy;

        while (head != null && head.next != null) {
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // 更改三者的next
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // 设置新的prevNode和head
            prevNode = firstNode;
            head = firstNode.next;
        }

        return dummy.next;
    }
}
