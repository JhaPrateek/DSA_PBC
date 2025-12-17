### Given an array of integers nums, each element in the array represents the maximum jump length at that position. Initially starting at the first index of the array, determine if it is possible to reach the last index. Return true if the last index can be reached, otherwise return false.

```
Example 1
Input : [2, 3, 1, 1, 4]
Output : true

Explanation : We can simply take Jump of 1 step at each index to reach the last index.

Example 2
Input : [3, 2, 1, 0, 4]
Output : false

Explanation : No matter how you make jumps you will always reach the third index (0 base) of the array.
The maximum jump of index three is 0, So you can never reach the last index of array.
```

```java
class Solution {
    public boolean canJump(int[] nums) {

        int n = nums.length;

        // maxInd keeps track of the farthest index
        // that can be reached so far
        int maxInd = 0;

        // Traverse the array index by index
        for (int i = 0; i < n; i++) {

            // If the current index is greater than the farthest
            // reachable index, it means we are stuck
            // and cannot move forward
            if (i > maxInd) {
                return false;
            }

            // Update the farthest index we can reach
            // from the current position
            maxInd = Math.max(maxInd, i + nums[i]);
        }

        // If we finish the loop, it means
        // the last index is reachable
        return true;
    }
}
Time Complexity
O(n) because the code iterates through the input array nums once.
Space Complexity
O(1) because the code uses a constant amount of extra space, regardless of the input size.
```

```
- Here is a crisp 10-line intuition, perfect for interviews and quick revision 👇
- You start at index 0 and want to reach the last index.
- Each element tells the maximum distance you can jump from that index.
- Instead of trying all jumps, track the farthest index reachable so far.
- Initialize maxReach = 0 to represent current jump power.
- Move through the array index by index.
- If at any index i, i > maxReach, you are stuck and cannot proceed.
- If reachable, update maxReach = max(maxReach, i + nums[i]).
- This greedy choice always keeps the best possible reach.
- You don’t care how you reach an index, only whether you can.
- If the loop ends without getting stuck, the last index is reachable.
```