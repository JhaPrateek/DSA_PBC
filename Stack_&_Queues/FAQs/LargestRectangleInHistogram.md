### Given an array of integers heights representing the histogram's bar height where the width of each bar is 1 return the area of the largest rectangle in the histogram.

```
Index:   0   1   2   3   4   5
Height:  2   1   5   6   2   3

                █
                █
            █   █
            █   █
    █       █   █       █
    █   █   █   █   █   █
    --------------------------------
       2   1   5   6   2   3
```

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        
       int ans = 0;                   // Stores the maximum rectangle area found so far
       int n = heights.length;        // Total number of bars in the histogram

       // Outer loop fixes the starting index of the rectangle
       for(int i = 0; i < n; i++) {
            
            int min = heights[i];     // Minimum height in the current window (i to j)

            // Inner loop expands the rectangle to the right
            for(int j = i; j < n; j++) {
                
                // Update minimum height between index i and j
                min = Math.min(min, heights[j]);

                // Width = (j - i + 1)
                // Height = min
                // Area = width × height
                int area = (j - i + 1) * min;

                // Update maximum area if current area is larger
                ans = Math.max(ans, area);
            }
       }

       // Return the largest rectangle area found in the histogram
       return ans;
    }
}
Time Complexity:The time complexity is O(n^2) because of the nested loops.
Space Complexity:The space complexity is O(1) because it uses a constant amount of extra space.
```

```java
class Solution {
    public int largestRectangleArea(int[] heights) {

       int n = heights.length;                 // Total number of bars
       
       // Find previous smaller element index for each bar
       int prev[] = prevSmaller(heights);

       // Find next smaller element index for each bar
       int next[] = nextSmaller(heights);

       int ans = 0;                            // Stores maximum area

       // Calculate area for each bar considering it as the smallest height
       for(int i = 0; i < n; i++) {

            // Width = next[i] - prev[i] - 1
            // Height = heights[i]
            int area = heights[i] * (next[i] - prev[i] - 1);

            // Update maximum area
            ans = Math.max(ans, area);
       }

       return ans;                             // Return largest rectangle area
    }

    // Finds index of NEXT smaller element to the RIGHT for each element
    public int[] nextSmaller(int nums[]) {

        int n = nums.length;
        int arr[] = new int[n];                // Stores next smaller indices
        Stack<Integer> st = new Stack<>();     // Stack stores indices

        // Traverse from RIGHT to LEFT
        for(int i = n - 1; i >= 0; i--) {

            // Remove all elements greater than or equal to current element
            while(!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }

            // If stack empty → no smaller element on right → use n
            // Else → top of stack is next smaller element
            arr[i] = st.isEmpty() ? n : st.peek();

            // Push current index into stack
            st.push(i);
        }

        return arr;
    }

    // Finds index of PREVIOUS smaller element to the LEFT for each element
    public int[] prevSmaller(int nums[]) {

        int n = nums.length;
        int arr[] = new int[n];                // Stores previous smaller indices
        Stack<Integer> st = new Stack<>();     // Stack stores indices

        // Traverse from LEFT to RIGHT
        for(int i = 0; i < n; i++) {

            // Remove all elements greater than or equal to current element
            while(!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }

            // If stack empty → no smaller element on left → use -1
            // Else → top of stack is previous smaller element
            arr[i] = st.isEmpty() ? -1 : st.peek();

            // Push current index into stack
            st.push(i);
        }

        return arr;
    }
}
Time Complexity:O(n), where n is the number of bars in the histogram, due to three loops each iterating through the input array of size n.
Space Complexity:O(n), where n is the number of bars in the histogram, due to the auxiliary arrays prev, next, and the stack which in the worst case could store all indices.
```