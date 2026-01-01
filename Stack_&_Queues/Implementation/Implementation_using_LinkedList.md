```java
// Stack implementation

class stack {
  // Private inner class representing a node in the stack
  private class stackNode {
    int data; // Stores the value of the node
    stackNode next; // Pointer to the next node in the stack

    // Constructor to initialize a new node with given data
    stackNode(int d) {
      data = d;
      next = null;
    }
  }

  stackNode top; // Pointer to the top of the stack
  int size; // Variable to keep track of the stack size

  // Constructor to initialize an empty stack
  stack() {
    this.top = null;
    this.size = 0;
  }

  // Method to push an element onto the stack
  public void stackPush(int x) {
    stackNode element = new stackNode(x); // Create a new node
    element.next = top; // Point new node's next to the current top
    top = element; // Update top to the new node
    System.out.println("Element pushed");
    size++; // Increase stack size
  }

  // Method to pop an element from the stack
  public int stackPop() {
    if (top == null) return -1; // Check if the stack is empty
    int topData = top.data; // Store the top element's data
    stackNode temp = top; // Temporary reference to top
    top = top.next; // Move top to the next node
    size--;
    return topData; // Return popped value
  }

  // Method to return the current size of the stack
  public int stackSize() {
    return size;
  }

  // Method to check if the stack is empty
  public boolean stackIsEmpty() {
    return top == null; // If top is null, stack is empty
  }

  // Method to print all elements in the stack
  public void printStack() {
    stackNode current = top; // Start from top
    while (current != null) { // Traverse till the end of stack
      System.out.print(current.data + " "); // Print current node's data
      current = current.next; // Move to next node
    }
    System.out.println(); // Print a newline after stack elements
  }
}
```

```java
// Queue implementation

class Queue {

    // Node of linked list
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front; // points to first element
    private Node rear;  // points to last element

    // Constructor
    public Queue() {
        front = null;
        rear = null;
    }

    // Enqueue: Add element at rear
    public void enqueue(int data) {
        Node newNode = new Node(data);

        // If queue is empty
        if (rear == null) {
            front = rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    // Dequeue: Remove element from front
    public int dequeue() {
        if (front == null) {
            System.out.println("Queue is empty");
            return -1;
        }

        int removed = front.data;
        front = front.next;

        // If queue becomes empty
        if (front == null) {
            rear = null;
        }

        return removed;
    }

    // Peek: Get front element
    public int peek() {
        if (front == null) {
            System.out.println("Queue is empty");
            return -1;
        }
        return front.data;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return front == null;
    }
}

```
