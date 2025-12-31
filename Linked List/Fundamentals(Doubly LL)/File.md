```
// Doubly Linked List utilities for TUF / GFG style problems
class Node {
    int data;
    Node next;
    Node back;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.back = null;
    }
}

public class Solution {

    // --------- 1. Convert Array to Doubly Linked List ---------
    // Creates a doubly linked list from a given int array.
    public Node convertArrayToDLL(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        Node head = new Node(arr[0]);
        Node prev = head;

        for (int i = 1; i < arr.length; i++) {
            Node curr = new Node(arr[i]);
            prev.next = curr;
            curr.back = prev;
            prev = curr;
        }
        return head;
    }

    // --------- 2. Delete head of Doubly Linked List ---------
    public Node deleteHead(Node head) {
        // If empty list or single node, result is null
        if (head == null || head.next == null) {
            return null;
        }

        // Move head to next node
        Node prev = head;
        head = head.next;

        // Break links from new head to old head
        head.back = null;
        prev.next = null;

        return head;
    }

    // --------- 3. Delete tail of Doubly Linked List ---------
    public Node deleteTail(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        // Traverse to last node
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        // tail.back is new tail
        Node newTail = tail.back;
        newTail.next = null;
        tail.back = null;

        return head;
    }

    // --------- 4. Delete Kth element of Doubly Linked List ---------
    public Node deleteKthElement(Node head, int k) {
        if (head == null) return null;
        if (k <= 0) return head;

        // If first element
        if (k == 1) {
            return deleteHead(head);
        }

        int count = 1;
        Node curr = head;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }

        // If k is out of bounds
        if (curr == null) return head;

        // If last node -> delete tail
        if (curr.next == null) {
            return deleteTail(head);
        }

        // Node in the middle
        Node prev = curr.back;
        Node nxt = curr.next;

        prev.next = nxt;
        nxt.back = prev;

        curr.next = null;
        curr.back = null;

        return head;
    }

    // --------- 5. Remove given node in Doubly Linked List ---------
    // Here "node" is a reference to the node to remove (always present in list).
    public Node removeGivenNode(Node head, Node node) {
        if (node == null) return head;

        // If node is head
        if (node == head) {
            return deleteHead(head);
        }

        // If node is tail
        if (node.next == null) {
            return deleteTail(head);
        }

        // Node in the middle
        Node prev = node.back;
        Node nxt = node.next;

        prev.next = nxt;
        nxt.back = prev;

        node.next = null;
        node.back = null;

        return head;
    }

    // --------- 6. Insert node before head in Doubly Linked List ---------
    public Node insertBeforeHead(Node head, int data) {
        Node newNode = new Node(data);

        if (head == null) {
            return newNode;
        }

        newNode.next = head;
        head.back = newNode;

        return newNode; // new head
    }

    // --------- 7. Insert node before tail in Doubly Linked List ---------
    public Node insertBeforeTail(Node head, int data) {
        if (head == null || head.next == null) {
            // If 0 or 1 element, inserting before tail is inserting before head
            return insertBeforeHead(head, data);
        }

        // Find tail
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        Node prev = tail.back;
        Node newNode = new Node(data);

        newNode.next = tail;
        newNode.back = prev;
        prev.next = newNode;
        tail.back = newNode;

        return head;
    }

    // --------- 8. Insert node before Kth node in Doubly Linked List ---------
    public Node insertBeforeKth(Node head, int k, int data) {
        if (k <= 1 || head == null) {
            // Insert before first node
            return insertBeforeHead(head, data);
        }

        int count = 1;
        Node curr = head;

        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }

        // If k is greater than length, do nothing (or append, based on problem)
        if (curr == null) return head;

        // Insert before curr
        Node prev = curr.back;
        Node newNode = new Node(data);

        newNode.next = curr;
        newNode.back = prev;
        prev.next = newNode;
        curr.back = newNode;

        return head;
    }

    // --------- 9. Insert before given node in Doubly Linked List ---------
    // "node" is reference of the node before which we insert.
    public Node insertBeforeGivenNode(Node head, Node node, int data) {
        if (node == null) return head;

        // If insert before head
        if (node == head) {
            return insertBeforeHead(head, data);
        }

        Node prev = node.back;
        Node newNode = new Node(data);

        newNode.next = node;
        newNode.back = prev;

        prev.next = newNode;
        node.back = newNode;

        return head;
    }

    // --------- Utility: print list (optional for debugging) ---------
    public void printDLL(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
```