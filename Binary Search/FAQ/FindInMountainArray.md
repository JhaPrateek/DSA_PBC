### You may recall that an array arr is a mountain array if and only if:arr.length >= 3 There exists some i with 0 < i < arr.length - 1 such that:
```
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
```
You cannot access the mountain array directly. You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

```
Example 1:
Input: mountainArr = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
Example 2:

Input: mountainArr = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.
```

```java
/**
 * // MountainArray API interface
 * // get(index) -> returns element at index
 * // length() -> returns length of array
 */

class Solution {

    public int findInMountainArray(int target, MountainArray mountainArr) {

        // Step 1: Find the peak index of the mountain array
        int peak = findPeak(mountainArr);

        int ans = -1;

        // Step 2: Binary search on ascending part (0 to peak)
        ans = binAsc(mountainArr, target, 0, peak);

        // Step 3: If not found, binary search on descending part
        if (ans == -1) {
            ans = binDsc(mountainArr, target, peak + 1, mountainArr.length() - 1);
        }

        return ans;
    }

    // Binary search on ascending sorted part
    public int binAsc(MountainArray mountainArr, int target, int s, int e) {

        int ans = -1;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            // If target found, store index and move left
            // (to get smallest index if duplicates exist)
            if (mountainArr.get(mid) == target) {
                ans = mid;
                e = mid - 1;
            }
            // If target is smaller, move left
            else if (target < mountainArr.get(mid)) {
                e = mid - 1;
            }
            // If target is larger, move right
            else {
                s = mid + 1;
            }
        }
        return ans;
    }

    // Binary search on descending sorted part
    public int binDsc(MountainArray mountainArr, int target, int s, int e) {

        int ans = -1;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            // If target found, store index and move left
            if (mountainArr.get(mid) == target) {
                ans = mid;
                e = mid - 1;
            }
            // Since array is descending, comparison is reversed
            else if (target < mountainArr.get(mid)) {
                s = mid + 1;
            }
            else {
                e = mid - 1;
            }
        }
        return ans;
    }

    // Find peak index in mountain array
    public int findPeak(MountainArray mountainArr) {

        int n = mountainArr.length();

        int s = 1, e = n - 2;  // peak cannot be at edges

        while (s <= e) {

            int m = s + (e - s) / 2;

            int mid = mountainArr.get(m);
            int left = mountainArr.get(m - 1);
            int right = mountainArr.get(m + 1);

            // If mid is greater than both neighbors, it is the peak
            if (mid > left && mid > right) {
                return m;
            }
            // If slope is increasing, move right
            else if (mid >= left) {
                s = m + 1;
            }
            // If slope is decreasing, move left
            else {
                e = m - 1;
            }
        }

        return -1; // should not occur for valid mountain array
    }
}

```