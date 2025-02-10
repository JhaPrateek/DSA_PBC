Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 
Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Approach - 1

  import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>(); // Stack to store opening brackets
        int n = s.length();
        
        // If the string is empty or has an odd length, it's invalid
        if (n == 0 || n % 2 != 0) {
            return false;
        }
        
        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            // If the character is an opening bracket, push it onto the stack
            if (c == '{' || c == '(' || c == '[') {
                st.push(c);
            } else { // If it's a closing bracket
                // If stack is empty and we get a closing bracket, it's invalid
                if (st.isEmpty()) {
                    return false;
                }
                
                // Check if the top of the stack has the matching opening bracket
                if (c == '}' && st.peek() == '{') {
                    st.pop(); // Valid pair found, remove opening bracket
                } else if (c == ']' && st.peek() == '[') {
                    st.pop(); // Valid pair found, remove opening bracket
                } else if (c == ')' && st.peek() == '(') {
                    st.pop(); // Valid pair found, remove opening bracket
                } else {
                    return false; // Mismatched brackets
                }
            }
        }
        
        // If the stack is empty at the end, all brackets were matched
        return st.isEmpty();
    }
}

TC - O(n)
SC - O(n) - stack used


Approach - 2

import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>(); // Stack to store expected closing brackets
        
        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            // If it's an opening bracket, push the corresponding closing bracket onto the stack
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } 
            // If it's a closing bracket, check if it matches the expected closing bracket from the stack
            else if (stack.isEmpty() || stack.pop() != c) {
                return false; // Stack is empty (extra closing bracket) or top element doesn't match
            }
        }
        
        // If stack is empty at the end, all brackets were matched properly
        return stack.isEmpty();
    }
}
