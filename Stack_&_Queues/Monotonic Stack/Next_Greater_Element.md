### Given an array arr[ ] of integers, the task is to find the next greater element for each element of the array in order of their appearance in the array. Next greater element of an element in the array is the nearest element on the right which is greater than the current element.
If there does not exist next greater of current element, then next greater element for current element is -1. For example, next greater of the last element is always -1.
```
Examples
Input: arr[] = [1, 3, 2, 4]
Output: [3, 4, 4, -1]
Explanation: The next larger element to 1 is 3, 3 is 4, 2 is 4 and for 4, since it doesn't exist, it is -1.
```

```java
class Solution {
    public int[] nextLargerElement(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int ans[] = new int[n];

        ans[n - 1] = -1;       // Last element → always -1
        st.push(arr[n - 1]);  // Push last element to stack

        for (int i = n - 2; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= arr[i]) {
                st.pop(); // Remove smaller elements
            }
            ans[i] = st.isEmpty() ? -1 : st.peek(); // Next greater
            st.push(arr[i]); // Push current element for future elements
        }
        return ans;
    }
}
Time Complexity:O(n), where n is the length of the input array, due to the single for loop and amortized constant time stack operations.
Space Complexity:O(n), where n is the length of the input array, due to the stack and the answer array.
```
