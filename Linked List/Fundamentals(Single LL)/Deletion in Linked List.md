### Deletion of the head of LL
```
Example 1
Input: linkedList = [1, 2, 3]
Output: [2, 3]
Explanation:
The first node was removed.

Example 2
Input: linkedList = [1]
Output: []
Explanation:
Note that the head of the linked list gets changed.
```
```java
class ListNode {
    int val;
    ListNode next;

    ListNode() {
        val = 0;
        next = null;
    }

    ListNode(int data1) {
        val = data1;
        next = null;
    }

    ListNode(int data1, ListNode next1) {
        val = data1;
        next = next1;
    }
}

class Solution {
    // Function to delete the head node of the linked list
    public ListNode deleteHead(ListNode head) {
        // If list is empty, nothing to delete
        if (head == null) 
            return null;

        // Set temporary pointer
        ListNode temp = head;

        // Update head to the next node 
        head = head.next;

        // Delete original head    
        temp = null;

        // Return new head          
        return head;
    }
}
Time Complexity: O(1) for updating the head of the linked list.
Space Complexity: O(1) as no extra space is used.
```

### Deletion of the tail of Linked List
```
Example 1
Input: linkedList = [1, 2, 3]
Output: [1, 2]
Explanation:
The last node was removed.

Example 2
Input: linkedList = [1]
Output: []
Explanation:
Note that the value of head is null here.
```
```java
class Solution {
    // Function to delete the tail node of linked list
    public ListNode deleteTail(ListNode head) {

        // If the list is empty or has only one node
        if (head == null || head.next == null) {
            return null; // Return null
        }

        // Temporary pointer
        ListNode temp = head;

        /* Traverse to the second last 
        node in the list */
        while (temp.next.next != null) {
            temp = temp.next;
        }

        // Delete the last node
        temp.next = null;

        // Return head of modified list
        return head;
    }
}
Time Complexity: O(N) for traversing the linked list and updating the tail.
Space Complexity: O(1) as no extra space has been used.
```