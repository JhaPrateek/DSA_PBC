### Covers
```
Introduction to Singly Linked List
Traversal in Linked List
Deletion in Linked List
Insertion in Linked List
Deletion of the head of Linked List
Deletion of the tail of Linked List
Deletion of the Kth element of Linked List
Delete the element with value X
Insertion at the head of Linked List
Insertion at the tail of Linked List
Insertion at the Kth position of Linked List
Insertion before the value X in Linked List
```

### Traversal in Linked List
### Given the head of a singly Linked List. Traverse the entire Linked List and return its elements in an array in the order of their appearance.
```
Example 1
Input: linkedList = [5, 4, 3, 1, 0]
Output: [5, 4, 3, 1, 0]
Explanation:
The nodes in the Linked List are 5 -> 4 -> 3 -> 1 -> 0, with the head pointing to node with value 5.

Example 2
Input: linkedList = [1]
Output: [1]
Explanation:
Only one node (head) present in the list
```
```java
class ListNode{
    public int data;
    public ListNode next;
    ListNode() { data = 0; next = null; }
    ListNode(int x) { data = x; next = null; }
    ListNode(int x, ListNode next) { data = x; this.next = next; }
}

class Solution {
     //Function for Linked List Traversal
    public List<Integer> LLTraversal(ListNode head) {
        //Storing a copy of the linked list
        ListNode temp = head;
        //To store the values sequentially
        List<Integer> ans = new ArrayList<>();
        
        //Keep traversing
        //Until null is encountered
        while (temp != null) {
            //Storing the values
            ans.add(temp.data);
            //Storing the address of the next node
            temp = temp.next;
        }
        //Return answer
        return ans;
    }
}
```

###
###
```
```
```java
```