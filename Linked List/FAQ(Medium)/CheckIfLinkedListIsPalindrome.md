### Given the head of a singly linked list representing a positive integer number. Each node of the linked list represents a digit of the number, with the 1st node containing the leftmost digit of the number and so on. Check whether the linked list values form a palindrome or not. Return true if it forms a palindrome, otherwise, return false. A palindrome is a sequence that reads the same forward and backwards.
```
Example 1
Input: head -> 3 -> 7 -> 5 -> 7 -> 3
Output: true
Explanation: 37573 is a palindrome.

Example 2
Input: head -> 1 -> 1 -> 2 -> 1
Output: false
Explanation: 1121 is not a palindrome.
```
```java
class Solution {

    /* Function to reverse a linked list
       using the iterative approach */
    private ListNode reverseLinkedList(ListNode head) {
        // Initialize previous pointer as null
        ListNode prev = null;

        // Initialize current pointer as head
        ListNode curr = head;

        // Traverse the list until all nodes are processed
        while (curr != null) {

            // Temporarily store the next node
            ListNode nextNode = curr.next;

            // Reverse the link direction
            curr.next = prev;

            // Move 'prev' one step forward
            prev = curr;

            // Move 'curr' one step forward
            curr = nextNode;
        }

        // 'prev' now points to the new head after reversal
        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        /* Check if the linked list is empty
           or has only one node */
        if (head == null || head.next == null) {
            // It's a palindrome by definition
            return true;
        }

        /* Initialize two pointers, slow and fast,
           to find the middle of the linked list */
        ListNode slow = head;
        ListNode fast = head;

        /* Traverse the linked list to find the
           middle using the slow-fast pointer approach */
        while (fast.next != null && fast.next.next != null) {

            // Move slow pointer one step
            slow = slow.next;

            // Move fast pointer two steps
            fast = fast.next.next;
        }

        /* Reverse the second half of the linked list
           starting from the node after the middle */
        ListNode newHead = reverseLinkedList(slow.next);

        // Pointer to the first half
        ListNode first = head;

        // Pointer to the reversed second half
        ListNode second = newHead;

        /* Compare nodes from both halves
           one by one to check for palindrome */
        while (second != null) {

            // If mismatch found, it's not a palindrome
            if (first.val != second.val) {

                // Restore the original list before returning
                reverseLinkedList(newHead);

                return false;
            }

            // Move both pointers one step ahead
            first = first.next;
            second = second.next;
        }

        /* Restore the second half of the linked list
           to its original order */
        reverseLinkedList(newHead);

        // All values matched, the list is a palindrome
        return true;
    }
}
Time Complexity: O(2xN) The algorithm involves traversing the linked list twice. The first traversal finds the middle and reverses the second half, while the second traversal compares elements from both halves. Since each traversal covers N/2 elements, the total time complexity is O(N/2 + N/2 + N/2 + N/2), which simplifies to O(2N), ultimately reducing to O(N).

Space Complexity: O(1) This approach uses a constant amount of additional space, regardless of the linked lists size. It does not require any extra data structures that depend on the input size, resulting in a space complexity of O(1).

Intuition
The previous approach uses O(N) additional space, which can be avoided by reversing only half of the linked list and comparing the first and second halves. If they match, reverse the portion that was originally reversed, and then return true; otherwise, return false. To implement this, we need to reverse the second half and compare it with the first half in phases. The first step is to divide the linked list into two halves by finding the middle node using the Tortoise and Hare Algorithm.
```