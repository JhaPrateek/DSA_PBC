### A software engineer is tasked with using the shortest job first (SJF) policy to calculate the average waiting time for each process. The shortest job first also known as shortest job next (SJN) scheduling policy selects the waiting process with the least execution time to run next. You are given an array of integers bt of size n representing the burst times (execution times) of n processes. Your task is to calculate the average waiting time for all processes when scheduled using the SJF policy. The waiting time of a process is the total time a process has to wait before its execution starts, which is the sum of burst times of all previously executed processes. Return the floor of the average waiting time, i.e., the largest whole number less than or equal to the actual average.

```
Example 1
Input : bt = [4, 1, 3, 7, 2]
Output : 4
Explanation : The total waiting time is 20.
So the average waiting time will be 20/5 => 4.

Example 2
Input : bt = [1, 2, 3, 4]
Output : 2
Explanation : The total waiting time is 10.
So the average waiting time will be 10/4 => 2.
```

```java
class Solution {
    public long solve(int[] bt) {

        int n = bt.length;

        // Step 1: Sort burst times (Shortest Job First scheduling)
        Arrays.sort(bt);

        long totalWaitingTime = 0;  // stores sum of waiting times
        long currentTime = 0;       // cumulative execution time so far

        // Step 2: Calculate waiting time for each process
        for (int i = 0; i < n; i++) {

            // currentTime represents total burst time
            // of all previously executed processes
            totalWaitingTime += currentTime;

            // add current process burst time
            // to be used as waiting time for next processes
            currentTime += bt[i];
        }

        // Step 3: Return average waiting time (floor value)
        return totalWaitingTime / n;
    }
}
Time Complexity
O(n log n) due to the Arrays.sort() method, plus O(n) for the loop.
Space Complexity
O(1) as the algorithm uses a constant amount of extra space.
```