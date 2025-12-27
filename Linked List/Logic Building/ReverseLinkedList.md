### Given the head of a singly linked list. Reverse the given linked list and return the head of the modified list.

```
Example 1
Input: head -> 1 -> 2 -> 3 -> 4 -> 5
Output: head -> 5 -> 4 -> 3 -> 2 -> 1
Explanation: All the links are reversed and the head now points to the last node of the original list.
```
```java
Iterative
class Solution {
    /*Function to reverse a linked list
    Using the 3-pointer approach*/
    public ListNode reverseList(ListNode head) {
        /*Initialize 'temp' at
        head of linked list*/
        ListNode temp = head;
        
        /*Initialize pointer 'prev' to NULL,
        representing the previous node*/
        ListNode prev = null;
        
        /*Traverse the list, continue till
        'temp' reaches the end (NULL)*/
        while (temp != null) {
            /* Store the next node in
            'front' to preserve the reference*/
            ListNode front = temp.next;
            
            /*Reverse the direction of the
            current node's 'next' pointer
            to point to 'prev'*/
            temp.next = prev;
            
            /*Move 'prev' to the current
            node for the next iteration*/
            prev = temp;
            
            /*Move 'temp' to the 'front' node
            advancing the traversal*/
            temp = front;
        }
        
        /*Return the new head of
        the reversed linked list*/
        return prev;
    }
}   
Time Complexity: O(N) because the algorithm traverses the entire linked list once, where 'N' is the number of nodes in the list. Since each node is visited exactly once during the traversal, the time complexity is linear, O(N).
Space Complexity: O(1) because the algorithm uses only a constant amount of additional space. This is achieved by utilizing three pointers (prev, temp, and front) to reverse the list without any significant extra memory usage, resulting in constant space complexity, O(1).
```
```java
Recursive
class Solution {
    /* Function to reverse a singly linked list using recursion */
    public ListNode reverseList(ListNode head) {
        /* Base case:
        If the linked list is empty or has only one node,
        return the head as it is already reversed. */
        if (head == null || head.next == null) {
            return head;
        }
        
        /* Recursive step:
        Reverse the linked list starting 
        from the second node (head.next). */
        ListNode newHead = reverseList(head.next);
        
        /* Save a reference to the node following
        the current 'head' node. */
        ListNode front = head.next;
        
        /* Make the 'front' node point 
        to the current
        'head' node in the 
        reversed order. */
        front.next = head;
        
        /* Break the link from 
        the current 'head' node
        to the 'front' node 
        to avoid cycles. */
        head.next = null;
        
        /* Return the 'newHead,' 
        which is the new
        head of the reversed 
        linked list. */
        return newHead;
    }
}
Time Complexity: O(N) because the algorithm traverses the linked list twice: once to push the values onto the stack, and once to pop the values and update the linked list. Since each node is visited during both traversals, the time complexity is linear, O(N).
Space Complexity: O(1) The algorithm does not use additional space explicitly for data structures or allocations during the reversal process. However, it does use stack space due to recursion, storing function calls and associated variables during the recursive traversal and reversal of the list. Despite this, no extra memory beyond the program's existing execution space is allocated, maintaining a space complexity of O(1).
```