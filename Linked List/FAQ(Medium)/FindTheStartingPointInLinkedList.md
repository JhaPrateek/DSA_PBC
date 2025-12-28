### Given the head of a singly linked list, the task is to find the starting point of a loop in the linked list if it exists. Return the starting node if a loop exists; otherwise, return null. A loop exists in a linked list if some node in the list can be reached again by continuously following the next pointer. Internally, pos denotes the index (0-based) of the node from where the loop starts. Note that pos is not passed as a parameter.

```java
Brute
class Solution {
    public ListNode findStartingPoint(ListNode head) {
        // Use temp to traverse the linked list
        ListNode temp = head;
        
        // HashMap to store all visited nodes
        HashMap<ListNode, Integer> map = new HashMap<>();
        
        // Traverse the list using temp
        while (temp != null) {
            // Check if temp has been encountered again
            if (map.containsKey(temp)) {
                // A loop is detected hence return temp
                return temp;
            }
            // Store temp as visited
            map.put(temp, 1);
            // Move to the next node
            temp = temp.next;
        }

        // If no loop is detected, return null
        return null;
    }
}
Time Complexity: O(N) The algorithm goes through the entire linked list once, with 'N' representing the total number of nodes. As a result, the time complexity is linear, or O(N).
Space Complexity: O(N) The algorithm utilizes a hash map to store the nodes it encounters. This hash map can store up to 'N' nodes, where 'N' is the total number of nodes in the list. Therefore, the space complexity is O(N) because of the additional space used by the hash map.
```

```java
Optimal
class Solution {
    public ListNode findStartingPoint(ListNode head) {
        // Initialize a slow and fast 
        // pointers to the head of the list
        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect the loop
        while (fast != null && fast.next != null) {
            
            // Move slow one step
            slow = slow.next;
            
            // Move fast two steps
            fast = fast.next.next;

            // If slow and fast meet,
            // a loop is detected
            if (slow == fast) {
                
                // Reset the slow pointer
                // to the head of the list
                slow = head;

                // Phase 2: Find the first node of the loop
                while (slow != fast) {
                    
                    // Move slow and fast one step
                    // at a time
                    slow = slow.next;
                    fast = fast.next;

                    // When slow and fast meet again,
                    // it's the first node of the loop
                }
                
                // Return the first node of the loop
                return slow;
            }
        }
        
        // If no loop is found, return null
        return null;
    }
}   
Time Complexity: O(N) The code examines each node in the linked list exactly once, where 'N' is the total number of nodes. This results in a linear time complexity, O(N), as the traversal through the list is direct and sequential.
Space Complexity: O(1) The code uses a fixed amount of extra space, regardless of the size of the linked list. This is accomplished by employing two pointers, slow and fast, to detect the loop. Since no additional data structures are used that depend on the size of the list, the space complexity remains constant, O(1).

Intuition
The previous method utilizes O(N) additional memory, which can be of concern when dealing with longer linked lists. To improve efficiency, we can use the Tortoise and Hare Algorithm, which is an optimized approach that reduces memory usage. This algorithm uses two pointers moving at different speeds to detect loops more efficiently.

Approach
Initialization: Initialize two pointers, slow and fast, to the head of the linked list. The slow pointer will advance one step at a time, while the fast pointer will advance two steps at a time. These pointers will move simultaneously through the list.

Traversal: As the traversal progresses, move the slow pointer one step and the fast pointer two steps at a time. This continues until one of two conditions is met: if fast or fast.next reaches the end of the linked list (i.e., becomes null), it means there is no loop in the linked list, and the algorithm terminates by returning null. Alternatively, if the fast and slow pointers meet at the same node, it indicates the presence of a loop in the linked list.

Finding the Loops Start: Once a loop is detected, reset the slow pointer to the head of the linked list. Then, move both the fast and slow pointers one step at a time. The point where they meet again is identified as the starting point of the loop. This method ensures efficient detection and pinpointing of the loops starting location in the linked list.


Proof of the Approach
You might wonder how this algorithm works, and it all comes down to the concept that the meeting point of the slow and fast pointers can be used to find the start of the loop.

In the "tortoise and hare" method for detecting loops in a linked list, when the slow pointer (tortoise) reaches the start of the loop, the fast pointer (hare) is at a position that is twice the distance traveled by the slow pointer. This happens because the hare moves twice as fast as the tortoise.


```