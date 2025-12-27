### Given the head of a singly linked list consisting of only 0, 1 or 2. Sort the given linked list and return the head of the modified list. Do it in-place by changing the links between the nodes without creating new nodes.

```
Example 1
Input: linkedList = [1, 0, 2, 0 , 1]
Output: [0, 0, 1, 1, 2]
Explanation: The values after sorting are [0, 0, 1, 1, 2].

Example 2
Input: linkedList = [1, 1, 1, 0]
Output: [0, 1, 1, 1]
Explanation: The values after sorting are [0, 1, 1, 1].
```

```java
class Solution {
    // Function to sort the linked list
    public ListNode sortList(ListNode head) {
        // Initialize counts
        int c0 = 0, c1 = 0, c2 = 0;
        ListNode temp = head;

        /* Count the number of 0s, 
           1s, and 2s in the list */
        while (temp != null) {
            if (temp.data == 0)
                c0++;
            else if (temp.data == 1)
                c1++;
            else if (temp.data == 2)
                c2++;
            temp = temp.next;
        }

        temp = head;

        /* Reassign values to 
           the nodes based on 
           the counts */
        while (temp != null) {
            if (c0 > 0) {
                temp.data = 0;
                c0--;
            } else if (c1 > 0) {
                temp.data = 1;
                c1--;
            } else if (c2 > 0) {
                temp.data = 2;
                c2--;
            }
            temp = temp.next;
        }

        return head;
    }
}
Time Complexity: O(2N) because the code traverses the linked list twice: once while counting the frequency of 0s, 1s, and 2s, and once again while reassigning the values to the nodes. Here, N represents the length of the linked list or the number of nodes present in the linked list.
Space Complexity: O(1) because no extra space is used.
```

```java
class Solution {
    // Function to sort the linked list
    public ListNode sortList(ListNode head) {
        /* If the list is empty or has only one 
           node, return as it is already sorted */
        if (head == null || head.next == null)
            return head;

        // Dummy nodes to point to heads of 
        // three lists
        ListNode zeroHead = new ListNode(-1);
        ListNode oneHead = new ListNode(-1);
        ListNode twoHead = new ListNode(-1);

        // Pointers to current last nodes of 
        // three lists
        ListNode zero = zeroHead;
        ListNode one = oneHead;
        ListNode two = twoHead;
        ListNode temp = head;

        /* Traverse the original list 
           and distribute the nodes 
           into three lists */
        while (temp != null) {
            if (temp.data == 0) {
                zero.next = temp;
                zero = temp;
            } else if (temp.data == 1) {
                one.next = temp;
                one = temp;
            } else if (temp.data == 2) {
                two.next = temp;
                two = temp;
            }
            temp = temp.next;
        }

        // Connect the three lists together
        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        one.next = twoHead.next;
        two.next = null;

        // New head of the sorted list
        ListNode newHead = zeroHead.next;

        // Delete dummy nodes
        return newHead;
    }
}
Time Complexity: O(N) because the code traverses the linked list once. Here, N represents the length of the linked list or the number of nodes present in the linked list.
Space Complexity: O(1) because no extra space is used and we just change the links of the nodes.

Intuition
Traverse the linked list to segregate nodes into three separate lists based on their values (0s, 1s, and 2s), then link these lists together to form a single sorted linked list.
```