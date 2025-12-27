### Given the head of a singly linked list and an integer n. Remove the nth node from the back of the linked List and return the head of the modified list. The value of n will always be less than or equal to the number of nodes in the linked list.

```
Example 1
Input: head -> 1 -> 2 -> 3 -> 4 -> 5, n = 2
Output: head -> 1 -> 2 -> 3 -> 5
Explanation: The 2nd node from the back was the node with value 4.

Example 2
Input: head -> 5 -> 4 -> 3 -> 2 -> 1, n = 5
Output: head -> 4 -> 3 -> 2 -> 1
Explanation: The 5th node from the back is the first node.
```
```java
Brute
class Solution {
    // Function to remove the nth node from end
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int cnt = 0;
        ListNode temp = head;

        // Count the number of nodes
        while (temp != null) {
            cnt++;
            temp = temp.next;
        }

        /* If N equals 
        the total number of nodes
        delete the head */
        if (cnt == n) {
            ListNode newHead = head.next;
            return newHead;
        }

        /* Calculate the position 
        of the node to delete (res) */
        int res = cnt - n;
        temp = head;

        /* Traverse to the node 
        just before the one to delete */
        while (temp != null) {
            res--;
            if (res == 0) {
                break;
            }
            temp = temp.next;
        }

        // Delete the Nth node from the end
        ListNode delNode = temp.next;
        temp.next = temp.next.next;
        return head;
    }
}
Time Complexity: O(L) + O(L-N) We are calculating the length of the linked list and then iterating up to the (L-N)th node of the linked list, where L is the total length of the list and N is the position of the node to delete.
Space Complexity: O(1) as we have not used any extra space.

Intuition
To delete the nth node from the end of a linked list, we first need to understand the formula (L-N+1), where L is the total length of the linked list. This formula helps us find the position of the node to delete from the start of the list:

L is the total number of nodes in the list. N is the position of the node from the end.
L - N gives the position of the node from the start in a 0-based index. Adding 1 converts it to a 1-based index, resulting in (L-N+1).
```
```java
Optimal
class Solution {
    // Function to remove the nth node from end
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Creating pointers
        ListNode fastp = head;
        ListNode slowp = head;

        /* Move the fastp pointer 
        N nodes ahead */
        for (int i = 0; i < n; i++) {
            fastp = fastp.next;
        }

        /* If fastp becomes NULL
        the Nth node from the 
        end is the head */
        if (fastp == null) {
            return head.next;
        }

        /* Move both pointers 
        Until fastp reaches the end */
        while (fastp.next != null) {
            fastp = fastp.next;
            slowp = slowp.next;
        }

        // Delete the Nth node from the end
        ListNode delNode = slowp.next;
        slowp.next = slowp.next.next;
        return head;
    }
}
Time Complexity: O(N) since the fast pointer will traverse the entire linked list, where N is the length of the linked list.
Space Complexity: O(1), as we have not used any extra space.

Intution

To enhance efficiency, we use a two-pointer technique involving a fast pointer and a slow pointer. The fast pointer is initially positioned exactly N nodes ahead of the slow pointer. Then, both pointers move one step at a time. When the fast pointer reaches the last node (the L-th node), the slow pointer will be at the (L-N)-th node. This allows us to remove the nth node from the end in a single traversal, improving the time complexity to O(L).
```