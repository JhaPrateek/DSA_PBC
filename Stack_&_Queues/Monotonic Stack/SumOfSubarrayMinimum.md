### Given an array of integers arr of size n, calculate the sum of the minimum value in each (contiguous) subarray of arr. Since the result may be large, return the answer modulo 109 +7.
```
Examples:
Input: arr = [3, 1, 2, 5]

Output: 18

Explanation: The minimum of subarrays: [3], [1], [2], [5], [3, 1], [1, 2], [2, 5], [3, 1, 2], [1, 2, 5], [3, 1, 2, 5] are 3, 1, 2, 5, 1, 1, 2, 1, 1, 1 respectively and their sum is 18.

Input: arr = [2, 3, 1]

Output: 10

Explanation: The minimum of subarrays: [2], [3], [1], [2,3], [3,1], [2,3,1] are 2, 3, 1, 2, 1, 1 respectively and their sum is 10.
```
```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int ans = 0;

        // We consider every possible subarray arr[i..j]
        for (int i = 0; i < n; i++) {

            int min = Integer.MAX_VALUE; 
            // 'min' will track the minimum value of the current subarray starting at index i

            for (int j = i; j < n; j++) {

                // update minimum for subarray arr[i..j]
                min = Math.min(min, arr[j]);

                // add this minimum to total sum
                // because each subarray contributes its minimum once
                ans += min;
            }
        }

        // return result modulo (1e9 + 7) as required
        return ans % (int)(1e9 + 7);
    }
}
Time Complexity:The time complexity is O(n^2) because of the nested loops.
Space Complexity:The space complexity is O(1) because only constant extra space is used.
```

```java
class Solution {

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;

        // NSE[i] = index of next element to the RIGHT that is strictly smaller than arr[i]
        int NSE[] = NSE(arr, n);

        // PSE[i] = index of previous element to the LEFT that is smaller OR equal to arr[i]
        int PSE[] = PSE(arr, n);

        int MOD = 1_000_000_007;
        long ans = 0;

        // Each element arr[i] contributes to all subarrays where it is the minimum.
        for (int i = 0; i < n; i++) {

            // Number of choices for left boundary (how many subarrays extend left from i)
            long left = i - PSE[i];

            // Number of choices for right boundary (how many subarrays extend right from i)
            long right = NSE[i] - i;

            // Total number of subarrays where arr[i] is the minimum = left * right
            long totalSubarrays = left * right;

            // Add contribution of arr[i] multiplied by number of subarrays
            // Use modulo to prevent overflow
            ans = (ans + (totalSubarrays * arr[i]) % MOD) % MOD;
        }

        return (int) ans;
    }


    // -------------------------------------------------------------
    // Next Smaller Element (NSE)
    // -------------------------------------------------------------
    // Finds the first index to the RIGHT of i where nums[j] < nums[i].
    // If none exists, we return n (sentinel index representing boundary).
    //
    // This version uses STRICT condition: nums[st.peek()] > nums[i]
    // That means equal elements are NOT popped on the right.
    // As a result, equal values extend the right boundary.
    // -------------------------------------------------------------
    public int[] NSE(int nums[], int n) {

        int result[] = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from RIGHT to LEFT
        for (int i = n - 1; i >= 0; i--) {

            // Pop indices whose values are strictly greater than nums[i]
            // Those cannot be the next smaller element
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
                st.pop();
            }

            // If stack empty → no smaller element to right → use n
            result[i] = st.isEmpty() ? n : st.peek();

            // Push current index for future comparisons
            st.push(i);
        }

        return result;
    }


    // -------------------------------------------------------------
    // Previous Smaller or Equal Element (PSE)
    // -------------------------------------------------------------
    // Finds the first index to the LEFT of i where nums[j] <= nums[i].
    // If none exists, return -1.
    //
    // Condition used: nums[st.peek()] >= nums[i]
    // This pops STRICTLY greater AND equal values.
    // Meaning: for duplicates, the RIGHTMOST copy carries the responsibility.
    //
    // This pairs correctly with NSE's strict '>' rule and avoids double-counting.
    // -------------------------------------------------------------
    public int[] PSE(int nums[], int n) {

        int result[] = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from LEFT to RIGHT
        for (int i = 0; i < n; i++) {

            // Pop all elements greater or equal to current
            // Equal values are popped, giving priority to the RIGHT duplicate.
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }

            // If empty → no smaller or equal element exists to the left
            result[i] = st.isEmpty() ? -1 : st.peek();

            // Push current index
            st.push(i);
        }

        return result;
    }
}
Time Complexity:O(n) because the code iterates through the array 'arr' of size 'n' three times (once in sumSubarrayMins, once in NSE, and once in PSE), with each iteration taking O(n) time. The stack operations within the loops are amortized O(1), so they dont change the overall time complexity.
Space Complexity:O(n) due to the auxiliary arrays NSE and PSE, each of size 'n', and the stack used in NSE and PSE, which can grow up to size 'n' in the worst case.
```
### Intution

Every element arr[i] becomes the minimum in some subarrays, and we only need to count how many subarrays choose arr[i] as the minimum.

To know these subarrays, we find how far arr[i] can extend to the left until a smaller element appears and how far it can extend to the right until a smaller element appears.

The total number of subarrays where arr[i] is the minimum is (i - PSE[i]) multiplied by (NSE[i] - i).

When duplicate values exist, both duplicates may try to count the same subarrays, which leads to incorrect answers unless we set clear tie breaking rules.

To avoid duplicate counting, one side must treat equal values as a stopping point, and the other side must treat equal values as not a stopping point. This ensures each duplicate gets its own unique counting range.

In your version, PSE uses >=. This means elements that are greater or equal on the left are removed, allowing the rightmost duplicate to control the counting window on the left side.

NSE uses >. This means equal values on the right are not removed, allowing them to extend the window further, which balances the effect of PSE removing equals.

If both PSE and NSE use >=, duplicates on both sides get removed, causing the same subarray to be counted twice.

If both PSE and NSE use >, duplicates remain on both sides, causing some subarrays to not be counted at all.

The modulo expression ans = (ans + (val * arr[i]) % MOD) % MOD is used because multiplication may overflow first, and then addition may overflow again, so modulo must be applied twice to keep the final result within the allowed range.