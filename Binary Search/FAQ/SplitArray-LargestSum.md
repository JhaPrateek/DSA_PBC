## Similar to Book Allocation
### Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized. Return the minimized largest sum of the split. A subarray is a contiguous part of the array.

 
```
Example 1:

Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
```

```java
class Solution {

    public int splitArray(int[] nums, int k) {
        int n = nums.length;

        // If students are more than books, allocation is impossible
        if (n < k) {
            return -1;
        }

        // Minimum possible maximum sum:
        // at least one subarray must contain the largest element
        int left = Arrays.stream(nums).max().getAsInt();

        // Maximum possible maximum sum:
        // one subarray contains all elements
        int right = Arrays.stream(nums).sum();

        int ans = -1;

        // Binary search on the answer (maximum subarray sum)
        while (left <= right) {

            int mid = left + (right - left) / 2; // candidate maximum sum

            // Check if we can split array into k subarrays
            // such that no subarray sum exceeds mid
            if (isValid(nums, n, k, mid)) {
                ans = mid;        // mid is a possible answer
                right = mid - 1;  // try to minimize the maximum sum
            } else {
                left = mid + 1;   // need a larger maximum sum
            }
        }

        return ans;
    }

    public boolean isValid(int[] nums, int n, int m, int max) {

        int student = 1;  // start with first subarray
        int sum = 0;      // sum of current subarray

        for (int i = 0; i < n; i++) {

            sum += nums[i];  // add current element to subarray

            // If sum exceeds allowed maximum,
            // start a new subarray
            if (sum > max) {
                student++;        // move to next subarray
                sum = nums[i];    // start sum with current element
            }
        }

        // If more than m subarrays are needed,
        // the split is not possible
        if (student > m) {
            return false;
        }

        return true;  // valid split within max limit
    }
}
Time Complexity
O(n * log(sum - max)) where n is the length of the array, sum is the sum of all elements, and max is the maximum element in the array. Finding the initial max and sum takes O(n) time, and the binary search performs log(sum - max) iterations, with each iteration calling the isValid function which takes O(n) time.
Space Complexity
O(1) as the algorithm only uses a constant amount of extra space for variables (n, left, right, ans, mid, student, sum) regardless of the input size.
```