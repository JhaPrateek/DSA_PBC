## Stack implementation


## Queue implementation

class Queue {

    private int arr[];
    private int start, end, currSize, maxSize;

    // Default constructor initializes a queue with a fixed size of 16
    public Queue() {
        arr = new int[16]; 
        start = -1;  // Indicates the queue is empty
        end = -1;    // Indicates the queue is empty
        currSize = 0; 
        maxSize = 16; // Default max size
    }

    // Parameterized constructor to initialize queue with given max size
    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        start = -1; // Queue is initially empty
        end = -1;   // Queue is initially empty
        currSize = 0;
    }

    // Push (enqueue) operation: Adds an element to the queue
    public void push(int newElement) {
        if (currSize == maxSize) {  // Check if the queue is full
            System.out.println("Queue is full\nExiting...");
            System.exit(1);
        }

        if (end == -1) {  // If queue is empty, initialize start and end
            start = 0;
            end = 0;
        } else {
            end = (end + 1) % maxSize; // Move end circularly
        }

        arr[end] = newElement; // Insert element at end position
        System.out.println("The element pushed is " + newElement);
        currSize++; // Increase current size
    }

    // Pop (dequeue) operation: Removes an element from the queue
    public int pop() {
        if (start == -1) {  // Check if the queue is empty
            System.out.println("Queue Empty\nExiting...");
            System.exit(1);
        }

        int popped = arr[start]; // Store the element to be removed

        if (currSize == 1) {  // If only one element was present, reset queue
            start = -1;
            end = -1;
        } else {
            start = (start + 1) % maxSize; // Move start circularly
        }

        currSize--; // Decrease current size
        return popped; // Return the removed element
    }

    // Returns the front element without removing it
    public int top() {
        if (start == -1) { // Check if the queue is empty
            System.out.println("Queue is Empty");
            System.exit(1);
        }
        return arr[start]; // Return the front element
    }

    // Returns the current size of the queue
    public int size() {
        return currSize;
    }
}
