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

### Deletion of the Kth element of Linked List
```
Example 1
Input: linkedList = [3, 4, 5], k = 2
Output: linkedList = [3, 5]
Explanation:
The 2nd node with value 4 was removed.

Example 2
Input: linkedList = [1, 2, 3], k = 1
Output: [2, 3]
Explanation:
The 1st Node was removed, note that the value of the head has changed.
```
```java
class Solution {
    public ListNode deleteKthNode(ListNode head, int k) {

        int cnt = 0; // Counter to keep track of node position

        // If the linked list is empty, nothing to delete
        if (head == null) {
            return null;
        }

        // Special case: if k == 1, we need to delete the head node
        // Move head to the next node and return it
        if (k == 1) {
            head = head.next;
            return head;
        }

        // temp is used to traverse the linked list
        ListNode temp = head;

        // prev will always point to the previous node of temp
        ListNode prev = null;

        // Traverse the linked list
        while (temp != null) {

            cnt++; // Increment position count as we move to next node

            // When we reach the kth node
            if (cnt == k) {
                // Skip the kth node by linking previous node
                // to the next node of the kth node
                prev.next = prev.next.next;
                break;
            }

            // Move prev and temp one step forward
            prev = temp;
            temp = temp.next;
        }

        // Return the (possibly updated) head of the linked list
        return head;
    }
}
Time Complexity: O(n)
Space Complexity: O(1)
```

### Delete the element with value X
```
Example 1
Input: linkedList = [3, 4, 5], X = 5
Output: [3, 4]
Explanation:
The node with value 5 was removed.

Example 2
Input: linkedList = [3, 4, 5], X = 7
Output: [3, 4, 5]
Explanation:
No nodes were removed.
```
```java
class Solution {
    public ListNode deleteNodeWithValueX(ListNode head, int X) {

        // If the list is empty, nothing to delete
        if (head == null) {
            return null;
        }

        // If the head node itself contains X,
        // delete head by moving it to the next node
        if (head.data == X) {
            return head.next;
        }

        // temp is used to traverse the list
        ListNode temp = head;

        // prev will always point to the node
        // just before temp
        ListNode prev = null;

        // Traverse the linked list
        while (temp != null) {

            // If current node has value X
            if (temp.data == X) {
                // Skip the current node
                prev.next = temp.next;
                break;
            }

            // Move prev and temp forward
            prev = temp;
            temp = temp.next;
        }

        // Return the head (unchanged if X not found)
        return head;
    }
}
Time Complexity
The time complexity is O(n), where 'n' is the number of nodes in the linked list. In the worst case, the code iterates through all 'n' nodes to find and delete the node with value X, or to determine that X is not present. The initial check and pointer assignments are O(1), and each operation within the loop is O(1).
Space Complexity
The space complexity is O(1). The code uses a constant number of extra pointers (temp and prev) regardless of the size of the linked list. No auxiliary data structures are created that scale with the input size.
```