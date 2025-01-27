// Question - Shortest Path in Weighted undirected graph (GeeksForGeeks)

/*
You are given a weighted undirected graph having n vertices numbered from 1 to n and m edges along with their weights. Find the shortest weight path between the vertex 1 and the vertex n,
if there exists a path, and return a list of integers whose first element is the weight of the path, and the rest consist of the nodes on that path. If no path exists, then return a
list containing a single element -1.
The input list of edges is as follows - {a, b, w}, denoting there is an edge between a and b, and w is the weight of that edge.


Input: n = 5, m= 6, edges = [[1, 2, 2], [2, 5, 5], [2, 3, 4], [1, 4, 1], [4, 3, 3], [3, 5, 1]]
Output: 5
Explanation: Shortest path from 1 to n is by the path 1 4 3 5 whose weight is 5. 
*/

class Solution {
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        // Initialize an adjacency list to represent the graph
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>()); // Create an empty list for each node
        }
        
        // Fill the adjacency list with edges
        for (int i = 0; i < m; i++) {
            // Since it's an undirected graph, add edges in both directions
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2])); // Add (destination, weight)
            adj.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2])); // Add (source, weight)
        }

        // Priority Queue (min-heap) to get the node with the shortest distance
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> {
            if (x.dist != y.dist) {
                return x.dist - y.dist; // Sort by distance
            }
            return x.node - y.node; // If distances are equal, sort by node
        });

        // Initialize parent and distance arrays
        int parent[] = new int[n + 1]; // To store the parent of each node in the path
        int minDist[] = new int[n + 1]; // To store the minimum distance from node 1 to each node
        for (int i = 0; i <= n; i++) {
            parent[i] = i; // Initially, each node is its own parent
            minDist[i] = (int) 1e9; // Set initial distances to a very large value
        }

        // Start Dijkstra's algorithm from node 1
        pq.offer(new Pair(1, 0)); // Add the starting node with distance 0
        minDist[1] = 0; // Distance to itself is 0

        while (!pq.isEmpty()) {
            Pair p = pq.poll(); // Get the node with the smallest distance
            int node = p.node;
            int dist = p.dist;

            // Traverse all adjacent nodes
            for (Pair x : adj.get(node)) {
                int cur_node = x.node; // Adjacent node
                int cur_dist = x.dist; // Weight of the edge
                // If a shorter path is found
                if (dist + cur_dist < minDist[cur_node]) {
                    pq.offer(new Pair(cur_node, dist + cur_dist)); // Add updated distance to the priority queue
                    parent[cur_node] = node; // Update parent to reconstruct the path later
                    minDist[cur_node] = dist + cur_dist; // Update the shortest distance
                }
            }
        }

        // Prepare the result
        List<Integer> ans = new ArrayList<>();
        if (minDist[n] == 1e9) { // If the destination node is not reachable
            ans.add(-1);
            return ans;
        }

        // Reconstruct the shortest path using the parent array
        int node = n; // Start from the destination node
        while (parent[node] != node) {                 // Go back through the parent array until reaching the source
            ans.add(node);                         // Add the current node to the path
            node = parent[node]; // Move to the parent node
        }
        ans.add(1); // Add the source node to the path
        Collections.reverse(ans); // Reverse the path to make it source to destination

        ans.add(0, minDist[n]); // Add the shortest distance as the first element in the result
        return ans; // Return the result
    }
}

class Pair {
    int node; // The current node
    int dist; // Distance from the source node

    Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}
