### Given N cards arranged in a row, each card has an associated score denoted by the cardScore array. Choose exactly k cards. In each step, a card can be chosen either from the beginning or the end of the row. The score is the sum of the scores of the chosen cards. Return the maximum score that can be obtained.

```
Examples:
Input : cardScore = [1, 2, 3, 4, 5, 6] , k = 3
Output : 15
Explanation : Choosing the rightmost cards will maximize your total score. So optimal cards chosen are the rightmost three cards 4 , 5 , 6.
The score is 4 + 5 + 6 => 15.

Input : cardScore = [5, 4, 1, 8, 7, 1, 3 ] , k = 3
Output : 12
Explanation : In first step we will choose card from beginning with score of 5.
In second step we will choose the card from beginning again with score of 4.
In third step we will choose the card from end with score of 3.
The total score is 5 + 4 + 3 => 12
```
```java
class Solution {
    public int maxScore(int[] cardScore, int k) {
        int n = cardScore.length;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        
        // STEP 1: Take first 'k' cards from the LEFT side initially
        // This is our initial window sum
        int i = 0;
        for (i = 0; i < k; i++) {
            sum += cardScore[i];
        }
        
        max = sum; // store the maximum sum found so far
        
        // STEP 2: Start taking cards from the RIGHT side one by one
        // and removing cards from the LEFT side (end of current window)
        int j = n - 1; // pointer to start from right end
        
        // We need to replace 'k' cards, so loop 'k' times
        while (j >= n - k) {  
            // Remove one card from the left window (i points to end of left window)
            sum -= cardScore[--i];  // remove last left-side element
            
            // Add one card from the right side
            sum += cardScore[j--];  // add right-side element
            
            // Update max after every change
            max = Math.max(max, sum);
        }
        
        return max; // return best score possible
    }
}
Time Complexity:O(k) due to the initial loop and O(k) due to the while loop, resulting in O(2k), which simplifies to O(k)
Space Complexity:O(1) as it uses a fixed amount of extra space regardless of the input size
```