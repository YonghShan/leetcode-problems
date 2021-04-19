/**
 * @author YonghShan
 * @date 2/7/21 - 23:25
 */
class MyLinkedList2 {
    // Doubly Linked List
    /* Runtime: 9ms
       Memory: 40MB
     */

    int size;
    //ATTENTION!! Here are the sentinel nodes as pseudo-head and pseudo-tail
    DoublyListNode head;
    DoublyListNode tail; // 如果不加一个pseudo-tail，在addAtHead()和addAtTail()都将不能简单地调用addAtIndex()

    /** Initialize your data structure here. */
    public MyLinkedList2() {
        size = 0;
        head = new DoublyListNode(0);
        tail = new DoublyListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        DoublyListNode curr = head;
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

        // Step 1: Find the prev
        DoublyListNode prev = head;
        for(int i = 0; i < index; i++) prev = prev.next;
        // Step 2: Initialize a new node curr with the given value, link its prev field to prev and its next field to prev.next
        DoublyListNode curr = new DoublyListNode(val, prev, prev.next);
        // Step 3: Redirect the next's prev field to curr
        prev.next.prev = curr;
        // Step 4: Redirect the prev's next field to curr
        prev.next = curr;

        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        DoublyListNode prev = head;
        for (int i = 0; i < index; i++) prev = prev.next;
        prev.next = prev.next.next;
        prev.next.prev = prev;

        size--;
    }
}
