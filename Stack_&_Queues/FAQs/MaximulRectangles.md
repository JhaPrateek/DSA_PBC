### Given a m x n binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.


Examples:
Input: matrix = [[1, 0, 1, 0, 0], [1, 0, 1, 1, 1], [1, 1, 1, 1, 1], [1, 0, 0, 1, 0]]

```
matrix = [
  [1, 0, 1, 0, 0],
  [1, 0, 1, 1, 1],
  [1, 1, 1, 1, 1],
  [1, 0, 0, 1, 0]
]

```

Output: 6



```java
class Solution {

    // ✅ Main function to find the largest rectangle of 1s in a binary matrix
    public int maximalAreaOfSubMatrixOfAll1(int[][] matrix) {

       int m = matrix.length;         // Number of rows
       int n = matrix[0].length;      // Number of columns

       // ✅ Prefix array where each row is treated like a histogram
       int prefix[][] = new int[m][n];

       // ✅ Build the histogram row by row
       for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                // If current cell is 1 and NOT in first row,
                // add height from above (build vertical histogram)
                if(i != 0 && matrix[i][j] == 1){
                    prefix[i][j] = prefix[i - 1][j] + matrix[i][j];
                } 
                // Otherwise directly copy the value (0 or 1)
                else{
                    prefix[i][j] = matrix[i][j];
                }
            }
       }

       int ans = 0;   // Stores maximum rectangle area

       // ✅ Treat each row of prefix matrix as a histogram
       for(int i = 0; i < m; i++){
            ans = Math.max(ans, largestRectangleArea(prefix[i]));
       }

       return ans;    // ✅ Final maximal rectangle of 1s
    }

    // ✅ Standard Largest Rectangle in Histogram (O(n))
    public int largestRectangleArea(int[] heights) {

       int n = heights.length;

       // Previous smaller element index for each bar
       int prev[] = prevSmaller(heights);

       // Next smaller element index for each bar
       int next[] = nextSmaller(heights);

       int ans = 0;

       // ✅ Compute max area for each bar as minimum height
       for(int i = 0; i < n; i++){

            // Width = next[i] - prev[i] - 1
            // Height = heights[i]
            int area = heights[i] * (next[i] - prev[i] - 1);

            ans = Math.max(ans, area);
       }

       return ans;
    }

    // ✅ Finds NEXT smaller element index for each element (Right Side)
    public int[] nextSmaller(int nums[]){

        int n = nums.length;
        int arr[] = new int[n];               // Stores next smaller index
        Stack<Integer> st = new Stack<>();    // Stack stores indices

        // Traverse from right to left
        for(int i = n - 1; i >= 0; i--){

            // Pop all elements greater than or equal to current
            while(!st.isEmpty() && nums[st.peek()] >= nums[i]){
                st.pop();
            }

            // If stack empty → No smaller to right → store n
            // Else → Top of stack is next smaller element
            arr[i] = st.isEmpty() ? n : st.peek();

            // Push current index into stack
            st.push(i);
        }

        return arr;
    }

    // ✅ Finds PREVIOUS smaller element index for each element (Left Side)
    public int[] prevSmaller(int nums[]){

        int n = nums.length;
        int arr[] = new int[n];               // Stores previous smaller index
        Stack<Integer> st = new Stack<>();    // Stack stores indices

        // Traverse from left to right
        for(int i = 0; i < n; i++){

            // Pop all elements greater than or equal to current
            while(!st.isEmpty() && nums[st.peek()] >= nums[i]){
                st.pop();
            }

            // If stack empty → No smaller to left → store -1
            // Else → Top of stack is previous smaller element
            arr[i] = st.isEmpty() ? -1 : st.peek();

            // Push current index into stack
            st.push(i);
        }

        return arr;
    }
}
Time Complexity:O(m*n) + O(m*n) which simplifies to O(m*n) where m is number of rows, and n is number of columns, it takes O(m*n) to compute prefix array, and another O(m*n) to find the largest rectangular area.
Space Complexity:O(n) due to the auxiliary arrays used in largestRectangleArea function. The prefix array uses O(m*n) space but the auxillary space will be O(n).
```

```
✅ Core Intuition 

Each row is converted into a histogram using prefix height count of 1s.

For every row, apply Largest Rectangle in Histogram algorithm.

For each bar, find previous smaller & next smaller elements using stack.

This gives the max width where the bar is the minimum height.

Area = height × width.

Track the maximum area across all rows.

Final answer is the largest submatrix of all 1s.
```