### Given an array of non-negative integers, height representing the elevation of ground. Calculate the amount of water that can be trapped after rain.


Examples:
Input: height= [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
Output: 6
Explanation: As seen from the diagram 1+1+2+1+1=6 unit of water can be trapped

Input: height= [4, 2, 0, 3, 2, 5]
Output: 9
Expalanation: 2+4+1+2=9 unit of water can be trapped

```java
import java.util.*;

class Solution {
    // Function to find the prefix maximum array
    private int[] findPrefixMax(int[] arr, int n) {
        // To store the prefix maximum
        int[] prefixMax = new int[n];
        
        // Initial configuration
        prefixMax[0] = arr[0];
        
        // Traverse the array
        for(int i = 1; i < n; i++) {
            // Store the maximum till ith index
            prefixMax[i] = 
                Math.max(prefixMax[i-1], arr[i]);
        }
        
        // Return the prefix maximum array
        return prefixMax;
    }
    
    // Function to find the suffix maximum array
    private int[] findSuffixMax(int[] arr, int n) {
        // To store the suffix maximum
        int[] suffixMax = new int[n];
        
        // Initial configuration
        suffixMax[n-1] = arr[n-1];
        
        // Traverse the array
        for(int i = n-2; i >= 0; i--) {
            // Store the maximum till ith index
            suffixMax[i] = 
                Math.max(suffixMax[i+1], arr[i]);
        }
        
        // Return the suffix maximum array
        return suffixMax;
    }

    // Function to get the trapped water
    public int trap(int[] height) {
        
        int n = height.length; // Size of array
    
        // To store the total trapped rainwater
        int total = 0;
        
        // Calculate prefix maximum array
        int[] leftMax = 
            findPrefixMax(height, n);
        
        // Calculate suffix maximum array
        int[] rightMax = 
            findSuffixMax(height, n);
        
        // Traverse the array
        for(int i = 0; i < n; i++) {
            
            /* If there are higher grounds 
            on both side to hold water */
            if(height[i] < leftMax[i] && 
               height[i] < rightMax[i]) {
                   
                // Add up the water on top of current height
                total += ( Math.min(leftMax[i], rightMax[i]) 
                           - height[i] );
            }
        }
        
        // Return the result
        return total;
    }

    public static void main(String[] args) {
        int[] heights = {4, 2, 0, 3, 2, 5};
        
        // Creating an instance of Solution class
        Solution sol = new Solution();
        
        // Function call to get the trapped water
        int ans = sol.trap(heights);
        
        System.out.println("The trapped rainwater is: " + ans);
    }
}
Time Complexity: O(N) (where N is the size of given array)
Calculating the Prefix and Suffix Maximum Arrays take O(N) time each.
Traversing on the given array once takes O(N) time.

Space Complexity: O(N)
Storing the Prefix and Suffix Maximum Arrays takes O(N) space each.
```

```java
class Solution {
    public int trap(int[] height) {
        
        int n = height.length;  
        // Total number of bars
        
        int left = 0;  
        // Left pointer starts from beginning
        
        int right = n - 1;  
        // Right pointer starts from end
        
        int leftMax = height[0];  
        // Maximum height seen so far from the left
        
        int rightMax = height[n - 1];  
        // Maximum height seen so far from the right
        
        int ans = 0;  
        // This will store the total trapped water
        
        // Continue until both pointers meet
        while (left < right) {
            
            // If left bar is smaller, it limits water trapping
            if (height[left] <= height[right]) {
                
                // If current left height is less than leftMax,
                // then water can be trapped above it
                if (height[left] < leftMax) {
                    ans += leftMax - height[left];  
                    // Add trapped water at this position
                } 
                // Otherwise update leftMax
                else {
                    leftMax = height[left];
                }
                
                left++;  
                // Move left pointer to the right
            } 
            // If right bar is smaller, it limits water trapping
            else {
                
                // If current right height is less than rightMax,
                // then water can be trapped above it
                if (height[right] < rightMax) {
                    ans += rightMax - height[right];  
                    // Add trapped water at this position
                } 
                // Otherwise update rightMax
                else {
                    rightMax = height[right];
                }
                
                right--;  
                // Move right pointer to the left
            }
        }
        
        return ans;  
        // Return total trapped rainwater
    }
}
Time Complexity (TC):  O(n)
Each bar is processed once.

Space Complexity (SC): O(1)
No extra array used, only variables.
```