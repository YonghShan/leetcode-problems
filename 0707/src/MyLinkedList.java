/**
 * @author YonghShan
 * @date 2/3/21 - 00:09
 */

class MyLinkedList {
    // Singly Linked List
    // 不是算法题，更像是概念题
    /* Runtime: 9ms
       Memory: 40MB
     */

    int size; // determine if the index is valid
    //ATTENTION!! Here is the sentinel node as pseudo-head
    ListNode head; // use head to represent a Linked List

    /** Initialize your data structure here. */
    public MyLinkedList() {
        size = 0;
        //head = new ListNode(0); // 调用任一有参构造函数都可
        head = new ListNode(0, null);  // 这里把sentinel作为pseudo-head；若是直接把head = null，则是将head作为末尾，见MyLinkedList1
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        // Linked List随机访问较差，因为要从头依次寻找
        ListNode curr = head;
        for(int i = 0; i < index + 1; i++) curr = curr.next; // 当循环结束时，curr为第index的ListNode
        return curr.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val); // size不用-1，因为是在尾部，也就是在index = size - 1的ListNode后面append
        // 之后，size变为size + 1
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) return; // index可以等于size，就是上面的addAtTail()

        // [so weird] If index is negative,
        // the node will be inserted at the head of the list.
        if (index < 0) index = 0;

        // Linked List中间插入高效
        // Step 1: Find the prev
        ListNode prev = head; // 对于在开头插入，prev就是sentinel
        // 如果index等于0（i.e. 在开头插入），则不会进入下面的for循环
        for(int i = 0; i < index; i++) prev = prev.next;
        // Step 2: Initialize a new node curr with the given value and link its next field to prev.next
        ListNode curr = new ListNode(val, prev.next);
        // Step 3: Redirect the prev's next field to curr
        prev.next = curr;

        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        // Linked List中间删除高效
        ListNode prev = head;
        for (int i = 0; i < index; i++) prev = prev.next;
        prev.next = prev.next.next;

        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
