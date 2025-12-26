
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

### Implementation

```java
class ListNode {
    int data;
    ListNode next;

    ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}
class LinkedList {

    private ListNode head;

    // 🔹 Insert at head
    public void insertAtHead(int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = head;
        head = newNode;
    }

    // 🔹 Insert at tail
    public void insertAtTail(int data) {
        ListNode newNode = new ListNode(data);

        // If list is empty
        if (head == null) {
            head = newNode;
            return;
        }

        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // 🔹 Delete node with value X
    public void deleteByValue(int X) {

        if (head == null) return;

        // If head itself needs to be deleted
        if (head.data == X) {
            head = head.next;
            return;
        }

        ListNode temp = head;
        while (temp.next != null) {
            if (temp.next.data == X) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    // 🔹 Search for a value
    public boolean search(int X) {
        ListNode temp = head;
        while (temp != null) {
            if (temp.data == X) return true;
            temp = temp.next;
        }
        return false;
    }

    // 🔹 Print linked list
    public void printList() {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}

```