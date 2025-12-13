### Given a string nums representing a non-negative integer, and an integer k, find the smallest possible integer after removing k digits from num.

Note: If removing k digits deletes all digits, return "0". The result must be a valid non-negative integer without leading zeros.

```
Example 1
Input: nums = "541892", k = 2
Output: "1892"
Explanation: Removing the two digits 5 and 4 yields the smallest number, 1892.
```

```
Example 2
Input: nums = "1002991", k = 3
Output: "21"
Explanation: Remove the three digits 1(leading one), 9, and 9 to form the new number 21(Note that the output must not contain leading zeroes) which is the smallest.
```

```
Example 3
Input: nums = "10", k = 2
Output:
"0"
```

```java
class Solution {
    /* Function to find the smallest possible 
    integer after removing k digits */
    public String removeKdigits(String nums, int k) {
        
        Stack<Character> st = new Stack<>(); // Stack
        
        // Traverse on the given string
        for(int i = 0; i < nums.length(); i++) {
            
            // Current digit
            char digit = nums.charAt(i);
            
            /* Pop last digits (when possible)
            if a smaller digit is found*/
            while(!st.isEmpty() && k > 0
                  && st.peek() > digit) {

                st.pop(); // Pop the last digit
                k--; // Decrement K by 1
            }
            
            // Push the current digit
            st.push(digit);
        }
        
        // If more digits can be removed
        while(!st.isEmpty() && k > 0) {
            
            st.pop(); // Pop the last added digits
            k--; // Decrement K by 1
        }
        
        // Handling edge case
        if(st.isEmpty()) return "0";
        
        // To store the result
        StringBuilder res = new StringBuilder();
        
        // Adding digits in stack to result
        while(!st.isEmpty()) {
            res.append(st.pop());
        }
        
        // Trimming the zeroes at the back
        while(res.length() > 0 && 
              res.charAt(res.length() - 1) == '0') {

            res.deleteCharAt(res.length() - 1);
        }
        
        // Reverse to get the actual number
        res.reverse();
        
        // Edge case
        if(res.length() == 0) return "0";
        
        // Return the stored result
        return res.toString();
    }
}
Time Complexity: O(N) (where N is the length of the given number)
Traversing the given string takes O(N) time.
Each element is pushed onto and popped from the stack at most once in worst-case taking o(N) time.
Removing the remaining digits (if k > 0) takes O(k) time which can go upto O(N) in worst-case.
Forming the result, trimming the zeros and reversing the digits takes O(N) time.

Space Complexity: O(N)
The stack space can be O(N) in the worst-case.
The space required to store the result is O(N) in worst-case.    
```