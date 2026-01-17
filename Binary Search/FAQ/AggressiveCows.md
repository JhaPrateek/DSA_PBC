### Given an array nums of size n, which denotes the positions of stalls, and an integer k, which denotes the number of aggressive cows, assign stalls to k cows such that the minimum distance between any two cows is the maximum possible. Find the maximum possible minimum distance.
```
Example 1
Input: n = 6, k = 4, nums = [0, 3, 4, 7, 10, 9]
Output: 3
Explanation:
The maximum possible minimum distance between any two cows will be 3 when 4 cows are placed at positions [0, 3, 7, 10]. Here the distances between cows are 3, 4, and 3 respectively.
In no manner can we increase the minimum distance beyond 3.

Example 2
Input : n = 5, k = 2, nums = [4, 2, 1, 3, 6]
Output: 5
Explanation: The maximum possible minimum distance between any two cows will be 5 when 2 cows are placed at positions [1, 6]. 
```
```java
class Solution {

    public int aggressiveCows(int[] stalls, int k) {

        // Sort stall positions to place cows in increasing order
        Arrays.sort(stalls);

        // Minimum possible distance between two cows
        int low = 1;

        // Maximum possible distance:
        // placing cows at first and last stall
        int high = stalls[stalls.length - 1] - stalls[0];

        int ans = 0;

        // Binary search on the minimum distance between cows
        while (low <= high) {

            // Try a candidate minimum distance
            int mid = low + (high - low) / 2;

            // Check if cows can be placed with at least mid distance
            if (canPlaceCows(stalls, k, mid)) {
                ans = mid;        // mid is a valid minimum distance
                low = mid + 1;    // try to maximize the distance
            } else {
                high = mid - 1;   // reduce distance if not possible
            }
        }

        return ans; // largest minimum distance
    }

    // Greedy check to see if we can place k cows
    // such that each cow is at least 'dist' apart
    private boolean canPlaceCows(int[] stalls, int k, int dist) {

        int cowsPlaced = 1;        // place first cow at first stall
        int lastPos = stalls[0];   // position of last placed cow

        // Try placing remaining cows in the next stalls
        for (int i = 1; i < stalls.length; i++) {

            // If current stall is far enough from last cow
            if (stalls[i] - lastPos >= dist) {
                cowsPlaced++;          // place a cow
                lastPos = stalls[i];   // update last placed position
            }

            // If all cows are placed successfully
            if (cowsPlaced == k) {
                return true;           // placement possible
            }
        }

        // Not enough stalls to place all cows with given distance
        return false;
    }
}
Time Complexity
O(n log n + n log D) where n is the number of stalls and D is the range of stall coordinates (stalls[max] - stalls[min]). The sorting step takes O(n log n), and the binary search performs O(log D) iterations, each calling the canPlaceCows method which takes O(n) time.
Space Complexity
O(1) because the algorithm uses a constant amount of extra space for variables like low, high, and mid, and performs the sorting in-place (excluding the internal recursion stack used by the sorting algorithm).
```