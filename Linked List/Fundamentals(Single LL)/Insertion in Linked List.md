### Insertion at the head of Linked List
```
Example 1
Input: linkedList = [1, 2, 3], X = 7
Output: [7, 1, 2, 3]
Explanation:
7 was added as the 1st node.

Example 2
Input: linkedList = [], X = 7
Output: [7]
Explanation:
7 was added as the 1st node.
```
```java
class Solution {
    // Function to insert at head
    public ListNode insertAtHead(ListNode head, int X) {
        // Creating a new node 
        ListNode newnode = new ListNode(X);
        
        /* Making next of newly created node to 
        point to the head of the LinkedList */
        newnode.next = head;
        
        // Making newly created node as head
        head = newnode;
        
        // Return the head of modified list
        return head;
    }
}
Time Complexity: O(1) for inserting the new node at the head of the linked list
Space Complexity: O(1) no extra space used.
```

### Insertion at the tail of Linked List
```
Example 1
Input: linkedList = [1, 2, 3], X = 7
Output: [1, 2, 3, 7]
Explanation:
7 was added as the last node.

Example 2
Input: linkedList = [], X = 0
Output: [0]
Explanation:
0 was added as the last/only node.
```
```java
class Solution {
    // Function to insert a new node at the tail of the linked list
    public ListNode insertAtTail(ListNode head, int X) {
        if (head == null)
            return new ListNode(X);

        ListNode temp = head;
        // Traversing until the last node
        while (temp.next != null) {
            temp = temp.next;
        }

        ListNode newNode = new ListNode(X);
        temp.next = newNode;

        return head;
    }
}
Time Complexity: O(N) for traversing the linked list and inserting the new node after the tail. Here N is the length of the Linked List.
Space Complexity: O(1) as no extra space used.
```

### Insertion at the Kth position of Linked List
```
Example 1
Input: linkedList = [1, 2, 3], X = 5, K = 2
Output: [1, 5, 2, 3]

Example 2
Input: linkedList = [], X = 7, K = 1
Output: [7]
Explanation:
Note that the value of the head was changed.
```
```java
class Solution {
    // Function to insert a new node at the kth position 
    public ListNode insertAtKthPosition(ListNode head, int X, int K) {
        /* If the linked list is empty 
        and k is 1, insert the 
        new node as the head */
        if (head == null) {
            if (K == 1)
                return new ListNode(X);
            else
                return head;
        }

        /* If K is 1, insert the new
        node at the beginning 
        of the linked list */
        if (K == 1)
            return new ListNode(X, head);

        int cnt = 0;
        ListNode temp = head;

        /* Traverse the linked list 
        to find the node at position k-1 */
        while (temp != null) {
            cnt++;
            if (cnt == K - 1) {
                /* Insert the new node after the node 
                at position k-1 */
                ListNode newNode = new ListNode(X, temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }

        return head;
    }
}
Time Complexity: O(N) worst case, when insertion happens at the tail and O(1) best case, when insertion happens at the head.
Space Complexity: O(1) no extra space used.
```

###
```
Example 1
Input: linkedList = [1, 2, 3], X = 2, val = 5
Output: [1, 5, 2, 3]
Explanation:
The node with value 5 was added before the node with value 2

Example 2
Input: linkedList = [1, 2, 3], X = 7, val = 5
Output: [1, 2, 3]
Explanation:
No node was added as X was not found in the list.
```
```java
class Solution {
    public ListNode insertBeforeX(ListNode head, int X, int val) {

        // If the list is empty, nothing to insert before
        if (head == null) {
            return head;
        }

        // Create new node with given value
        ListNode newNode = new ListNode(val);

        // If head itself contains X,
        // insert new node before head
        if (head.data == X) {
            newNode.next = head;
            return newNode;
        }

        // temp is used to traverse the list
        ListNode temp = head;

        // Traverse until second last node
        while (temp.next != null) {

            // If next node contains X,
            // insert new node before it
            if (temp.next.data == X) {
                newNode.next = temp.next;
                temp.next = newNode;
                break;
            }

            temp = temp.next;
        }

        // Return the head of the list
        return head;
    }
}
Time Complexity: O(N) worst case, when insertion happens at the tail and O(1) best case, when insertion happens at the head.
Space Complexity: O(1) no extra space used.
```