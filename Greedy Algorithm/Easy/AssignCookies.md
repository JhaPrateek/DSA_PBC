### Consider a scenario where a teacher wants to distribute cookies to students, with each student receiving at most one cookie. Given two arrays, Student and Cookie, the ith value in the Student array describes the minimum size of cookie that the ith student can be assigned. The jth value in the Cookie array represents the size of the jth cookie. If Cookie[j] >= Student[i], the jth cookie can be assigned to the ith student. Maximize the number of students assigned with cookies and output the maximum number.

```
Example 1
Input : Student = [1, 2, 3] , Cookie = [1, 1]
Output :1

Explanation : You have 3 students and 2 cookies.
The minimum size of cookies required for students are 1 , 2 ,3.
You have 2 cookies both of size 1, So you can assign the cookie only to student having minimum cookie size 1.
So your answer is 1.

Example 2
Input : Student = [1, 2] , Cookie = [1, 2, 3]
Output : 2

Explanation : You have 2 students and 3 cookies.
The minimum size of cookies required for students are 1 , 2.
You have 3 cookies and their sizes are big enough to assign cookies to all students.
So your answer is 2.
```

```java
class Solution {
    public int findMaximumCookieStudents(int[] g, int[] s) {

        // m = number of students
        int m = g.length;

        // n = number of cookies
        int n = s.length;

        // Sort students by greed factor (smallest greed first)
        Arrays.sort(g);

        // Sort cookies by size (smallest cookie first)
        Arrays.sort(s);

        // i -> pointer for students
        // j -> pointer for cookies
        int i = 0;
        int j = 0;

        // Traverse while students and cookies are available
        while (i < m && j < n) {

            // If current cookie can satisfy current student
            // (cookie size >= student's greed)
            if (g[i] <= s[j]) {

                // Assign this cookie to the student
                i++;   // move to next student
                j++;   // move to next cookie

            } else {
                // Cookie is too small for the student
                // Try a bigger cookie
                j++;
            }
        }

        // i represents the number of students who got cookies
        return i;
    }
}
Time Complexity
O(mlogm + nlogn), where m and n are the lengths of the arrays g and s respectively, due to the sorting operations. The while loop is O(m+n).
Space Complexity
O(1) excluding the space used by the sorting algorithm, which might be O(log m + log n) depending on the implementation.
```