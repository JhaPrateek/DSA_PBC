### Given an integer array nums, determine the range of a subarray, defined as the difference between the largest and smallest elements within the subarray. Calculate and return the sum of all subarray ranges of nums.



A subarray is defined as a contiguous, non-empty sequence of elements within the array.


Examples:
Input: nums = [1, 2, 3]

Output: 4

Explanation: The 6 subarrays of nums are the following:
```
[1], range = largest - smallest = 1 - 1 = 0 

[2], range = 2 - 2 = 0

[3], range = 3 - 3 = 0

[1,2], range = 2 - 1 = 1

[2,3], range = 3 - 2 = 1

[1,2,3], range = 3 - 1 = 2
```
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.

Input: nums = [1, 3, 3]

Output: 4

Explanation: The 6 subarrays of nums are the following:
```
[1], range = largest - smallest = 1 - 1 = 0

[3], range = 3 - 3 = 0

[3], range = 3 - 3 = 0

[1,3], range = 3 - 1 = 2

[3,3], range = 3 - 3 = 0

[1,3,3], range = 3 - 1 = 2
```
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.

```java
class Solution {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long ans = 0, min, max;

        // We fix a starting index i and explore all subarrays beginning at i
        for (int i = 0; i < n; i++) {
            
            // For each new starting point, reset min and max
            max = Long.MIN_VALUE;   // smallest possible initial max
            min = Long.MAX_VALUE;   // largest possible initial min

            // Extend the subarray from i to j
            for (int j = i; j < n; j++) {

                // Update min and max of the current subarray nums[i..j]
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);

                // For every subarray nums[i..j],
                // contribute (max - min) to answer
                // because the problem asks for sum of (max - min) of all subarrays
                ans += (max - min);
            }
        }

        // Return total of ranges of all subarrays
        return ans;
    }
}
Time Complexity:O(n^2) because of the nested loops iterating through the input array.
Space Complexity:O(1) as it uses a constant amount of extra space, regardless of the input size.
```

```java
import java.util.*;

class Solution {
    
    /* Function to find the indices of 
    next smaller elements */
    private int[] findNSE(int[] arr) {
        
        // Size of array
        int n = arr.length;
        
        // To store the answer
        int[] ans = new int[n];
        
        // Stack 
        Stack<Integer> st = new Stack<>();
        
        // Start traversing from the back
        for (int i = n - 1; i >= 0; i--) {
            
            // Get the current element
            int currEle = arr[i];
            
            /* Pop the elements in the stack until 
            the stack is not empty and the top 
            element is not the smaller element */
            while (!st.isEmpty() && arr[st.peek()] >= currEle) {
                st.pop();
            }
            
            // Update the answer
            ans[i] = !st.isEmpty() ? st.peek() : n;
            
            /* Push the index of current 
            element in the stack */
            st.push(i);
        }
        
        // Return the answer
        return ans;
    }
    
    /* Function to find the indices of 
    next greater elements */
    private int[] findNGE(int[] arr) {
        
        // Size of array
        int n = arr.length;
        
        // To store the answer
        int[] ans = new int[n];
        
        // Stack 
        Stack<Integer> st = new Stack<>();
        
        // Start traversing from the back
        for (int i = n - 1; i >= 0; i--) {
            
            // Get the current element
            int currEle = arr[i];
            
            /* Pop the elements in the stack until 
            the stack is not empty and the top 
            element is not the greater element */
            while (!st.isEmpty() && arr[st.peek()] <= currEle) {
                st.pop();
            }
            
            // Update the answer
            ans[i] = !st.isEmpty() ? st.peek() : n;
            
            /* Push the index of current 
            element in the stack */
            st.push(i);
        }
        
        // Return the answer
        return ans;
    }
    
    /* Function to find the indices of 
    previous smaller or equal elements */
    private int[] findPSEE(int[] arr) {
        
        // Size of array
        int n = arr.length;
        
        // To store the answer
        int[] ans = new int[n];
        
        // Stack 
        Stack<Integer> st = new Stack<>();
        
        // Traverse on the array
        for (int i = 0; i < n; i++) {
            
            // Get the current element
            int currEle = arr[i];
            
            /* Pop the elements in the stack until 
            the stack is not empty and the top 
            elements are greater than the current element */
            while (!st.isEmpty() && arr[st.peek()] > currEle) {
                st.pop();
            }
            
            // Update the answer
            ans[i] = !st.isEmpty() ? st.peek() : -1;
            
            /* Push the index of current 
            element in the stack */
            st.push(i);
        }
        
        // Return the answer
        return ans;
    }
    
    /* Function to find the indices of 
    previous greater or equal elements */
    private int[] findPGEE(int[] arr) {
        
        // Size of array
        int n = arr.length;
        
        // To store the answer
        int[] ans = new int[n];
        
        // Stack 
        Stack<Integer> st = new Stack<>();
        
        // Traverse on the array
        for (int i = 0; i < n; i++) {
            
            // Get the current element
            int currEle = arr[i];
            
            /* Pop the elements in the stack until 
            the stack is not empty and the top 
            elements are smaller than the current element */
            while (!st.isEmpty() && arr[st.peek()] < currEle) {
                st.pop();
            }
            
            // Update the answer
            ans[i] = !st.isEmpty() ? st.peek() : -1;
            
            /* Push the index of current 
            element in the stack */
            st.push(i);
        }
        
        // Return the answer
        return ans;
    }
    
    /* Function to find the sum of the 
    minimum value in each subarray */
    private long sumSubarrayMins(int[] arr) {
        
        int[] nse = findNSE(arr);
        
        int[] psee = findPSEE(arr);
        
        // Size of array
        int n = arr.length;
        
        // To store the sum
        long sum = 0;
        
        // Traverse on the array
        for (int i = 0; i < n; i++) {
            
            // Count of first type of subarrays
            int left = i - psee[i];
            
            // Count of second type of subarrays
            int right = nse[i] - i;
            
            /* Count of subarrays where 
            current element is minimum */
            long freq = left * right * 1L;
            
            // Contribution due to current element 
            long val = (freq * arr[i] * 1L);
            
            // Updating the sum
            sum += val;
        }
        
        // Return the computed sum
        return sum;
    }
    
    /* Function to find the sum of the 
    maximum value in each subarray */
    private long sumSubarrayMaxs(int[] arr) {
        
        int[] nge = findNGE(arr);
        
        int[] pgee = findPGEE(arr);
        
        // Size of array
        int n = arr.length;
        
        // To store the sum
        long sum = 0;
        
        // Traverse on the array
        for (int i = 0; i < n; i++) {
            
            // Count of first type of subarrays
            int left = i - pgee[i];
            
            // Count of second type of subarrays
            int right = nge[i] - i;
            
            /* Count of subarrays where 
            current element is maximum */
            long freq = left * right * 1L;
            
            // Contribution due to current element 
            long val = (freq * arr[i] * 1L);
            
            // Updating the sum
            sum += val;
        }
        
        // Return the computed sum
        return sum;
    }
    
    /* Function to find the sum of 
    subarray ranges in each subarray */
    public long subArrayRanges(int[] arr) {
        
        // Return the result
        return ( sumSubarrayMaxs(arr) - 
                 sumSubarrayMins(arr) );
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        
        /* Creating an instance of 
        Solution class */
        Solution sol = new Solution(); 
        
        /* Function call to find the sum of 
        subarray ranges in each subarray */
        long ans = sol.subArrayRanges(arr);
        
        System.out.println("The sum of subarray ranges is: " + ans);
    }
}
Time Complexity:O(n) due to loops and stack operations that process each element at most a constant number of times.
Space Complexity:O(n) due to the stacks and result arrays used in helper functions.
```