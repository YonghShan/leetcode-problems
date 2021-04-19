import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 2/4/21 - 16:11
 */

public class Solution2 {
    // 新建一个HashSet存放List 1中的元素，再依次拿List 2中的元素与Set中的元素比较
    /* Runtime: 10ms     O(n+m) (HashSet的contains()方法是O(1)，调用的是HashMap的containsKey(Object)，本质上利用hash code检查)
       Memory: 54MB      O(n)/O(m), where n is the length of Linked List 1 and m is the length of Linked List 2.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set =new HashSet<>();

        if (headA == null || headB == null) return null;

        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }
}