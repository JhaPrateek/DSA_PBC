### Given the head of a non-empty singly linked list containing integers, delete the middle node of the linked list. Return the head of the modified linked list. The middle node of a linked list of size n is the (⌊n / 2⌋ + 1)th node from the start using 1-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.

```
Example 1
Input: head -> 1 -> 2 -> 3 -> 4 -> 5
Output: head -> 1 -> 2 -> 4 -> 5
Explanation: n = 5.
⌊n / 2⌋ + 1 = 3, therefore middle node has index 3 and so the node with value 3 was deleted.

Example 2
Input: head -> 7 -> 6 -> 5 -> 4
Output: head -> 7 -> 6 -> 4
Expl﻿anation: n = 4.
⌊n / 2⌋ + 1 = 3, therefore middle node has index 3 and so the node with value 5 was deleted.
```
```java
class Solution {
    // Function to delete the middle node of a linked list
    public ListNode deleteMiddle(ListNode head) {
        /* If the list is empty or has only one node,
         * return null as there is no middle node to delete */
        if (head == null || head.next == null) {
            return null;
        }

        // Initialize slow and fast pointers
        ListNode slow = head;
        ListNode fast = head.next.next;

        // Move 'fast' pointer twice as fast as 'slow'
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Delete the middle node by skipping it
        slow.next = slow.next.next;
        return head;
    }
}
Time Complexity: O(N/2) because the code traverses the linked list using the Tortoise and Hare approach. The code increments both 'slow' and 'fast' pointers at different rates, effectively covering about half the list before reaching the midpoint, hence the time complexity of the algorithm is O(N/2) ~ O(N).

Space Complexity: O(1) because the code uses a constant amount of extra space regardless of the size of the input (linked list). It doesnt use any additional data structures in proportion to the input size.

Intuition
The brute force method involves traversing the linked list twice to find and delete the middle node. To make this more efficient,we use the Tortoise and Hare approach which helps in finding the middle node in one traversal by moving the 'slow' pointer one step and the 'fast' pointer two steps at a time.

This ensures the 'slow' pointer reaches the middle when the 'fast' pointer reaches the end. To have 'slow' reach just before the middle, the 'fast' pointer gets a head start.
```