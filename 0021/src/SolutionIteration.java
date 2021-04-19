class SolutionIteration {
    /* Runtime: 0ms    O(m+n), where m is the length of list1 and n is the length of list2
       memory: 38MB    O(1)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);  //方便之后prehead.next获取结果list的开头
        ListNode temp = prehead;

//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                temp = l1;    //这样写，并不能在先后两个node间建立联系
//                l1 = l1.next;
//            } else {
//                temp = l2;
//                l2 = l2.next;
//            }
//        }

        //Iteration
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;  //在各个node之间建立联系
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        //当上面while循环结束时，只有一个List的元素合并完了，另一条List还剩有元素
        //同时，这样写，也保证了合并一开始，有一个或两个List都为null，merge也能进行
        if (l1 == null) {
            temp.next = l2;
        } else {
            temp.next = l1;
        }

        return prehead.next;
    }
}
