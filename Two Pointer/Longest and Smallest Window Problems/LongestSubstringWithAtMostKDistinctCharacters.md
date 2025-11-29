### Given a string s and an integer k.Find the length of the longest substring with at most k distinct characters.

Examples:
Input : s = "aababbcaacc" , k = 2
Output : 6
Explanation : The longest substring with at most two distinct characters is "aababb".
The length of the string 6.

Input : s = "abcddefg" , k = 3
Output : 4
Explanation : The longest substring with at most three distinct characters is "bcdd".
The length of the string 4.

```java
class Solution {
    public int kDistinctChar(String s, int k) {
        int n = s.length();
        int ans = 0; // Stores maximum substring length with <= k distinct characters

        // Outer loop: choose each index as starting point of substring
        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>(); // Stores distinct characters in current substring

            // Inner loop: expand substring from index i to j
            for (int j = i; j < n; j++) {
                set.add(s.charAt(j)); // Insert character into set

                // If distinct character count exceeds k → stop this window (invalid)
                if (set.size() > k) {
                    break;  // No need to check further for this starting point
                }

                // Update maximum valid substring length found so far
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans; // Return longest valid substring length
    }
}
Time Complexity:O(n^2) due to nested loops, where n is the length of the string.
Space Complexity:O(k) because the HashSet can store at most k distinct characters.
```

```java
class Solution {
    public int kDistinctChar(String s, int k) {
        int n = s.length();
        int ans = 0;
        int l = 0, r = 0; // Left and right pointers for sliding window

        Map<Character, Integer> map = new HashMap<>(); // Stores character frequency inside current window

        while (r < n) { // Expand the window by moving 'r'
            
            // Add current character into the map (increase freq)
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);

            // If number of distinct characters > k → shrink from left
            while (map.size() > k) {
                char leftChar = s.charAt(l);    // Character at left pointer
                int freq = map.get(leftChar);  // Get its frequency
                
                freq--;                         // Reduce its frequency
                
                if (freq == 0) {
                    map.remove(leftChar);      // Remove from map if freq is 0
                } else {
                    map.put(leftChar, freq);   // Otherwise update the reduced freq
                }
                
                l++; // Move left pointer forward (shrink window)
            }

            // Now window is valid (distinct chars ≤ k) → update answer
            ans = Math.max(ans, r - l + 1);
            r++; // Expand window further
        }

        return ans; // Return longest valid substring length
    }
}
Time Complexity:The time complexity is O(n) because, in the worst case, the right pointer 'r' and the left pointer 'l' each traverse the string of length 'n' once.
Space Complexity:The space complexity is O(k) because the HashMap 'map' stores at most 'k' distinct characters, where k is the input parameter.
```

```java
class Solution {
    public int kDistinctChar(String s, int k) {
        int n = s.length();
        int ans = 0;     // Stores longest valid substring length
        int l = 0, r = 0; // Sliding window pointers (two pointers)

        // HashMap to store frequency of characters inside current window
        Map<Character, Integer> map = new HashMap<>();

        while (r < n) { // Expand the window by moving 'r' to the right

            // 1️⃣ Add current character's frequency into the map
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);

            // 2️⃣ If we have MORE than k distinct characters → shrink window from left
            //    Why shrink? Because window is INVALID (more than k distinct chars)
            if (map.size() > k) {
                int freq = map.get(s.charAt(l));
                freq--;

                // If character's frequency becomes 0 → remove from map
                if (freq == 0) {
                    map.remove(s.charAt(l));
                } else {
                    map.put(s.charAt(l), freq);
                }
                l++;   // Move left pointer to reduce window size
            }

            // 3️⃣ Now window is VALID (<= k distinct characters)
            //    Update maximum window size
            if (map.size() <= k) {
                ans = Math.max(ans, r - l + 1);
            }

            r++; // Expand window further
        }
        return ans;
    }
}
Time Complexity:O(N), where N is the size of the array. As the other while loop runs for N times only. Ignore the contribution of map data structure in the time complexity as size of the map is extremely small.
Space Complexity: O(k) , as at most the map data structure is holding k elements.
```