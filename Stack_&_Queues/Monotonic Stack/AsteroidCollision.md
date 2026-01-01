### You are given an integer array asteroids representing asteroids in a row. Each asteroid moves at the same speed.



The absolute value of an asteroid represents its size. The sign of an asteroid represents its direction: positive (+) means moving right, negative (-) means moving left.


Collision rules:

Asteroids moving in the same direction never collide.
When two asteroids moving in opposite directions collide, the smaller asteroid explodes and the larger asteroid continues moving in the same direction.
If both asteroids are equal in size, both explode.
Collisions are resolved one at a time, from left to right. If an asteroid survives a collision, it continues moving and may collide immediately with the next asteroid in its path.


Return the state of the asteroids after all collisions as an array in the same order.

```
Examples:
Input: asteroids = [1, 2, 3, -4, -2]
Output: [-4, -2]

Explanation:
Asteroid 3 and -4 collide → 3 explodes, -4 survives.
Asteroid -4 continues and collides with 2 → 2 explodes, -4 continues.
Asteroid -4 collides with 1 → 1 explodes, -4 continues.
Next asteroid -2 is moving left → no collision.
Final state: [-4, -2].

Input: asteroids = [5, 10, -5, -10, 8, -8, -3, 12]
Output: [5, 12]

Explanation:
Asteroid 10 and -5 collide → -5 explodes, 10 survives.
Asteroid 10 and -10 collide → both explode.
Asteroid 8 and -8 collide → both explode.
Asteroid -3 moves left → collides with 5 (right-moving) → 5 > 3 → -3 explodes, 5 survives.
Asteroid 12 moves right → no collision with 5 because it is behind → 12 survives.
Final state: [5, 12]
```

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        
        // Using Deque as a stack to store surviving asteroids
        Deque<Integer> st = new ArrayDeque<>();

        for (int a : asteroids) {
            boolean destroyed = false; // flag to check if current asteroid gets destroyed

            /*
                Collision can happen ONLY when:
                - The asteroid on stack top is moving RIGHT (positive)
                - Current asteroid is moving LEFT (negative)
                i.e., → ← (head-on collision)
            */
            while (!st.isEmpty() && st.peek() > 0 && a < 0) {

                int top = st.peek(); // asteroid at stack top (moving right)

                // Case 1: stack top asteroid is smaller → it gets destroyed
                if (Math.abs(top) < Math.abs(a)) {
                    st.pop();   // remove the smaller asteroid
                } 
                
                // Case 2: both asteroids have equal size → both destroyed
                else if (Math.abs(top) == Math.abs(a)) {
                    st.pop();       // remove top asteroid
                    destroyed = true;  // current asteroid also destroyed
                    break;            // stop processing
                } 
                
                // Case 3: top asteroid is bigger → current asteroid gets destroyed
                else {
                    destroyed = true;
                    break;           // current asteroid gone → stop
                }
            }

            // If current asteroid survived all collisions, push it into stack
            if (!destroyed) {
                st.push(a);
            }
        }

        // Convert stack to array (in correct left-to-right order)
        int[] ans = new int[st.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = st.pop();
        }

        return ans;
    }
}

Time Complexity:O(n) in the worst case, where n is the number of asteroids because each asteroid is pushed and popped from the stack at most once.
Space Complexity:O(n) in the worst case, where n is the number of asteroids because the stack can contain all the asteroids.

Intuition of Asteroid Collision (10 lines, crisp)
Only head-on collisions matter: + then -.
Use a stack to remember active right-moving asteroids.
Traverse asteroids one by one.
If current moves right → push directly.
If current moves left → it may collide with stack top.
Compare sizes during collision.
Smaller asteroid gets destroyed.
Equal size → both destroyed.
Bigger one survives and blocks further collisions.
Stack finally contains all surviving asteroids in order.
```