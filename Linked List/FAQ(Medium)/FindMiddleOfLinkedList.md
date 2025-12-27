### Given the head of a singly Linked List, return the middle node of the Linked List.  If the Linked List has an even number of nodes, return the second middle one.

```
Example 1
Input: head -> 3 -> 8 -> 7 -> 1 -> 3
Output(value at returned node): 7
Explanation: There are 5 nodes, so the middle node is the 3rd Node, with value 7.

Example 2
Input: head -> 2 -> 9 -> 1 -> 4 -> 0 -> 4
Output(value at returned node): 4
Explanation: There are 6 nodes, thus both the 3rd and 4th nodes are middle. So the 2nd middle node (4th Node) is returned with value 4.
```
```java
class Solution {
    // Function to get the middle node of linked list
    public ListNode middleOfLinkedList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        // Until the fast pointer reaches NULL or the last node
        while (fast != null && fast.next != null) {
            // Move slow pointer by one step
            slow = slow.next;
            
            // Move fast pointer by two steps
            fast = fast.next.next;
        }
        
        return slow;
    }
}
Time Complexity: O(N/2), where N is the number of nodes in the linked list.
The total iterations taken by the fast pointer to reach the end of the linked list are of the order O(N/2).
Space Complexity: O(1), as only a couple of variables are used.

Intuition:
An optimal approach to solve this problem involves the use of two pointer technique using the slow and fast pointers. The slow pointer moves one step at a time while the fast pointer moves two steps at a time. If the fast pointer reaches the end of the list, the slow pointer will be at the middle of the list.

This is because the fast pointer moves twice as fast as the slow pointer, so when the fast pointer reaches the end of the list, the slow pointer will be at the middle of the list.
```