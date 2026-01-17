### Given a circular integer array arr, return the next greater element for every element in arr. The next greater element for an element x is the first element greater than x that we come across while traversing the array in a clockwise manner.
If it doesn't exist, return -1 for that element.
```
Examples:
Input: arr = [3, 10, 4, 2, 1, 2, 6, 1, 7, 2, 9]
Output: [10, -1, 6, 6, 2, 6, 7, 7, 9, 9, 10]

Explanation:
For the first element in arr i.e, 3, the greater element which comes next to it while traversing and is closest to it is 10. Hence,10 is present on index 0 in the resultant array. Now for the second element i.e, 10, there is no greater number and hence -1 is it’s next greater element (NGE). Similarly, we got the NGEs for all other elements present in arr.  

Input: arr = [5, 7, 1, 7, 6, 0]
Output: [7, -1, 7, -1, 7, 5]

Explanation
For the first element in arr i.e, 5, the greater element which comes next to it while traversing and is closest to it is 7. Now for the second element i.e, 7, there is no greater number and hence -1 is it’s next greater element (NGE). Similarly, we got the NGEs for all other elements present in arr.
```
```java
import java.util.*;

class Solution {

    /* Function to find the next greater element
    for each element in the circular array */
    public int[] nextGreaterElements(int[] arr) {
       
        int n = arr.length; // size of array
        
        // To store the next greater elements
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
       
        for(int i = 0; i < n; i++) {
           
            // Get the current element
            int currEle = arr[i];
           
            /* Nested loop to get the 
            next greater element */
            for(int j = 1; j < n; j++) {
               
                // Getting the hypothetical index
                int ind = (j + i) % n;
               
                // If the next greater element is found
                if(arr[ind] > currEle) {
                   
                    // Store the next greater element
                    ans[i] = arr[ind];
                   
                    // Break from the loop
                    break;
                }    
            }
        }
       
        // Return the answer
        return ans;
    }
}
Time Complexity: O(N2) (where N is the size of given array)
Using two nested for loops to find the next greater elements.

Space Complexity: O(N) The space required to store the answer is O(N).    
```

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {

        int n = nums.length;

        // ans[i] will store the next greater element for nums[i]
        int ans[] = new int[n];

        // Stack to store potential "next greater" elements
        // We store values (not indices) because we only need the next greater value
        Stack<Integer> st = new Stack<>();

        /*
            We traverse from (2*n - 1) to 0 to simulate a circular array.

            Why 2*n?
            - Because array is circular
            - Second pass allows elements at the beginning to find
              next greater elements at the end
        */
        for (int i = 2 * n - 1; i >= 0; i--) {

            /*
                Remove all elements from stack that are
                smaller than or equal to current element.

                Why?
                - They can NEVER be the next greater element
                - Because current element blocks them
            */
            while (!st.isEmpty() && st.peek() <= nums[i % n]) {
                st.pop();
            }

            /*
                We fill answers ONLY during the first pass (i < n)

                If stack is not empty:
                - Top of stack is the next greater element
                Else:
                - No greater element exists → -1
            */
            if (i < n) {
                ans[i] = !st.isEmpty() ? st.peek() : -1;
            }

            /*
                Push current element into stack
                It may act as the next greater element for
                elements on the left
            */
            st.push(nums[i % n]);
        }

        return ans;
    }
}

Time Complexity:The time complexity is O(n) because each element is pushed and popped from the stack at most once, and the outer loop iterates 2*n times.
Space Complexity:The space complexity is O(n) because the stack can store up to n indices and the result array 'ans' also takes O(n) space.    
```
