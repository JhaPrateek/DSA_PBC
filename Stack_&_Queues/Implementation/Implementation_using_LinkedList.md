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

class MyQueue {
    QueueNode front, rear; // Pointers to the front and rear of the queue

    // Function to push (enqueue) an element into the queue
    void push(int a) {
        QueueNode qn = new QueueNode(a); // Create a new node with the given value

        if (front == null && rear == null) { // If the queue is empty
            front = qn; // Both front and rear point to the new node
            rear = qn;
        } else {
            rear.next = qn; // Link the current rear to the new node
            rear = qn; // Move the rear pointer to the new node
        }
    }

    // Function to pop (dequeue) the front element from the queue
    int pop() {
        if (front == null || rear == null) { // If the queue is empty
            return -1; // Return -1 to indicate an empty queue
        } else if (front == rear) { // If only one element is present
            QueueNode q = front; // Store the current front node
            front = null; // Set front and rear to null (empty queue)
            rear = null;
            return q.data; // Return the removed element's data
        } else {
            int val = front.data; // Store the front element's value
            front = front.next; // Move the front pointer to the next node
            return val; // Return the removed element's data
        }
    }
}

```
