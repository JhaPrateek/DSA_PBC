## Next smaller element

```java
public int[] NSE(int nums[], int n) {
    int arr[] = new int[n];       // This will store index of Next Smaller Element for each position
    Stack<Integer> st = new Stack<>();   // Monotonic stack to maintain indices

    // Traverse from right to left because we want "next" element on the right
    for (int i = n - 1; i >= 0; i--) {

        // Remove all elements from stack that are NOT smaller than current element
        // Since nums[st.peek()] >= nums[i], they cannot be the next smaller element.
        while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
            st.pop();
        }

        // After popping:
        // If stack becomes empty → no smaller element exists on the right
        // Else → top of stack contains index of the next smaller element
        arr[i] = st.isEmpty() ? n : st.peek();

        // Push current index into stack so it can act as NSE candidate for elements to the left
        st.push(i);
    }

    // Return the array containing the index of the next smaller element for each index
    return arr;
}

```

## Previous smaller element

```java
public int[] PSE(int nums[], int n) {

    // This array will store the index of Previous Smaller Element for each position
    int arr[] = new int[n];

    // Stack will store indices of elements in a monotonic (increasing) manner
    Stack<Integer> st = new Stack<>();

    // Traverse from left to right because we need "previous" element
    for (int i = 0; i < n; i++) {

        // Pop all elements from stack that are >= current element
        // Because they cannot be "smaller" candidates
        while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
            st.pop();
        }

        // After popping:
        // If stack is empty → no smaller element exists on the left
        // Else → top of stack is index of the previous smaller element
        arr[i] = st.isEmpty() ? -1 : st.peek();

        // Push current index into stack for future comparisons
        st.push(i);
    }

    // Return array containing previous smaller element indices
    return arr;
}

```