### Given an array arr of n integers, where arr[i] represents price of the stock on the ith day. Determine the maximum profit achievable by buying and selling the stock at most once. The stock should be purchased before selling it, and both actions cannot occur on the same day.


Examples:
Input: arr = [10, 7, 5, 8, 11, 9]
Output: 6
Explanation: Buy on day 3 (price = 5) and sell on day 5 (price = 11), profit = 11 - 5 = 6.

Input: arr = [5, 4, 3, 2, 1]
Output: 0
Explanation: In this case, no transactions are made. Therefore, the maximum profit remains 0.

```java
class Solution {
    public int stockBuySell(int[] arr, int n) {

        int min = arr[0];   // Track minimum price so far (best buy option)
        int ans = 0;        // Track maximum profit

        for (int i = 1; i < n; i++) {

            // If current price is greater than the previous minimum,
            // selling today gives a profit → update max profit
            if (arr[i] > min) {
                ans = Math.max(ans, arr[i] - min);
            } 
            else {
                // If current price is lower or equal, update min (better buy price)
                min = arr[i];
            }
        }

        return ans;   // Maximum profit
    }
}
Time Complexity - O(n)
Space Complexity - O(1)
```