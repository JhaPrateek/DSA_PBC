/*
Given a n * m matrix grid where each element can either be 0 or 1. You need to find the shortest distance between a given source cell to a destination cell. The path can only be created out of a
cell if its value is 1. 
If the path is not possible between source cell and destination cell, then return -1.
Note : You can move into an adjacent cell if that adjacent cell is filled with element 1. Two cells are adjacent if they share a side. In other words, you can move in one of the four directions,
Up, Down, Left and Right. The source and destination cell are based on the zero based indexing. The destination cell should be 1.


Input:
grid[][] = {{1, 1, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {1, 0, 0, 1}}
source = {0, 1}
destination = {2, 2}

Output:
3
Explanation:
1 1 1 1
1 1 0 1
1 1 1 1
1 1 0 0
1 0 0 1
The highlighted part in the matrix denotes the 
shortest path from source to destination cell.
*/


class Solution {

    int shortestPath(int[][] grid, int[] source, int[] destination) {
        // If the source is the same as the destination, the shortest path is 0
        if (source[0] == destination[0] && source[1] == destination[1]) {
            return 0;
        }

        // Get dimensions of the grid
        int m = grid.length;    // Number of rows
        int n = grid[0].length; // Number of columns

        // Arrays to represent directions for moving right, up, left, and down
        int row[] = {0, -1, 0, 1};
        int col[] = {1, 0, -1, 0};

        // Initialize minDist matrix to track the shortest distance to each cell
        int minDist[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minDist[i][j] = (int) 1e9; // Set all distances to a large value (infinity)
            }
        }

        // Queue for BFS, storing pairs of distance, row, and column
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, source[0], source[1])); // Start BFS from the source
        minDist[source[0]][source[1]] = 0; // Distance to the source is 0

        // Perform BFS to find the shortest path
        while (!q.isEmpty()) {
            Pair p = q.poll(); // Dequeue the current cell
            int dist = p.dist; // Current distance
            int r = p.row;     // Current row
            int c = p.col;     // Current column

            // Explore all 4 possible directions (right, up, left, down)
            for (int i = 0; i < 4; i++) {
                int new_row = r + row[i];
                int new_col = c + col[i];

                // Check if the new cell is valid and accessible (within bounds and has value 1)
                if (new_row >= 0 && new_col >= 0 && new_row < m && new_col < n && grid[new_row][new_col] == 1) {
                    
                    // If we reach the destination, return the shortest distance
                    if (new_row == destination[0] && new_col == destination[1]) {
                        return dist + 1;
                    }

                    // If the new cell has not been visited (minDist is still set to infinity)
                    if (minDist[new_row][new_col] == 1e9) {
                        minDist[new_row][new_col] = dist + 1; // Update the distance
                        q.offer(new Pair(dist + 1, new_row, new_col)); // Add the new cell to the queue
                    }
                }
            }
        }

        // If we exit the loop without finding the destination, return -1 (no path exists)
        return -1;
    }
}

class Pair {
    int dist; // Distance from the source
    int row;  // Row index
    int col;  // Column index

    Pair(int dist, int row, int col) {
        this.dist = dist;
        this.row = row;
        this.col = col;
    }
}
