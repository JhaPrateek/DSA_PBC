### Given the head of a singly linked list. Group all the nodes with odd indices followed by all the nodes with even indices and return the reordered list. Consider the 1st node to have index 1 and so on. The relative order of the elements inside the odd and even group must remain the same as the given input.

```
Example 1
Input: linkedList = [1, 2, 3, 4, 5]
Output: [1, 3, 5, 2, 4]
Explanation:
The nodes with odd indices are 1, 3, 5 and the ones with even indices are 2, 4.

Example 2
Input: linkedList = [4, 3, 2, 1]
Output: [4, 2, 3, 1]
Explanation:
The nodes with odd indices are 4, 2 and the ones with even indices are 3, 1.
```
```java
class Solution {
    // Function to segregate odd and even indices nodes
    public ListNode oddEvenList(ListNode head) {
        // Check if list is empty or has only one node
        if (head == null || head.next == null)
            return head;

        // To store values
        List<Integer> array = new ArrayList<>();   
        ListNode temp = head;

        /*Traverse the list, skipping one node, 
        and store values in the vector*/
        while (temp != null && temp.next != null) {
            array.add(temp.data);
            temp = temp.next.next;
        }

        /* If the traversal ends on a valid 
        odd-indexed node, include its value as well*/
        if (temp != null)
            array.add(temp.data);

        // Reset temp 
        temp = head.next;

        /*Traverse the list again, skipping one node,
         and store values 
        in the vector*/
        while (temp != null && temp.next != null) {
            array.add(temp.data);
            temp = temp.next.next;
        }

       /* If the traversal ends on a valid 
        even-indexed node, include its value as well */
        if (temp != null)
            array.add(temp.data);

        // Reset temp 
        temp = head;
        int i = 0;

        // Update node values 
        while (temp != null) {
            temp.data = array.get(i);
            temp = temp.next;
            i++;
        }

        return head;
    }
}
Time Complexity: O(2xN) for the following reasons:-
Traversing the odd-indexed elements takes O(N/2) time.
Traversing the even-indexed elements takes O(N/2) time.
Updating the linked list with the values from the array takes O(N) time.
Here N is the size of the linked list.
Space Complexity: O(N) because an additional list is used to store the grouped elements from the linked list.
```
```java
Optimal
class Solution {
    // Function to rearrange nodes
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        /*Initialize pointers for odd 
        and even nodes and keep 
        track of the first even node*/
        ListNode odd = head;
        ListNode even = head.next;
        ListNode firstEven = head.next;

        // Rearranging nodes
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }

        /* Connect the last odd 
       node to the first even node*/
        odd.next = firstEven;

        return head;
    }
}
Time Complexity: O(N/2)x2 ~ O(N) because we are iterating over the odd-indexed as well as the even-indexed elements. Here N is the size of the given linked list.
Space Complexity: O(1) because we have not used any extra space.
```