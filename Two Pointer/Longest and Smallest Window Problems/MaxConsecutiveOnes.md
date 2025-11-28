### Given a binary array nums and an integer k, flip at most k 0's. Return the maximum number of consecutive 1's after performing the flipping operation.


Examples:
Input : nums = [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0] , k = 3
Output : 10
Explanation : The maximum number of consecutive 1's are obtained only if we flip the 0's present at position 3, 4, 5 (0 base indexing).
The array after flipping becomes [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0].
The number of consecutive 1's is 10.

Input : nums = [0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1] , k = 3
Output : 9
Explanation : The underlines 1's are obtained by flipping 0's in the new array.
[1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1].
The number of consecutive 1's is 9.

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int ans = 0;              // To store the maximum length of subarray found
        int n = nums.length;      // Length of the input array

        // Outer loop -> Choose the starting index of subarray
        for (int i = 0; i < n; i++) {
            int k1 = k;          // Copy of k because we modify it for each i
            int cnt = 0;         // Count of valid subarray length starting from i

            // Inner loop -> Check all subarrays starting from index i
            for (int j = i; j < n; j++) {

                // If current element is 0, we need to flip it to 1
                if (nums[j] == 0) {
                    // If no flips(k1) are left, we must stop
                    if (k1 == 0) {
                        break;
                    } else {
                        k1--;    // Use one flip for this 0
                        cnt++;   // Increase subarray length
                    }
                } 
                // If nums[j] is 1, just increase subarray length
                else {
                    cnt++;
                }

                // Update the answer with maximum subarray length seen so far
                ans = Math.max(ans, cnt);
            }
        }
        // Return the maximum length of subarray with at most k flips allowed
        return ans;
    }
}
Time Complexity:O(N2), where N is the size of the array. As for every element of the array the inner loop runs for N times.
Space Complexity: O(1) as no extra space is being used.
```

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = 0;    // Sliding window pointers
        int ans = 0;         // Stores the max window size found
        int zeros = 0;       // Count of zeros in the current window

        while (r < n) {      // Expand the window by moving 'r' to the right
            if (nums[r] == 0) {
                zeros++;     // Count zeros (flip needed)
            }

            // If zeros > k → too many flips required → shrink window from left
            while (zeros > k) {
                if (nums[l] == 0) {
                    zeros--; // Restore zero count when moving left pointer
                }
                l++;         // Shrink window
            }

            // Update answer with the maximum window size found till now
            ans = Math.max(ans, r - l + 1);

            r++;  // Expand window further
        }

        return ans;  // Final answer
    }
}
Time Complexity:O(2N), where N is the size of the array. The outer and the inner loop is running for N times each.
Space Complexity: O(1) as no extra space is being used.
```

```java

```