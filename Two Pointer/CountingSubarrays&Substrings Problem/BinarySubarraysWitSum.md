### Given a binary array nums and an integer goal. Return the number of non-empty subarrays with a sum goal.
A subarray is a continuous part of the array.

Examples:
Input : nums = [1, 1, 0, 1, 0, 0, 1] , goal = 3
Output : 4
Explanation : The subarray with sum 3 are
```
[1, 1, 0, 1]
[1, 1, 0, 1, 0]
[1, 1, 0, 1, 0, 0]
[1, 0, 1, 0, 0, 1].
```

Input : nums = [0, 0, 0, 0, 1] , goal = 0
Output : 10
Explanation : Some of the subarray with sum 0 are
```
[0]
[0, 0]
[0, 0, 0]
[0, 0, 0, 0]
```

```java
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {

        int n = nums.length;
        int cnt = 0; // will store total number of valid subarrays

        // Try every possible starting index i
        for (int i = 0; i < n; i++) {

            int sum = 0; // sum of current subarray starting from index i

            // Expand subarray from index i to j
            for (int j = i; j < n; j++) {

                sum += nums[j]; // add current element to subarray sum

                // If sum equals required goal → valid subarray found
                if (sum == goal) {
                    cnt++;  // count this subarray
                }
            }
        }

        return cnt;  // return total number of subarrays with sum = goal
    }
}
Time Complexity:The time complexity is O(n^2) because of the nested loops, where 'n' is the length of the input array.
Space Complexity:The space complexity is O(1) as it uses a constant amount of extra space, irrespective of the input size.
```

```java

```