### Given an array nums of n integers, where nums[i] represents the number of pages in the i-th book, and an integer m representing the number of students, allocate all the books to the students so that each student gets at least one book, each book is allocated to only one student, and the allocation is contiguous. Allocate the books to m students in such a way that the maximum number of pages assigned to a student is minimized. If the allocation of books is not possible, return -1.

Example 1
Input: nums = [12, 34, 67, 90], m=2
Output: 113
Explanation: The allocation of books will be 12, 34, 67 | 90. One student will get the first 3 books and the other will get the last one.

Example 2
Input: nums = [25, 46, 28, 49, 24], m=4
Output: 71
Explanation: The allocation of books will be 25, 46 | 28 | 49 | 24.
```

```java
class Solution {

    public int findPages(int[] nums, int m) {
        int n = nums.length;

        // If students are more than books, allocation is not possible
        if (n < m) {
            return -1;
        }

        // Minimum possible maximum pages:
        // one student must read the largest book
        int left = Arrays.stream(nums).max().getAsInt();

        // Maximum possible maximum pages:
        // one student reads all books
        int right = Arrays.stream(nums).sum();

        int ans = -1;

        // Binary search on answer (maximum pages per student)
        while (left <= right) {

            int mid = (left + right) / 2; // candidate max pages

            // Check if allocation is possible with mid as limit
            if (isValid(nums, n, m, mid)) {
                ans = mid;        // mid is a valid answer
                right = mid - 1;  // try to minimize max pages
            } else {
                left = mid + 1;   // increase limit if not possible
            }
        }

        return ans;
    }

    public boolean isValid(int nums[], int n, int m, int max) {

        int student = 1; // start with first student
        int sum = 0;     // pages allocated to current student

        for (int i = 0; i < n; i++) {

            sum += nums[i]; // assign book to current student

            // If pages exceed allowed limit,
            // allocate book to next student
            if (sum > max) {
                student++;     // move to next student
                sum = nums[i]; // start count with current book
            }
        }

        // If students needed exceed available students
        if (student > m) {
            return false; // allocation not possible
        }

        return true; // allocation possible
    }
}
Time Complexity
O(n * log(sum - max)) where n is the length of the array, sum is the sum of all elements, and max is the maximum element. The initial stream operations to find max and sum take O(n). The binary search runs log(sum - max) times, and inside each iteration, the isValid function iterates through the array in O(n) time.
Space Complexity
O(1) because the algorithm only uses a constant amount of extra space for primitive variables (n, left, right, ans, mid, student, sum) regardless of the input size.
```