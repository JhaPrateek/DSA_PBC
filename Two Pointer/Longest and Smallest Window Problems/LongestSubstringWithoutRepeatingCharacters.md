### Given a string, S. Find the length of the longest substring without repeating characters.

Examples:
Input : S = "abcddabac"
Output : 4
Explanation : The answer is "abcd" , with a length of 4.

Input : S = "aaabbbccc"
Output : 
Explanation : The answers are "ab" , "bc". Both have maximum length 2.


```java
class Solution {
    public int longestNonRepeatingSubstring(String s) {
        int n = s.length();
        int max = 1; // stores length of longest non-repeating substring

        // Outer loop: choose starting point of substring
        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>(); // to store unique characters

            // Inner loop: expand substring from index i towards right
            for (int j = i; j < n; j++) {

                // If character is not seen before, add to set
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));

                    // set.size() gives current valid substring length
                    max = Math.max(max, set.size());
                } 
                // if duplicate is found → break (this substring is no longer valid)
                else {
                    break;
                }
            }
        }
        return max;
    }
}
Time Complexity:The time complexity is O(n^2) because of the nested loops where the outer loop iterates 'n' times, and the inner loop iterates up to 'n' times in the worst case for each iteration of the outer loop.
Space Complexity:The space complexity is O(n) because, in the worst-case scenario, the HashSet 'set' can store up to 'n' distinct characters from the input string 's'.
```

```java
class Solution {
    public int longestNonRepeatingSubstring(String s) {
        int n = s.length();
        int i = 0, j = 0, max = 0;      // i = left pointer, j = right pointer of sliding window

        Map<Character, Integer> map = new HashMap<>();   // stores last seen index of each character

        while (j < n) {                 // expand the window using j (right pointer)
            char ch = s.charAt(j);

            // 🔴 If character already seen AND its previous index is inside current window
            //    then move 'i' to right of that previous index (to remove duplicate)
            if (map.containsKey(ch) && map.get(ch) >= i) {
                // shrink window from the left to avoid duplicate
                i = map.get(ch) + 1;
            }

            // 🟢 Store/update the latest index of current character
            map.put(ch, j);

            // 🧠 Calculate window length: j - i + 1  (current valid substring)
            max = Math.max(max, j - i + 1);   // update max if current window is larger

            j++;     // move right pointer forward (expand window)
        }

        return max;
    }
}
Time Complexity:O(n), where n is the length of the input string s, due to the single while loop that iterates through the string.
Space Complexity:O(min(n, charset)), where n is the length of the string s, and m is the size of the character set, due to the space used by the HashMap.
```