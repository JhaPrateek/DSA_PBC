### A ninja has planned a n-day training schedule. Each day he has to perform one of three activities - running, stealth training, or fighting practice. The same activity cannot be done on two consecutive days and the ninja earns a specific number of merit points, based on the activity and the given day. Given a n x 3-sized matrix, where matrix[i][0], matrix[i][1], and matrix[i][2], represent the merit points associated with running, stealth and fighting practice, on the (i+1)th day respectively. Return the maximum possible merit points that the ninja can earn.

Input: matrix = [[10, 40, 70], [20, 50, 80], [30, 60, 90]]
Output: 210
Explanation:
Day 1: fighting practice = 70
Day 2: stealth training = 50
Day 3: fighting practice = 90
Total = 70 + 50 + 90 = 210
This gives the optimal points.

Input: matrix = [[70, 40, 10], [180, 20, 5], [200, 60, 30]]
Output: 290
Explanation:
Day 1: running = 70
Day 2: stealth training = 20
Day 3: running = 200
Total = 70 + 20 + 200 = 290
This gives the optimal points.

```java
class Solution {

    public int ninjaTraining(int[][] matrix) {

        int n = matrix.length;

        // Start from the last day (n-1)
        // Use last = 3 → meaning "no activity restriction on the first day"
        return fun(n - 1, 3, matrix);
    }


    // Recursive function:
    // n    = current day index
    // last = activity performed on the next day (cannot repeat this day)
    // matrix[day][activity] = points for that activity
    public int fun(int n, int last, int[][] matrix) {

        // Base Case: When reaching day 0
        // Choose the BEST activity except the one equal to "last"
        if (n == 0) {
            int max = 0;
            for (int i = 0; i < 3; i++) {
                if (i != last) {
                    max = Math.max(max, matrix[0][i]); // pick the best among allowed activities
                }
            }
            return max;
        }

        int max = 0;

        // Try all 3 activities (0 = run, 1 = stealth, 2 = fight)
        for (int i = 0; i < 3; i++) {

            // Skip the activity that was performed on the next day (no consecutive repeat)
            if (i != last) {

                // If choosing activity i today:
                // points = today’s points + best result from previous day with last = i
                max = Math.max(max, matrix[n][i] + fun(n - 1, i, matrix));
            }
        }

        // Return the best among all valid activities
        return max;
    }
}
// Time Complexity: O(3^n)
// Space Complexity:O(n) recursion stack => O(n)
```

```java
Memoization
import java.util.*;

class Solution {
    /* Recursive function to calculate the 
    maximum points for the ninja training*/
    private int func(int day, int last, int[][] points, int[][] dp) {
        // Time Complexity: O(n * 3) = O(n) — each state (day, last) computed once
        // Space Complexity: O(n * 4) = O(n) for memo table + O(n) recursion stack => O(n)
        // If the result is already calculated, return it
        if (dp[day][last] != -1) return dp[day][last];
        
        // Base case
        if (day == 0) {
            int maxi = 0;
        
            /* Calculate the maximum points for the first day
            by choosing an activity different from last one*/
            for (int i = 0; i < 3; i++) {
                if (i != last) {
                    maxi = Math.max(maxi, points[0][i]);
                }
            }
            // Store the result in dp array and return it
            return dp[day][last] = maxi;
        }

        // Initialize max points for the current day
        int maxi = 0;
        
        // Iterate through activities for the current day
        for (int i = 0; i < 3; i++) {
            if (i != last) {
                /* Calculate the points for the current activity
                and add it to the maximum points obtained so far */
                int activity = points[day][i] + func(day - 1, i, points, dp);
                maxi = Math.max(maxi, activity);
            }
        }

        // Store the result in dp array and return it
        return dp[day][last] = maxi;
    }

    // Function to find the maximum points for ninja training
    public int ninjaTraining(int[][] points) {
        // Get the number of days
        int days = points.length;
        
        // Initialize a memoization table with -1 values
        int dp[][] = new int[days][4];
        for (int[] row : dp)
            Arrays.fill(row, -1);
            
        // Return the maximum points
        return func(days - 1, 3, points, dp);
    }

    public static void main(String[] args) {
        int[][] points = {
            {10, 40, 70},
            {20, 50, 80},
            {30, 60, 90}
        };

        // Create an instance of Solution class
        Solution sol = new Solution();

        // Print the maximum points for ninja training
        System.out.println(sol.ninjaTraining(points));
    }
}
// Time Complexity: O(n * 3) = O(n)
// Space Complexity: O(n * 4) = O(n) for dp table + O(n) recursion stack => O(n)
```

```java
Tabulation
import java.util.Arrays;

class Solution {
    /* Function to calculate the maximum
    points for the ninja training*/
    public int ninjaTraining(int[][] matrix) {
        int n = matrix.length;
        // Create a 2D DP table to store the maximum points
        int[][] dp = new int[n][4];

        // Initialize the DP table for the first day (day 0)
        dp[0][0] = Math.max(matrix[0][1], matrix[0][2]);
        dp[0][1] = Math.max(matrix[0][0], matrix[0][2]);
        dp[0][2] = Math.max(matrix[0][0], matrix[0][1]);
        dp[0][3] = Math.max(matrix[0][0], Math.max(matrix[0][1], matrix[0][2]));

        // Iterate through the days starting from day 1
        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                // Iterate through the tasks for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != last) {
                        /* Calculate the points for the current 
                        activity and add it to the maximum points
                        obtained on the previous day */
                        int activity = matrix[day][task] + dp[day - 1][task];

                        /* Update the maximum points for 
                        the current day and last activity*/
                        dp[day][last] = Math.max(dp[day][last], activity);
                    }
                }
            }
        }

        /* The maximum points for the last day with 
        any activity can be found in dp[n-1][3]*/
        return dp[n - 1][3];
    }

    public static void main(String[] args) {
        int[][] points = {
            {10, 40, 70},
            {20, 50, 80},
            {30, 60, 90}
        };
        
        // Create an instance of Solution class
        Solution sol = new Solution();
        
        // Print the maximum points for ninja training
        System.out.println(sol.ninjaTraining(points)); 
    }
}
// Time Complexity: O(n * 4 * 3) = O(n)
// Space Complexity: O(n * 4) = O(n)

```

```java
Space Optimization
import java.util.Arrays;

class Solution {
    /* Function to calculate the maximum
    points for the ninja training*/
    public int ninjaTraining(int[][] matrix) {
        int n = matrix.length;
        
         /* Initialize a vector to store the maximum
        points for the previous day's activities*/
        int[] prev = new int[4];

        // Initialize the prev array for the first day (day 0)
        prev[0] = Math.max(matrix[0][1], matrix[0][2]);
        prev[1] = Math.max(matrix[0][0], matrix[0][2]);
        prev[2] = Math.max(matrix[0][0], matrix[0][1]);
        prev[3] = Math.max(matrix[0][0], Math.max(matrix[0][1], matrix[0][2]));

        // Iterate through the days starting from day 1
        for (int day = 1; day < n; day++) {
            /* Initialize a temporary vector to store the
            maximum points for the current day's activities*/
            int[] temp = new int[4];

            for (int last = 0; last < 4; last++) {
                temp[last] = 0;
                // Iterate through the tasks for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != last) {
                        /* Calculate the points for the current activity
                        and add it to the maximum points obtained on the
                        previous day (stored in prev)*/
                        temp[last] = Math.max(temp[last], matrix[day][task] + prev[task]);
                    }
                }
            }

            // Update prev with maximum points for the current day
            prev = temp;
        }

        /* The maximum points for the last day with 
        any activity can be found in prev[3]*/
        return prev[3];
    }

    public static void main(String[] args) {
        int[][] points = {
            {10, 40, 70},
            {20, 50, 80},
            {30, 60, 90}
        };

        // Create an instance of Solution class
        Solution sol = new Solution();

        // Print the maximum points for ninja training
        System.out.println(sol.ninjaTraining(points)); 
    }
}
// Time Complexity: O(n * 4 * 3) = O(n)
// Space Complexity: O(1) (only constant extra space `prev`/`temp` of size 4)
```
