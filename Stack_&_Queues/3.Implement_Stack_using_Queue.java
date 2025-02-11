```java
class Stack {
    Queue<Integer> q = new LinkedList<>(); // Using a single queue to implement stack

    // Push operation: Adds an element to the stack
    void push(int x) {
        q.add(x); // Add the new element to the queue

        // Rotate the queue to maintain stack order (LIFO)
        for (int i = 0; i < q.size() - 1; i++) {
            q.add(q.remove()); // Move front element to the rear
        }
    }

    // Pop operation: Removes and returns the top element of the stack
    int pop() {
        if (q.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return q.remove(); // Removes the front element, which is the top of the stack
    }

    // Top operation: Returns the top element without removing it
    int top() {
        if (q.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return q.peek(); // Retrieves the front element, which is the top of the stack
    }

    // Size operation: Returns the current number of elements in the stack
    int size() {
        return q.size();
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Top element: " + stack.top()); // Should print 30
        System.out.println("Popped element: " + stack.pop()); // Should print 30
        System.out.println("Stack size: " + stack.size()); // Should print 2
    }
}
```
