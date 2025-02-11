Given an array arr[ ] of integers, the task is to find the next greater element for each element of the array in order of their appearance in the array. Next greater element of an element in the array is the nearest element on the right which is greater than the current element.
If there does not exist next greater of current element, then next greater element for current element is -1. For example, next greater of the last element is always -1.

Examples
Input: arr[] = [1, 3, 2, 4]
Output: [3, 4, 4, -1]
Explanation: The next larger element to 1 is 3, 3 is 4, 2 is 4 and for 4, since it doesn't exist, it is -1.

Time Complexity: O(N)
Space Complexity: O(N)

```java
class Solution {
    // Function to find the next greater element for each element of the array.
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> al = new ArrayList<>();
        Stack<Integer> st = new Stack<>(); // Stack to keep track of next greater elements.

        // Traverse the array from right to left.
        for (int i = n - 1; i >= 0; i--) {
            // If stack is empty, there is no greater element, so add -1.
            if (st.isEmpty()) {
                al.add(-1);
            }
            // If top of stack is greater than current element, it is the next greater element.
            else if (arr[i] < st.peek()) {
                al.add(st.peek());
            }
            // If top of stack is smaller or equal, pop elements until a greater one is found.
            else {
                while (!st.isEmpty() && st.peek() <= arr[i]) {
                    st.pop();
                }
                // If stack is not empty, top of stack is the next greater element.
                if (!st.isEmpty()) {
                    al.add(st.peek());
                } 
                // If stack is empty, no greater element exists, so add -1.
                else {
                    al.add(-1);
                }
            }
            // Push the current element to stack for future comparisons.
            st.push(arr[i]);
        }

        // Reverse the list to maintain original order.
        Collections.reverse(al);
        return al;
    }
}

```
