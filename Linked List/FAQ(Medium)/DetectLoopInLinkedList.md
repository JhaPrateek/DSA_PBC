### 

```java
Optimised
class Solution {
    // Function to detect a loop in a linked
    // list using the Tortoise and Hare Algorithm
    public boolean hasCycle(ListNode head) {
        // Initialize two pointers, slow and fast,
        // to the head of the linked list
        ListNode slow = head;
        ListNode fast = head;

        // Step 2: Traverse the linked list with
        // the slow and fast pointers
        while (fast != null && fast.next != null) {
            // Move slow one step
            slow = slow.next;
            // Move fast two steps
            fast = fast.next.next;

            // Check if slow and fast pointers meet
            if (slow == fast) {
                return true;  // Loop detected
            }
        }

        // If fast reaches the end of the list,
        // there is no loop
        return false;
    }
}
Time Complexity: O(N), where N represents the number of nodes in the linked list. In the worst-case scenario, the fast pointer, which advances more quickly, will either reach the end of the list (if there's no loop) or catch up to the slow pointer (if there's a loop) in a time proportional to the length of the list.

The reason this complexity is O(N) and not slower is due to the fact that each step of the algorithm decreases the gap between the fast and slow pointers (when they are within the loop) by one node. Thus, the maximum number of steps required for them to meet is directly related to the number of nodes in the list.

Space Complexity: O(1) The algorithm utilizes a constant amount of additional space, regardless of the size of the linked list. This efficiency is achieved by using only two pointers (slow and fast) to detect the loop, without needing any significant extra memory, resulting in a constant space complexity of O(1).   

Intuition
In a linked list with a loop, we can use two pointers to detect the cycle: one moves one node at a time (slow) and the other moves two nodes at a time (fast). As these pointers start moving through the list, they will eventually enter the loop and end up some distance 'd' apart within the loop.

The key idea is to observe the relative speeds of these pointers. Since the fast pointer moves twice as fast as the slow pointer, it reduces the distance between them by one node with each iteration. This is akin to a faster runner catching up to a slower runner in a race, where the faster runner closes the gap between them steadily.

In the context of the linked list, the fast pointer will eventually catch up to the slow pointer within the loop, thereby confirming the presence of a cycle. This happens because the fast pointer, moving at double the speed, progressively shortens the distance until it becomes zero.
```

```java
class Solution {
    // Function to detect a loop in the linked list
    public boolean hasCycle(ListNode head) {
        // Initialize a pointer 'temp'
        // At the head of the linked list
        ListNode temp = head;  

        // Create a set to keep track of
        // Encountered nodes
        HashSet<ListNode> nodeSet = new HashSet<>();  

        // Traverse the linked list
        while (temp != null) {
            // If the node is already in the
            // Set, there is a loop
            if (nodeSet.contains(temp)) {
                return true;
            }
            // Store the current node
            // In the set
            nodeSet.add(temp);
            
            // Move to the next node
            temp = temp.next;  
        }

        // If the list is successfully traversed 
        // Without a loop, return false
        return false;
    }
}
Time Complexity: O(N), where N is the number of nodes in the LL.
The algorithm traverses the linked list once, and each insertion or lookup operation in a HashSet (or unordered_set) takes O(1) on average due to hashing. Hence, total time = O(N) * O(1) = O(N).
(Note: In the worst case of excessive hash collisions, it can degrade to O(N2), but this is extremely rare with a good hash function.)

Space Complexity: O(N)The HashSet stores references to all visited nodes in the worst case (when no loop exists), resulting in O(N) auxiliary space.

Intuition
A loop in a linked list happens when a node points back to one of the previous nodes, creating a cycle. This means that if you keep following the next pointers, you will eventually return to the same node. One common way to do this is by using hashing.
```