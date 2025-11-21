### Given an array arr of n integers and an integer target, determine if there is a subset of the given array with a sum equal to the given target.

Examples:
Input: arr = [1, 2, 7, 3], target = 6
Output: True
Explanation: There is a subset (1, 2, 3) with sum 6.

Input: arr = [2, 3, 5], target = 6
Output: False
Explanation: There is no subset with sum 6.

```java
class Solution {

    public boolean isSubsetSum(int[] arr, int target) {
        int n = arr.length;
        return fun(0, n, arr, target);
    }

    public boolean fun(int i, int n, int[] arr, int target) {

        // 🎯 Base Case: If target becomes 0 → subset found
        if (target == 0) {
            return true;
        }

        // ❌ Base Case: Reached end of array but target still > 0 → NOT possible
        if (i == n) {
            return false;
        }

        // ✔ OPTION 1: Take current element (only if it doesn’t make target negative)
        if (arr[i] <= target) {
            boolean take = fun(i + 1, n, arr, target - arr[i]);
            if (take) return true;   // As soon as we find TRUE — return immediately
        }

        // 🚫 OPTION 2: Not take current element and move ahead
        boolean notTake = fun(i + 1, n, arr, target);

        // Return true if ANY option gives true
        return notTake;
    }
}

/*
📌 TIME COMPLEXITY:  O(2^n)  // exponential recursion
📌 SPACE COMPLEXITY: O(n)   // recursion stack depth
*/

```

```java
class Solution {
    public boolean isSubsetSum(int[] arr, int target) {
        // dp[i][t] = whether we can make sum 't' using elements from index i to end
        Boolean[][] dp = new Boolean[arr.length][target + 1];
        return fun(0, arr, target, dp);
    }

    public boolean fun(int i, int[] arr, int target, Boolean[][] dp) {

        // 🎯 Base Case 1: If target becomes 0 → subset is found
        if (target == 0) return true;

        // ❌ Base Case 2: If array is fully traversed but target != 0 → no subset
        if (i == arr.length) return false;

        // 🔁 If already computed, return stored answer (Memoization)
        if (dp[i][target] != null) {
            return dp[i][target];
        }

        // 🚫 OPTION 1: Do NOT take current element
        boolean notTake = fun(i + 1, arr, target, dp);

        // ✔ OPTION 2: Take current element (only if it doesn't make target negative)
        boolean take = false;
        if (arr[i] <= target) {
            take = fun(i + 1, arr, target - arr[i], dp);
        }

        // Store result in dp table for future use ➝ Memoization
        return dp[i][target] = take || notTake;
    }
}

/*
📌 TIME COMPLEXITY:  O(n * target)
📌 SPACE COMPLEXITY: O(n * target) + O(n) recursion stack
*/

```

```java

```

