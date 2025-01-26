//Why we are using PQ(min heap) ->  bcz we want shortest distance to always be at top.

//Why Pq instead of queue -> We use a Priority Queue (PQ) instead of a regular Queue in Dijkstra's algorithm because the algorithm relies on always processing 
//                           the node with the smallest distance first. This behavior cannot be achieved with a regular queue.

//TC- O(E log V) -> Each insertion and deletion operation in the priority queue takes O(log V) 
//                  Each edge contributes O(log V) for the priority queue operations.
//                  There are E edges in the graph.
//                  Hence, the total complexity due to edge relaxation is O(E log V).



class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
class Solution {
   
    // Function to implement Dijkstra's algorithm
    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        int V = adj.size(); // Number of vertices in the graph

        // Distance array to store the shortest path distances
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            ans.add((int) 1e9); // Initialize distances as "infinity" (1e9)
        }

        // Priority queue to get the minimum distance node (min-heap)
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);

        // Add the source node to the priority queue with distance 0
        pq.offer(new Pair(0, src));
        ans.set(src, 0); // Distance to the source is always 0

        // Start processing the graph
        while (!pq.isEmpty()) {
            Pair p = pq.poll(); // Extract the node with the minimum distance
            int dist = p.distance; // Distance of the current node
            int node = p.node;     // Current node

            // Iterate over all neighbors of the current node
            for (iPair x : adj.get(node)) {
                int cur_node = x.first;   // Neighbor node
                int cur_dis = x.second;   // Weight of the edge

                // Relaxation step: Update the distance if a shorter path is found
                if (dist + cur_dis < ans.get(cur_node)) {
                    ans.set(cur_node, dist + cur_dis); // Update the distance
                    pq.offer(new Pair(dist + cur_dis, cur_node)); // Add the updated node to the queue
                }
            }
        }
        return ans; // Return the shortest path distances
    }
}

// Helper class to store distance and node pairs for the priority queue
class Pair {
    int distance; // Distance to the node
    int node;     // Node itself

    Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }

