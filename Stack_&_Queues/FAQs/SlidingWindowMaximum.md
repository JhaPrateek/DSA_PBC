### Given an array of integers arr, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.


Examples:
Input: arr = [4, 0, -1, 3, 5, 3, 6, 8], k = 3

Output: [4, 3, 5, 5, 6, 8]

```java
class Solution {
    public int[] maxSlidingWindow(int[] arr, int k) {
        
        int n = arr.length;  
        // n = total number of elements in the array
        
        int ans[] = new int[n - k + 1];  
        // Result array will store maximum of each window
        // Total windows = n - k + 1
        
        // Outer loop controls the starting index of each window
        for (int i = 0; i <= n - k; i++) {
            
            int max = arr[i];  
            // Assume first element of current window is the maximum
            
            // Inner loop scans k elements of current window
            for (int j = i; j < i + k; j++) {
                max = Math.max(arr[j], max);  
                // Update max if a bigger value is found
            }
            
            ans[i] = max;  
            // Store max of current window in result
        }
        
        return ans;  
        // Return the final array of window maximums
    }
}
Time Complexity:O(n*k), due to nested loops iterating through the array.
Space Complexity:O(n-k+1), primarily due to the size of the `ans` array.
```