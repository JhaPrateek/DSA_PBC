### Given a string s , consisting only of characters 'a' , 'b' , 'c'.Find the number of substrings that contain at least one occurrence of all these characters 'a' , 'b' , 'c'.

Examples:
Input : s = "abcba"
Output : 5
Explanation : The substrings containing at least one occurrence of the characters 'a' , 'b' , 'c' are "abc" , "abcb" , "abcba" , "bcba" , "cba".

Input : s = "ccabcc"
Output : 8
Explanation : The substrings containing at least one occurrence of the characters 'a' , 'b' , 'c' are "ccab" , "ccabc" , "ccabcc" , "cab" , "cabc" , "cabcc" , "abc" , "abcc".

```java
class Solution {    
    public int numberOfSubstrings(String s) {

        int n = s.length();
        int cnt = 0;  // Stores total number of valid substrings

        // Try every starting index 'i'
        for (int i = 0; i < n; i++) {

            Set<Character> set = new HashSet<>();  // To store unique characters in the current substring

            // Expand substring from index 'i' to 'j'
            for (int j = i; j < n; j++) {

                set.add(s.charAt(j));  // Add current character into set

                // If all 3 characters ('a', 'b', 'c') are present:
                if (set.size() == 3) {

                    // 🔥 All substrings starting at 'i' and ending from 'j' up to end (n-1) are valid
                    // Example: for n = 7, j = 4 → substrings (i,4), (i,5), (i,6) → total = n - j
                    cnt += (n - j);

                    // No need to expand further, already found the smallest j that makes substring valid
                    break;
                }
            }
        }
        return cnt;  // Return total valid substrings
    }
}
Time Complexity:O(n^2) due to the nested loops, where the outer loop iterates 'n' times and the inner loop iterates up to 'n' times in the worst case.
Space Complexity:O(1) because the HashSet 'set' stores at most 3 characters, making its space usage constant regardless of the input string length.
```

```java
class Solution {    
    public int numberOfSubstrings(String s) {

        int n = s.length();
        int cnt = 0;         // Stores total count of valid substrings
        int l = 0, r = 0;    // Sliding window pointers

        Map<Character, Integer> map = new HashMap<>();

        while (r < n) {

            // Add the current character to the map (increase frequency)
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);

            // 📌 When all 3 characters are present in the window
            while (map.size() == 3) {

                // All substrings starting from current window [l..r] to [l..n-1] are valid
                cnt += (n - r);

                // Shrink the window from the left side
                int freq = map.get(s.charAt(l));
                freq--;

                if (freq == 0) {
                    map.remove(s.charAt(l)); // Remove char entirely if frequency becomes 0
                } else {
                    map.put(s.charAt(l), freq); // Otherwise update reduced frequency
                }

                l++;  // Move left pointer to further shrink the window
            }

            r++; // Move right pointer to expand the window
        }

        return cnt;
    }
}
Time Complexity:O(n) because the `while` loop iterates through the string once, and the inner `while` loops iterations are bounded by `n`, where n is the length of the string.
Space Complexity:O(1) because the HashMap stores at most 3 distinct characters, resulting in constant space complexity.
```