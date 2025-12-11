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

```