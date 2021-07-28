import javax.management.ListenerNotFoundException;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

/**
 * @author YonghShan
 * @date 2/4/21 - 17:40
 */
public class Solution3 {
    // Two Pointers: p1和p2分别从两个list的head出发，one step per iteration, 谁到了tail，就重新指向另一条list的head（p2到了tail，下一回合指向list1的head），直到p1、p2相遇即为intersection
    // 如果p1和p2到达的tail不一样，则没有intersection
    /* Runtime: 1ms     <=O(n+m) 当intersection为tail时，取等号
       Memory: 42MB       O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;

        if (headA == null || headB == null) return null;

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
            // 会需要下面这个判断的原因是因为将p1和p2与null的判断放在了后面，Solution3Abbr就是将这个判断放在了一进while循环，所以不再需要下面的判断
            if (p1 == p2) return p1; // p1和p2是否相同的判断要放在前：假如没有intersection时，p1和p2会最终同时等于null，此时就要返回p1或p2（即null）。如果这个判断放在了最后，则循环无法终止。
            if (p1 == null) p1 = headB;
            if (p2 == null) p2 = headA;
        }

        return p1;
    }
}
