### Given the heads of two linked lists, list1 and list2, where each linked list has its elements sorted in non-decreasing order, merge them into a single sorted linked list and return the head of the merged linked list.

```
Example 1
Input: list1 = head -> 2 -> 4 -> 7 -> 9, list2 = head -> 1 -> 2 -> 5 -> 6
Output: head -> 1 -> 2 -> 2 -> 4 -> 5 -> 6 ->7 -> 9
Explanation: head -> 1 -> 2 -> 2 -> 4 -> 5 -> 6 ->7 -> 9, the underlined nodes come from list2, the others come from list1.

Example 2
Input: list1 = head -> 1 -> 2 -> 3 -> 4, list2 = head -> 5 -> 6 -> 10
Output: head -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 10
Explanation: head -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 10, the underlined nodes come from list2, the others come from list1.

Example 3
Input: list1 = head -> 0 -> 2, list2 = head -> 1 -> 3 -> 5 -> 6
Output:
head -> 0 -> 1 -> 2 -> 3 -> 5 -> 6
```
```java
class Solution {
    // Function to merge two sorted linked lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode temp1 = list1;
        ListNode temp2 = list2;

        
        // Add elements from list1 to the vector
        while (temp1 != null) {
            arr.add(temp1.val);
            // Move to the next node in list1
            temp1 = temp1.next;
        }

        // Add elements from list2 to the vector
        while (temp2 != null) {
            arr.add(temp2.val);
            // Move to the next node in list2
            temp2 = temp2.next;
        }

        // Sorting the vector in ascending order
        Collections.sort(arr);

        // Sorted vector to linked list
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;
        for (int i = 0; i < arr.size(); i++) {
            temp.next = new ListNode(arr.get(i));
            temp = temp.next;
        }

        // Return the head of 
        // merged sorted linked list
        return dummyNode.next;
    }
}
Time Complexity: O(N1 + N2) + O(N log N) + O(N) where N1 is the number of linked list nodes in the first list, N2 is the number of linked list nodes in the second list, and N is the total number of nodes (N1 + N2). Traversing both lists into the array takes O(N1 + N2), sorting the array takes O((N1 + N2) X log(N1 + N2)), and then traversing the sorted array and creating a list gives us another O(N1 + N2).    
Space Complexity: O(N) + O(N) where N is the total number of nodes from both lists (N1 + N2). O(N) to store all the nodes of both the lists in an external array and another O(N) to create a new combined list.
```

```java
class Solution {
    // Function to merge two sorted linked lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy node to serve as 
        // the head of the merged list
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;

        // Traverse both lists simultaneously
        while (list1 != null && list2 != null) {
            /*Compare elements of both lists 
            and link the smaller node 
            to the merged list*/
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            // Move the temporary pointer 
            // to the next node
            temp = temp.next;
        }

        /*If any list still 
        has remaining elements, 
        append them to the merged list*/
        if (list1 != null) {
            temp.next = list1;
        } else {
            temp.next = list2;
        }

        // Return merged list 
        return dummyNode.next;
    }
}
Time Complexity: O(N1 + N2) because both lists are traversed in a single pass for merging without any additional loops or nested iterations. Here N1 is the number of nodes in the first linked list and N2 is the number of nodes in the second linked list.
Space Complexity: O(1) because no additional data structures or space is allocated for storing data, only a constant space for pointers to maintain for traversing the linked list.

Intuition
A more efficient approach involves merging the given sorted linked lists directly without the need for an intermediate array. By taking advantage of the fact that the linked lists are already sorted, we can use a simple comparison strategy.
Position a pointer each at the beginning of both lists. Compare the current values at these pointers and choose the smaller value to add to the new merged list. Move the pointer forward in the list from which the smaller value was taken. Repeat this process until one of the lists is fully merged. At this point, attach the remaining nodes of the other list to the merged list, as they are already in order.
Approach
Set up two pointers at the start of each input linked list. Create a dummy node to serve as the anchor for the beginning of the merged list and use a separate traversal pointer to build the combined list.
While both lists have remaining nodes, compare their current values. Attach the smaller value to the merged list and advance the traversal pointer, along with the pointer from the list from which the smaller value was taken.
When one of the lists is fully traversed, directly attach the remaining nodes from the other list to the merged list, as they are already in sorted order.
The merged list starts after the dummy node. Return the next node of the dummy node as the head of the newly merged and sorted linked list.
```