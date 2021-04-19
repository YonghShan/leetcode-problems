import java.util.LinkedList;

/**
 * @author YonghShan
 * @date 1/19/21 - 19:44
 */
public class SolutionTest {
    public static void main(String[] args) {
//        LinkedList<Integer> list1 = new LinkedList<Integer>();
//        list1.add(1);
//        list1.add(2);
//        list1.add(4);
//
//        LinkedList<Integer> list2 = new LinkedList<Integer>();
//        list2.add(1);
//        list2.add(3);
//        list2.add(4);

        ListNode l1_3 = new ListNode(4, null);
        ListNode l1_2 = new ListNode(2, l1_3);
        ListNode l1_1 = new ListNode(1, l1_2);

        ListNode l2_3 = new ListNode(4, null);
        ListNode l2_2 = new ListNode(3, l2_3);
        ListNode l2_1 = new ListNode(1, l2_2);

        //SolutionRecursion s = new SolutionRecursion();
        SolutionIteration s = new SolutionIteration();
        ListNode result = s.mergeTwoLists(l1_1, l2_1);
        System.out.println(result.val);
        System.out.println(result.next.val);
        System.out.println(result.next.next.val);
        System.out.println(result.next.next.next.val);
    }
}
