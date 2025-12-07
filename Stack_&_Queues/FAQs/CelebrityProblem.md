### A celebrity is a person who is known by everyone else at the party but does not know anyone in return. Given a square matrix M of size N x N where M[i][j] is 1 if person i knows person j, and 0 otherwise, determine if there is a celebrity at the party. Return the index of the celebrity or -1 if no such person exists.

Note that M[i][i] is always 0.

Examples:
Input: M = [ [0, 1, 1, 0], [0, 0, 0, 0], [1, 1, 0, 0], [0, 1, 1, 0] ]
Output: 1
Explanation: Person 1 does not know anyone and is known by persons 0, 2, and 3. Therefore, person 1 is the celebrity.

Input: M = [ [0, 1], [1, 0] ]
Output: -1
Explanation: Both persons know each other, so there is no celebrity.

```java
class Solution {
    // Function to find the index of celebrity
    public int celebrity(int[][] M) {
        
        // Size of given matrix
        int n = M.length;
        
        /* To store count of people who 
        know person of index i */
        int[] knowMe = new int[n];
        
        /* To store count of people who 
        the person of index i knows */
        int[] Iknow = new int[n];
        
        // Traverse on given matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                // If person i knows person j
                if (M[i][j] == 1) {
                    knowMe[j]++;
                    Iknow[i]++;
                }
            }
        }
        
        // Traverse for all persons to find the celebrity
        for (int i = 0; i < n; i++) {
            
            // Return the index of celebrity
            if (knowMe[i] == n - 1 && Iknow[i] == 0) {
                return i;  
            }
        }
        
        // Return -1 if no celebrity is found
        return -1;
    }
}
Time Complexity: O(N^2), as Traversing the given square matrix to populate the two lists takes O(N2) time and Traversing on the lists to identify the celebrity takes O(N) time.
Space Complexity: O(N), The two lists to store the count of how many people each person knows and how many people know each person takes O(N) space each.    
```

```java
class Solution {
    public int celebrity(int[][] M) {
        
        int n = M.length;
        // Total number of people
        
        // Try each person as a potential celebrity
        for (int i = 0; i < n; i++) {
            
            boolean everyoneKnows = true;
            // Assumption: everyone knows person i
            
            boolean knowsSomeone = false;
            // Assumption: person i knows no one
            
            // Check both celebrity conditions for person i
            for (int j = 0; j < n; j++) {

                // Condition 1:
                // Celebrity should NOT know anyone
                if (M[i][j] == 1) {
                    knowsSomeone = true;
                }

                // Condition 2:
                // Everyone should know the celebrity
                if (i != j && M[j][i] == 0) {
                    everyoneKnows = false;
                }
            }
            
            // If both conditions are satisfied, i is the celebrity
            if (!knowsSomeone && everyoneKnows) {
                return i;
            }
        }
        
        // If no celebrity exists
        return -1;
    }
}
Time Complexity:O(n^2) because there is a nested for loop iterating up to n.
Space Complexity:O(1) because the algorithm uses a constant amount of extra space.
```

```java
class Solution {
    // Function to find the index of celebrity
    public int celebrity(int[][] M) {
        
        // Total number of people at the party
        int n = M.length;
        
        // 'top' starts from the first person
        // 'down' starts from the last person
        int top = 0, down = n - 1;
        
        // This loop is used to eliminate non-celebrities
        // Only one potential celebrity will remain after this loop
        while (top < down) {
            
            /*
             If top knows down,
             then top cannot be a celebrity
             
             Because a celebrity knows nobody
             So we eliminate 'top' and move forward
            */
            if (M[top][down] == 1) {
                top = top + 1;
            }
            
            /*
             If down knows top,
             then down cannot be a celebrity
             
             So we eliminate 'down' and move backward
            */
            else if (M[down][top] == 1) {
                down = down - 1;
            }
            
            /*
             If neither top knows down
             nor down knows top,
             
             Then both cannot be a celebrity:
             - top is not known by down
             - down is not known by top
             
             So we eliminate both
            */
            else {
                top++;
                down--;
            }
        }
        
        /*
         If top has crossed down, 
         it means no possible celebrity candidate exists
        */
        if (top > down) return -1;
        
        /*
         Now 'top' is the only remaining candidate.
         We must verify whether this person is truly a celebrity.
        */
        for (int i = 0; i < n; i++) {
            
            // Skip checking the same person
            if (i == top) continue;
            
            /*
             Two conditions for a celebrity:
             1) Celebrity should NOT know anyone → M[top][i] must be 0
             2) Everyone should know the celebrity → M[i][top] must be 1
             
             If any condition fails, return -1
            */
            if (M[top][i] == 1 || M[i][top] == 0) {
                return -1;
            }
        }
        
        // If all conditions are satisfied, return the celebrity index
        return top;
    }
}
Time Complexity:The time complexity is O(n), where n is the number of people, due to the while loop and the for loop, each iterating at most n times.
Space Complexity:The space complexity is O(1), as it uses a constant amount of extra space.
```