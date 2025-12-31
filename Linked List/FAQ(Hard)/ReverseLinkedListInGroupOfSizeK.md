### Given the head of a singly linked list containing integers, reverse the nodes of the list in groups of k and return the head of the modified list. If the number of nodes is not a multiple of k, then the remaining nodes at the end should be kept as is and not reversed. Do not change the values of the nodes, only change the links between nodes.

```
Example 1
Input: head -> 1 -> 2 -> 3 -> 4 -> 5, k = 2
Output: head -> 2 -> 1 -> 4 -> 3 -> 5
Explanation: The groups 1 -> 2 and 3 -> 4 were reversed as 2 -> 1 and 4 -> 3.

Example 2
Input: head -> 1 -> 2 -> 3 -> 4 -> 5, k = 3
Output: head -> 3 -> 2 -> 1 -> 4 -> 5
Explanation: The groups 1 -> 2 -> 3 were reversed as 3 -> 2 -> 1.
Note that 4 -> 5 was not reversed.
```
```java
class Solution {

    // Reverse nodes of the linked list in groups of size k
    public ListNode reverseKGroup(ListNode head, int k) {

        // temp → points to the start of the current group
        ListNode temp = head;

        // nextNode → stores the node after the current k-group
        // prevLast → tail of the previously reversed group
        ListNode nextNode = null, prevLast = null;

        // Traverse the linked list group by group
        while (temp != null) {

            // Find the k-th node from the current position
            ListNode kthNode = findKthNode(temp, k);

            // If fewer than k nodes are remaining
            if (kthNode == null) {

                // Connect the last reversed group to remaining nodes
                if (prevLast != null) {
                    prevLast.next = temp;
                }
                break; // no more reversal possible
            }

            // Store the node after the k-th node
            nextNode = kthNode.next;

            // Break the list to isolate current k-group
            kthNode.next = null;

            // Reverse the current group
            rev(temp);

            // If this is the first group, update head
            if (head == temp) {
                head = kthNode;
            } 
            // Otherwise, connect previous group to current reversed group
            else {
                prevLast.next = kthNode;
            }

            // prevLast should now point to the tail of the reversed group
            prevLast = temp;

            // Move temp to the start of the next group
            temp = nextNode;
        }

        return head;
    }

    // Reverse an entire linked list and return new head
    public ListNode rev(ListNode head) {

        ListNode temp = head;
        ListNode prev = null;

        // Standard linked list reversal
        while (temp != null) {
            ListNode curr = temp.next; // store next node
            temp.next = prev;          // reverse link
            prev = temp;               // move prev forward
            temp = curr;               // move temp forward
        }

        // prev becomes the new head after reversal
        return prev;
    }

    // Find the k-th node starting from given node
    public ListNode findKthNode(ListNode node, int k) {

        k--; // current node is counted as 1st node

        // Move forward k-1 times
        while (node != null && k > 0) {
            k--;
            node = node.next;
        }

        // Returns k-th node or null if not enough nodes
        return node;
    }
}
Time Complexity: O(2N) because it consists of actions of reversing segments of K and finding the Kth node, both of which operate in linear time. Thus, O(N) + O(N) = O(2N), which simplifies to O(N).
Space Complexity: O(1) because the code operates in place without any additional space requirements.

Intuition
The intuition is to reverse the nodes of the linked list in groups of k by identifying each group, reversing the links within that group, and then connecting the reversed groups, leaving any remaining nodes as they are if the group is less than k.

Approach
First, traverse the linked list to identify segments of K nodes. For each segment, adjust the pointers within the segment to reverse the direction of the nodes. This involves iterating through the segment and changing the links between nodes.

Next, after reversing a segment, connect the reversed segment to the previous part of the list. Continue this process until you reach the end of the list.

Finally, if there are fewer than K nodes left at the end of the list, leave them as they are. Return the head of the modified linked list.

Below is the algorithm for the approach:-
Initialize a pointer temp to the head of the linked list. Using temp, traverse to the Kth node iteratively.
Upon reaching the Kth node, preserve the Kth node’s next node as nextNode and set the Kth node’s next pointer to null. This effectively breaks the linked list into a smaller list of size K that can be reversed and attached back.
Treat this segment from temp to the Kth node as an individual linked list and reverse it. This can be done using a helper function that reverses the linked list.
The reversed linked list segment returns a modified list with temp now at its tail and the Kth node pointing to its head. Update temps next pointer to nextNode. If reversing the first segment of K nodes, update the head to the Kth node.
Continue this reversal process for further groups. If a segment has fewer than K nodes, leave them unmodified and return the new head. Use the prevLast pointer to maintain the link between the end of the previous reversed segment and the current segment.
```