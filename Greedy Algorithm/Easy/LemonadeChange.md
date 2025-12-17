### Each lemonade at a booth sells for $5. Consumers are lining up to place individual orders, following the billing order. Every consumer will purchase a single lemonade and may pay with a $5, $10, or $20 bill. Each customer must receive the appropriate change so that the net transaction is $5. Initially, there is no change available. Determine if it is possible to provide the correct change to every customer. Return true if the correct change can be given to every customer, and false otherwise. Given an integer array bills, where bills[i] is the bill the ith customer pays, return true if the correct change can be given to every customer, and false otherwise.

```
Example 1
Input : bills = [5, 5, 10, 5, 20]
Output : true

Explanation : Initially we have $0 available for change.
From first two customers, we will collect two $5 bills in order. After the first two customers we have two $5 bills available with us for change.
From the third customer , we collect bill of $10 and give back $5. After third customer we have one $5 and one $10 bill available with us for change.
From fourth customer , we collect $5 bill. After fourth customer we have two $5 and one $10 bills available with us for change if required.
From fifth customer , we collect bill of $20 and give back $15 (one $10 + one $5 bill).
Since all the customers did receive the change correctly , so we return true.

Example 2
Input : bills = [5, 5, 10, 10, 20]
Output : false

Explanation : From first two customers, we will collect two $5 bills in order. After the first two customers we have two $5 bills available with us for change.
From third customer , we collect $10 and give back $5. After the third customer we have one $5 and one $10 bill available with us for change.
From fourth customer , we collect $10 and give back $5. After the fourth customer we have two $10 bill available with us for change.
From fifth customer , we collect $20 , we cannot give the $15 change as we have two $10 bills.
Since all the customers did not receive the change correctly , the we return false.
```

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {

        // cnt5  -> number of $5 bills we currently have
        // cnt10 -> number of $10 bills we currently have
        int cnt5 = 0, cnt10 = 0;

        // Traverse each customer in order
        for (int i = 0; i < bills.length; i++) {

            // Case 1: Customer pays with $5
            // No change required, just collect the bill
            if (bills[i] == 5) {
                cnt5++;
            }

            // Case 2: Customer pays with $10
            // Change required = $5
            else if (bills[i] == 10) {

                // We must give one $5 as change
                if (cnt5 > 0) {
                    cnt5--;     // give $5 as change
                    cnt10++;    // keep the $10 bill
                } else {
                    // No $5 available → cannot give change
                    return false;
                }
            }

            // Case 3: Customer pays with $20
            // Change required = $15
            else if (bills[i] == 20) {

                // Preferred option:
                // Give one $10 + one $5 (preserves smaller bills)
                if (cnt10 > 0 && cnt5 > 0) {
                    cnt10--;
                    cnt5--;
                }

                // Alternative option:
                // Give three $5 bills
                else if (cnt5 >= 3) {
                    cnt5 -= 3;
                }

                // No valid combination to give $15 change
                else {
                    return false;
                }
            }

            // Invalid bill encountered
            else {
                return false;
            }
        }

        // If we successfully gave change to every customer
        return true;
    }
}

Time Complexity
The time complexity is O(n) because it iterates through the input array once.
Space Complexity
The space complexity is O(1) because it uses a fixed number of variables, regardless of the input size.
```