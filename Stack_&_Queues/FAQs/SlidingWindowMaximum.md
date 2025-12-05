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

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        int n = nums.length;
        
        // Deque to store indices of useful elements
        Deque<Integer> deq = new ArrayDeque<>();
        
        // Result array to store max of each window
        int[] result = new int[n - k + 1];
        int ri = 0; // result index
        
        for (int i = 0; i < n; i++) {
            
            // ---------------- STEP 1 ----------------
            // Remove elements that are OUTSIDE the current window
            // Window range = [i - k + 1, i]
            while (!deq.isEmpty() && deq.peekFirst() <= i - k) {
                deq.pollFirst();
            }
            
            // ---------------- STEP 2 ----------------
            // Remove all smaller elements from the back
            // because they cannot be maximum anymore
            while (!deq.isEmpty() && nums[i] > nums[deq.peekLast()]) {
                deq.pollLast();
            }
            
            // ---------------- STEP 3 ----------------
            // Add current index to the deque
            deq.offerLast(i);
            
            // ---------------- STEP 4 ----------------
            // Start adding maximums to result once first window is complete
            if (i >= k - 1) {
                result[ri++] = nums[deq.peekFirst()];
            }
        }
        
        return result;
    }
}
Time Complexity:O(n), where n is the length of the input array, because each element is visited and processed at most twice (once for adding and once for removing) in the deque.
Space Complexity:O(k), where k is the window size, because the deque stores at most k elements.
```

### Intution of 2nd approach

- We use a deque to store indices of elements in decreasing order of their values.

- Before adding a new element, we remove indices from the front that are outside the current window.

- Then we remove indices from the back whose values are smaller than the current element, as they’ll never be useful again.

- This keeps the deque clean and ordered with only potential maximum candidates.

- We always insert the current index at the back.

- Once the first window is formed (i ≥ k−1), the front of deque gives the maximum.

- Each element is added and removed at most once, making the algorithm efficient.

- Therefore, the total time complexity is O(n) with space O(k).