### Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

```java
Brute
class Solution {
    // Function to find the intersection node of two linked lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Create a hash set to store the nodes
        // Of the first list
        HashSet<ListNode> nodes_set = new HashSet<>();

        // Traverse the first linked list
        // And add all its nodes to the set
        while (headA != null) {
            nodes_set.add(headA);
            headA = headA.next;
        }

        // Traverse the second linked list
        // And check for intersection
        while (headB != null) {
            // If a node from the second list is found in the set,
            // It means there is an intersection
            if (nodes_set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }

        // No intersection found, return null
        return null;
    }
}
Time Complexity: O(N + M), where N and M are the lengths of the first and second linked list respectively.

Traversing the first list and adding the nodes to the hashset takes O(N) time assuming the hashset takes O(1) time for operations. Iterating through all nodes in the second list takes O(M) time. Therefore, the total time complexity is O(N + M).
Note: If the hashset takes logarithmic time for operations, the time complexity get a multiple of logN.

Space Complexity: O(N)
Using an hashset to store the addresses of all nodes in the first list takes O(N) space.

Intuition:
To find where two linked lists intersect, we can use a hash set to remember all the nodes from the first list. Then, as we go through the second list, we check if any node is already in the hash set. The first node that is found in the set is where the lists intersect.
```
```java
Better
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        int n1 = 0, n2 = 0;

        // Get the length of first linked list
        while (temp1 != null) {
            n1++;
            temp1 = temp1.next;
        }

        // Get the length of second linked list
        while (temp2 != null) {
            n2++;
            temp2 = temp2.next;
        }

        // Traverse the longer list and bring the pointers to same level
        if (n1 < n2) return collisionPoint(headA, headB, n2 - n1);

        return collisionPoint(headB, headA, n1 - n2);
    }

    private ListNode collisionPoint(ListNode smallerListHead, ListNode longerListHead, int len) {
        ListNode temp1 = smallerListHead;
        ListNode temp2 = longerListHead;

        // Adjust the pointers to same level
        for (int i = 0; i < len; i++) temp2 = temp2.next;

        while (temp1 != temp2) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return temp1;
    }
}
Time Complexity: O(N + M), where N and M are the lengths of first and second linked list respectively.
Calculating the lengths of the two linked list takes O(N) and O(M) time. Another O(|N-M|) time is needed for aligning the nodes. The final traversal of the aligned lists takes O(min(N,M)) time in the worst case. Thus, the overall time complexity is O(N + 2M) or O(N + M).
Space Complexity: O(1), because only a couple of pointers are used.
```
```java
class Solution {
    // Function to find the intersection node of two linked lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Edge case
        if (headA == null || headB == null) return null;

        // Initialize two pointers to traverse the lists
        ListNode d1 = headA;
        ListNode d2 = headB;

        // Traverse both lists until the pointers meet
        while (d1 != d2) {
            // Move both the pointers by one place
            d1 = d1.next;
            d2 = d2.next;

            // If intersection is found
            if (d1 == d2) return d1;

            // If either of the two pointers reaches end, place at the front of next linked list 
            if (d1 == null) d1 = headB;
            if (d2 == null) d2 = headA;
        }

        // Return the intersection node
        return d1;
    }
}
Time Complexity: O(N + M), where N and M are the lengths of first and second linked list respectively.
In the worst case (when the last node in a linked list is the intersection node), both the pointers will traverse the total length of the two linked lists before meeting at the intersection node. Hence, the time complexity is O(N + M).
Space Complexity: O(1), as no extra space was used.

Intuition:
The simple intuition behind the optimal approach is to use two pointers to traverse the lists and traverse the same total distance by the time the pointers reach the intersection node. If one list ends, the pointer is placed at the front of the other list. The two pointers will meet at the intersection node if the lists have an intersection, otherwise, they will reach the end of the lists at the same time.
```