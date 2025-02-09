// Approach - 1 (For optimeised peek and pop)

import java.util.*;

class MyQueue {
    Stack<Integer> input = new Stack<>(); // Stack to store queue elements in reverse order
    Stack<Integer> output = new Stack<>(); // Temporary stack to help reorder elements

    /** Initialize your data structure here. */
    public MyQueue() {
        // Constructor to initialize the queue
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        // Move all elements from input stack to output stack
        while (!input.empty()) {
            output.push(input.pop());
        }

        // Insert the new element into the input stack
        System.out.println("The element pushed is " + x);
        input.push(x);

        // Move all elements back from output stack to input stack
        while (!output.empty()) {
            input.push(output.pop());
        }
    }

    /** Removes the element from the front of the queue and returns that element. */
    public int pop() {
        if (input.empty()) {
            System.out.println("Queue is empty");
            return -1; // Return -1 if queue is empty
        }
        return input.pop(); // Remove and return the front element
    }

    /** Get the front element. */
    public int peek() {
        if (input.empty()) {
            System.out.println("Queue is empty");
            return -1; // Return -1 if queue is empty
        }
        return input.peek(); // Return the front element without removing it
    }

    /** Returns the size of the queue. */
    int size() {
        return input.size(); // Return the number of elements in the queue
    }
}

// Approach - 2 (For optimised push)

import java.util.*;

class MyQueue {
    Stack<Integer> input = new Stack<>();  // Stack used for pushing elements
    Stack<Integer> output = new Stack<>(); // Stack used for popping elements

    /** Initialize your data structure here. */
    public MyQueue() {
        // Constructor initializes the queue
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        System.out.println("The element pushed is " + x);
        input.push(x); // Push directly to the input stack
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (output.empty()) { // If output stack is empty, transfer elements from input stack
            while (!input.empty()) {
                output.push(input.pop());
            }
        }
        if (output.empty()) { // If both stacks are empty, queue is empty
            System.out.println("Queue is empty");
            return -1;
        }
        return output.pop(); // Pop the top of output stack (which is the front of queue)
    }

    /** Get the front element. */
    public int peek() {
        if (output.empty()) { // If output stack is empty, transfer elements from input stack
            while (!input.empty()) {
                output.push(input.pop());
            }
        }
        if (output.empty()) { // If both stacks are empty, queue is empty
            System.out.println("Queue is empty");
            return -1;
        }
        return output.peek(); // Return the front element without removing it
    }

    /** Returns the size of the queue. */
    int size() {
        return output.size() + input.size(); // Total elements in both stacks represent queue size
    }
}


// Comparison 

-> If pushing elements is more frequent, Approach 2 (Lazy Dequeue) is better because push() is always O(1).
-> If popping elements is more frequent, Approach 1 (Eager Enqueue) is better because pop() is always O(1).

-> Use Approach 2 (Lazy Dequeue) in most cases because real-world applications typically push elements more frequently before retrieving them in batches.

-> Use Approach 1 (Eager Enqueue) only if you need frequent pop() calls with minimal delay.
