### Given two sorted arrays arr1 and arr2 of size m and n respectively, return the median of the two sorted arrays. The median is defined as the middle value of a sorted list of numbers. In case the length of the list is even, the median is the average of the two middle elements.

```
Example 1
Input: arr1 = [2, 4, 6], arr2 = [1, 3, 5]
Output: 3.5
Explanation: The array after merging arr1 and arr2 will be [ 1, 2, 3, 4, 5, 6 ]. As the length of the merged list is even, the median is the average of the two middle elements. Here two medians are 3 and 4. So the median will be the average of 3 and 4, which is 3.5.

Example 2
Input: arr1 = [2, 4, 6], arr2 = [1, 3]
Output: 3.0
Explanation: The array after merging arr1 and arr2 will be [ 1, 2, 3, 4, 6 ]. The median is simply 3.
```
```java
class Solution {

    public double median(int[] arr1, int[] arr2) {

        int m = arr1.length;
        int n = arr2.length;

        // Always apply binary search on the smaller array
        if (m > n) return median(arr2, arr1);

        // Number of elements required on the left side of partition
        int left = (m + n + 1) / 2;

        int s = 0, e = m;

        // Binary search on arr1
        while (s <= e) {

            int mid1 = (s + e) / 2;     // partition index in arr1
            int mid2 = left - mid1;     // partition index in arr2

            // Left elements of partition
            int l1 = mid1 > 0 ? arr1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = mid2 > 0 ? arr2[mid2 - 1] : Integer.MIN_VALUE;

            // Right elements of partition
            int r1 = mid1 < m ? arr1[mid1] : Integer.MAX_VALUE;
            int r2 = mid2 < n ? arr2[mid2] : Integer.MAX_VALUE;

            // Check if correct partition is found
            if (l1 <= r2 && l2 <= r1) {

                // If total elements are odd,
                // median is the maximum element on the left side
                if ((m + n) % 2 == 1) {
                    return Math.max(l1, l2);
                }

                // If total elements are even,
                // median is average of max(left) and min(right)
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;

            }
            // If left side of arr1 is too large, move partition left
            else if (l1 > r2) {
                e = mid1 - 1;
            }
            // If left side of arr1 is too small, move partition right
            else {
                s = mid1 + 1;
            }
        }

        return 0; // should never reach here for valid input
    }
}
Time Complexity
O(log(min(m, n))) because the binary search is performed on the smaller of the two arrays to find the partition point.
Space Complexity
O(1) as the algorithm only uses a constant amount of extra space for variables regardless of input size.
```