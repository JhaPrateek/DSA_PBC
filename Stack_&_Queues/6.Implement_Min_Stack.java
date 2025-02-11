Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
Implement the MinStack class:
MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

Example 1:
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]


class MinStack {

    // Internal class to store each value along with the minimum at that point
    private class Pair {
        int val;  // Value of the element
        int min;  // Minimum value in the stack at this point

        // Constructor to initialize the pair
        Pair(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    // Stack to store Pair objects
    Stack<Pair> st;

    // Constructor to initialize the stack
    public MinStack() {
        st = new Stack<>();
    }
    
    // Push operation
    public void push(int val) {
        if (st.isEmpty()) {
            // If stack is empty, the min value is the element itself
            st.push(new Pair(val, val));
        } else {
            // Get the minimum so far and store the new min value
            int min = Math.min(val, st.peek().min);
            st.push(new Pair(val, min));
        }
    }
    
    // Pop operation
    public void pop() {
        if (!st.isEmpty()) {
            // Remove the top element from the stack
            st.pop();
        }
    }
    
    // Returns the top element of the stack
    public int top() {
        return st.peek().val;
    }
    
    // Returns the minimum element in the stack
    public int getMin() {
        return st.peek().min;
    }
}
