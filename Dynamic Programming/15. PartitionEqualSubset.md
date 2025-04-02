## Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;

        // Calculate the total sum of elements
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        // If sum is odd, we cannot divide it into two equal subsets
        if (sum % 2 != 0) {
            return false;
        }

        // Start recursive function to check if a subset with sum/2 exists
        return fun(n - 1, nums, sum / 2);
    }

    public boolean fun(int ind, int[] nums, int sum) {
        // If sum becomes 0, we found a valid subset
        if (sum == 0) {
            return true;
        }

        // If we reach the first element, check if it matches the required sum
        if (ind == 0) {
            return sum == nums[ind];
        }

        // Choice 1: Do not take the current element
        boolean notTake = fun(ind - 1, nums, sum);

        // Choice 2: Take the current element if it's <= required sum
        boolean take = false;
        if (sum >= nums[ind]) {
            take = fun(ind - 1, nums, sum - nums[ind]);
        }

        // Return true if either option leads to a valid partition
        return take || notTake;
    }
}

/*
Time Complexity (TC): O(2^N) 
   - Each element has two choices: either be included or not, leading to exponential recursion.

Space Complexity (SC): O(N)
   - Due to the recursive stack depth, in the worst case, recursion can go up to N levels.
*/

```

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        
        // Calculate the sum of all elements
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        
        // If the sum is odd, we cannot partition it into two equal subsets
        if (sum % 2 != 0) {
            return false;
        }
        
        // Create a memoization table
        Boolean dp[][] = new Boolean[n][sum / 2 + 1];
        
        // Call the recursive function to check if we can form sum/2
        return fun(n - 1, nums, sum / 2, dp);
    }
    
    public boolean fun(int ind, int nums[], int sum, Boolean dp[][]) {
        // If the required sum is 0, return true
        if (sum == 0) {
            return true;
        }
        
        // If we reach the first element, check if it equals the required sum
        if (ind == 0) {
            return nums[ind] == sum;
        }
        
        // If already computed, return the stored value
        if (dp[ind][sum] != null) {
            return dp[ind][sum];
        }
        
        // Case 1: Exclude the current element
        boolean notTake = fun(ind - 1, nums, sum, dp);
        
        // Case 2: Include the current element if it does not exceed sum
        boolean take = false;
        if (sum >= nums[ind]) {
            take = fun(ind - 1, nums, sum - nums[ind], dp);
        }
        
        // Store the result in the memoization table and return it
        return dp[ind][sum] = take || notTake;
    }
}

/*
Time Complexity: O(n * sum/2)  
- Since each state (ind, sum) is computed once due to memoization, we have approximately O(n * sum) states. 
- Each state takes O(1) time to compute.

Space Complexity: O(n * sum/2) (for the memoization table) + O(n) (recursive stack space in the worst case) 
- Total: O(n * sum/2) space.
*/

```

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        
        // Calculate the sum of all elements
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        
        // If the sum is odd, we cannot partition it into two equal subsets
        if (sum % 2 != 0) {
            return false;
        }
        
        // Define a DP table to store subset sum possibilities
        boolean dp[][] = new boolean[n][sum / 2 + 1];
        sum = sum / 2;
        
        // Initialize first column as true since we can always form sum 0
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        
        // If the first element is within the sum range, mark it as achievable
        if (sum >= nums[0]) {
            dp[0][nums[0]] = true;
        }
        
        // Fill the DP table using bottom-up approach
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                
                // Exclude the current element
                boolean notTake = dp[i - 1][j];
                
                // Include the current element if it does not exceed sum
                boolean take = false;
                if (j >= nums[i]) {
                    take = dp[i - 1][j - nums[i]];
                }
                
                // Store the result in DP table
                dp[i][j] = take || notTake;
            }
        }
        
        // Final answer is whether we can form sum/2 using all elements
        return dp[n - 1][sum];
    }
}

/*
Time Complexity: O(n * sum/2)
- We iterate through all elements and for each element, we check all sums up to sum/2.
- This results in O(n * sum/2) operations.

Space Complexity: O(n * sum/2)
- We use a 2D DP table of size n x (sum/2), leading to O(n * sum/2) space.
- This can be optimized to O(sum/2) using a 1D DP array.
*/

```
