### There is only one row of fruit trees on the farm, oriented left to right. An integer array called fruits represents the trees, where fruits[i] denotes the kind of fruit produced by the ith tree. The goal is to gather as much fruit as possible, adhering to the owner's stringent rules:

1) There are two baskets available, and each basket can only contain one kind of fruit. The quantity of fruit each basket can contain is unlimited.

2) Start at any tree, but as you proceed to the right, select exactly one fruit from each tree, including the starting tree. One of the baskets must hold the harvested fruits.

3) Once reaching a tree with fruit that cannot fit into any basket, stop.

Return the maximum number of fruits that can be picked.

```
Examples:
Input : fruits = [1, 2, 1]
Output : 3
Explanation : We will start from first tree.
The first tree produces the fruit of kind '1' and we will put that in the first basket.
The second tree produces the fruit of kind '2' and we will put that in the second basket.
The third tree produces the fruit of kind '1' and we have first basket that is already holding fruit of kind '1'. So we will put it in first basket.
Hence we were able to collect total of 3 fruits.

Input : fruits = [1, 2, 3, 2, 2]
Output : 4
Explanation : we will start from second tree.
The first basket contains fruits from second , fourth and fifth.
The second basket will contain fruit from third tree.
Hence we collected total of 4 fruits.
```
```java
class Solution {
    public int totalFruits(int[] fruits) {

        int n = fruits.length;
        int ans = 0; // Will store the maximum fruits we can collect (i.e., max window size)

        // Outer loop: Try starting the basket from every index 'i'
        for (int i = 0; i < n; i++) {

            Set<Integer> set = new HashSet<>(); // To store unique fruit types in the current window

            // Inner loop: Expand the window from index 'i' to 'j'
            for (int j = i; j < n; j++) {

                set.add(fruits[j]); // Add current fruit type to the set

                // If more than 2 types of fruits exist → not allowed → break the loop
                if (set.size() > 2) {
                    break; 
                }

                // Valid window (size ≤ 2) → update the answer
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;   // Return the largest valid window size
    }
}
Time Complexity:The time complexity is O(n^2) due to the nested loops.
Space Complexity:The space complexity is O(1) because the HashSet 'set' stores at most 3 elements (integers), which is constant.
```

```java
class Solution {
    public int totalFruits(int[] fruits) {

        int n = fruits.length;
        int ans = 0;        // stores maximum number of fruits collected (max window size)
        int l = 0, r = 0;   // sliding window pointers

        Map<Integer, Integer> map = new HashMap<>(); // stores fruit type and its frequency in current window

        // Expanding right pointer (r) till array ends
        while (r < n) {

            // Add current fruit at r into the map (increase frequency)
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);

            // If we have more than 2 distinct fruits → shrink the window from the left
            while (map.size() > 2) {

                int freq = map.get(fruits[l]);  // Get frequency of the fruit at left pointer
                freq--;  // Decrease its frequency because window is shrinking

                if (freq == 0) {
                    map.remove(fruits[l]);      // Remove if frequency becomes 0
                } else {
                    map.put(fruits[l], freq);   // Otherwise, update new frequency
                }

                l++; // Move left pointer ahead (reduce window size)
            }

            // Update the maximum valid window size (only ≤2 fruit types allowed)
            ans = Math.max(ans, r - l + 1);

            r++; // Expand window by moving right pointer ahead
        }

        return ans; // Return max window size with at most 2 distinct fruit types
    }
}
Time Complexity:O(n), where n is the length of the fruits array, due to the while loop that iterates through the array.
Space Complexity:O(1), The space complexity is constant as the HashMap will store at most 3 elements, irrespective of input size.
```

```java
int n = fruits.length;
int ans = 0;          // Stores the maximum window size with at most 2 fruit types
int l = 0, r = 0;     // Sliding window pointers (left & right)

Map<Integer, Integer> map = new HashMap<>();  // Stores fruit type and its frequency in current window

while (r < n) {

    // Add current fruit at index 'r' to the map (or increase its frequency)
    map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);

    // If we now have more than 2 types of fruits → shrink window from the left
    if (map.size() > 2) {

        int freq = map.get(fruits[l]);  // Get frequency of the fruit at index 'l'
        freq--;                         // Decrease its count because window is shrinking

        if (freq == 0) {
            map.remove(fruits[l]);      // Remove fruit completely if frequency becomes 0
        } else {
            map.put(fruits[l], freq);   // Otherwise, update its reduced frequency
        }

        l++;    // Move left pointer ahead → shrink window
    }

    // Now, window contains at most 2 types → update maximum window size
    if (map.size() <= 2) {
        ans = Math.max(ans, r - l + 1);
    }

    r++;  // Expand window by moving right pointer
}

return ans; // Return the largest possible valid window size
Time Complexity:The time complexity is O(n) because the while loop iterates through the array at most once, and HashMap operations take O(1) time on average.
Space Complexity:The space complexity is O(1) because the HashMap stores at most three distinct fruit types, making the space used constant.
```