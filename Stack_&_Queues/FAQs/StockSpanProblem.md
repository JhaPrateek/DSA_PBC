### Given an array arr of size n, where each element arr[i] represents the stock price on day i. Calculate the span of stock prices for each day. The span Si for a specific day i is defined as the maximum number of consecutive previous days (including the current day) for which the stock price was less than or equal to the price on day i.


Examples:
Input: n = 7, arr = [120, 100, 60, 80, 90, 110, 115]

Output: [1, 1, 1, 2, 3, 5, 6]

Explanation:

Traversing the given input span:

120 is greater than or equal to 120 and there are no more elements behind it so the span is 1,

100 is greater than or equal to 100 and smaller than 120 so the span is 1,

60 is greater than or equal to 60 and smaller than 100 so the span is 1,

80 is greater than or equal to 60, 80 and smaller than 100 so the span is 2,

90 is greater than or equal to 60, 80, 90 and smaller than 100 so the span is 3,

110 is greater than or equal to 60, 80, 90, 100, 110 and smaller than 120 so the span is 5,

115 is greater than or equal to all previous elements and smaller than 120 so the span is 6.

Hence the output will be 1 1 1 2 3 5 6.

```java
class Solution {
    public int[] stockSpan(int[] arr, int n) {

      int ans[] = new int[n];   // Array to store stock span for each day

      // Outer loop picks each day as the current day
      for(int i = 0; i < n; i++){

        int cnt = 0;           // Counts span for current day i

        // Move backward from current day i to day 0
        for(int j = i; j >= 0; j--){

            // If previous day's price is less than or equal to today's price,
            // it is included in span
            if(arr[j] <= arr[i]){
                cnt++;
            }
            // As soon as we find a greater price, stop counting
            else{
                break;
            }
        }

        // Store the count as span for day i
        ans[i] = cnt;
      }

      // Return the stock span array
      return ans;
    }
}
Time Complexity:The time complexity is O(n^2) because of the nested loops.
Space Complexity:The space complexity is O(n) due to the ans array.
```

```java
class Solution {
    public int[] stockSpan(int[] arr, int n) {

      int ans[] = new int[n];          // Stores stock span result
      Stack<Integer> st = new Stack<>(); // Stack stores INDICES of days

      // Traverse all days from left to right
      for(int i = 0; i < n; i++){

        // ✅ Remove all previous days whose price is
        // less than or equal to current day's price
        while(!st.isEmpty() && arr[st.peek()] <= arr[i]){
            st.pop();
        }

        // ✅ If stack is empty → no greater element on left
        // So stock span = all days till now = i + 1
        // Else → span = distance from previous greater element
        ans[i] = st.isEmpty() ? i + 1 : i - st.peek();

        // ✅ Push current index into stack for future days
        st.push(i);
      }

      return ans;   // ✅ Final stock span array
    }
}
Time Complexity:O(n) because each element is pushed and popped from the stack at most once.
Space Complexity:O(n) due to the stack and the answer array, both potentially storing n elements.
```