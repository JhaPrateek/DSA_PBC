### Design a data structure that follows the constraints of Least Recently Used (LRU) cache.Implement the LRUCache class:

LRUCache(int capacity): We need to initialize the LRU cache with positive size capacity.

int get(int key): Returns the value of the key if the key exists, otherwise return -1.

void put(int key,int value): Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.

The functions get and put must each run in O(1) average time complexity.

Examples:
Input:

 ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]

 [ [2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4] ]

Output:

 [null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation:

LRUCache lRUCache = new LRUCache(2);

lRUCache.put(1, 1); // cache is {1=1}

lRUCache.put(2, 2); // cache is {1=1, 2=2}

lRUCache.get(1);  // return 1

lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}

lRUCache.get(2);  // returns -1 (not found)

lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}

lRUCache.get(1);  // return -1 (not found)

lRUCache.get(3);  // return 3

lRUCache.get(4);  // return 4

Input:

["LRUCache", "put", "put", "get", "put", "get", "put", "get"]

[[1], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [3]]

Output:

[null, null, null, -1, null, -1, null, -1]

Explanation:

LRUCache lRUCache = new LRUCache(1);

lRUCache.put(1, 1); // cache is {1=1}

lRUCache.put(2, 2); // evicts key 1, cache is {2=2}

lRUCache.get(1);  // returns -1 (not found)

lRUCache.put(3, 3); // evicts key 2, cache is {3=3}

lRUCache.get(2);  // returns -1 (not found)

lRUCache.put(4, 4); // evicts key 3, cache is {4=4}

lRUCache.get(3);  // returns -1 (not found)

```java
class LRUCache {
    
    // List to store cache elements in LRU order
    // Least Recently Used → at index 0
    // Most Recently Used → at last index
    List<Pair> list;
    
    // Maximum capacity of the cache
    int size;
    
    // Constructor to initialize cache capacity
    public LRUCache(int capacity) {
        list = new ArrayList<>();
        size = capacity;
    }
    
    // Function to get value of a key
    public int get(int key) {
        
        // Traverse the list to find the key
        for (int i = 0; i < list.size(); i++) {
            
            // If key is found
            if (list.get(i).key == key) {
                
                // Store the pair temporarily
                Pair p = list.get(i);
                
                // Remove it from its current position
                // (because it is now recently used)
                list.remove(i);
                
                // Add it at the end (most recently used)
                list.add(p);
                
                // Return the value
                return p.val;
            }
        }
        
        // If key is not found, return -1
        return -1;
    }
    
    // Function to insert/update a key-value pair
    public void put(int key, int value) {
        
        boolean alreadyPresent = false;
        
        // Check if key already exists
        for (int i = 0; i < list.size(); i++) {
            
            if (list.get(i).key == key) {
                
                // If key exists, remove old entry
                Pair p = list.get(i);
                list.remove(i);
                
                // Insert updated value at the end (most recently used)
                list.add(new Pair(key, value));
                
                alreadyPresent = true;
                return;
            }
        }
        
        /*
         If key is NOT present and
         cache is FULL → remove least recently used element
         which is at index 0
        */
        if (alreadyPresent == false && size == list.size()) {
            list.remove(0);  // Remove LRU element
            list.add(new Pair(key, value)); // Add new element as MRU
        }
        
        /*
         If key is NOT present and
         cache is NOT full → directly insert
        */
        else {
            list.add(new Pair(key, value));
        }
    }
}

// Helper class to store key-value pairs
class Pair {
    int key, val;
    
    Pair(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
Time Complexity:The time complexity of both `get` and `put` methods is O(n) due to the linear search through the list. Initializing the ArrayList takes O(1) time.
Space Complexity:The space complexity is O(capacity) because the list stores at most 'capacity' number of elements.
```

```java
// Doubly Linked List Node
class Node {
    public int key, val;      // Stores key-value pair
    public Node next, prev;   // Pointers for DLL

    // Default constructor (used for dummy head & tail)
    Node() {
        key = val = -1;
        next = prev = null;
    }

    // Parameterized constructor
    Node(int k, int value) {
        key = k;
        val = value;
        next = prev = null;
    }
}

// Class implementing LRU cache
class LRUCache {

    // HashMap for O(1) access to cache elements
    private Map<Integer, Node> mpp;

    // Maximum capacity of the cache
    private int cap;

    // Dummy head and tail nodes to avoid null checks
    private Node head;
    private Node tail;

    /*
     Private helper method to delete a node from
     the doubly linked list in O(1)
    */
    private void deleteNode(Node node) {

        // Store previous and next nodes
        Node prevNode = node.prev;
        Node nextNode = node.next;

        // Bypass the current node
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    /*
     Private helper method to insert a node
     right after the head (Most Recently Used)
    */
    private void insertAfterHead(Node node) {

        // Node that is currently after head
        Node nextNode = head.next;

        // Insert new node after head
        head.next = node;
        nextNode.prev = node;

        // Update node pointers
        node.prev = head;
        node.next = nextNode;
    }

    // Constructor to initialize LRU Cache with capacity
    public LRUCache(int capacity) {
        cap = capacity;             // Set capacity
        mpp = new HashMap<>();      // Initialize HashMap

        // Create dummy head and tail
        head = new Node();
        tail = new Node();

        // Connect head and tail
        head.next = tail;
        tail.prev = head;
    }

    // Method to retrieve a value from cache
    public int get(int key_) {

        // If key is not present, return -1
        if (!mpp.containsKey(key_))
            return -1;

        // Fetch the corresponding node
        Node node = mpp.get(key_);
        int val = node.val;

        /*
         Since it is accessed now,
         move it to front (MRU position)
        */
        deleteNode(node);
        insertAfterHead(node);

        return val;
    }

    /*
     Method to insert a new key-value pair
     or update value if key already exists
    */
    public void put(int key_, int value) {

        // If key already exists in cache
        if (mpp.containsKey(key_)) {

            // Get the existing node
            Node node = mpp.get(key_);

            // Update value
            node.val = value;

            /*
             Move this node to front
             as it becomes most recently used
            */
            deleteNode(node);
            insertAfterHead(node);

            return;
        }

        /*
         If cache is full, remove the
         Least Recently Used (LRU) node
         which is at tail.prev
        */
        if (mpp.size() == cap) {

            Node node = tail.prev;

            // Remove from hashmap
            mpp.remove(node.key);

            // Remove from doubly linked list
            deleteNode(node);
        }

        // Create new node for new key-value pair
        Node newNode = new Node(key_, value);

        // Store reference in HashMap
        mpp.put(key_, newNode);

        // Insert node at front (MRU position)
        insertAfterHead(newNode);
    }
}
Time Complexity:The time complexity for all the operations get and put is O(1) because HashMap and doubly linked list operations take constant time.
Space Complexity:The space complexity is O(capacity) because the HashMap and doubly linked list store at most 'capacity' number of nodes.
```

### ✅ LRU Cache Intuition

--> LRU Cache removes the Least Recently Used item first.
--> Every time a key is accessed or updated, it becomes Most Recently Used.
--> We must always know the usage order of elements.
--> We must also be able to find any key instantly.
--> When cache is full, the oldest unused item is removed.
--> The most recently used item is always kept at the front
--> The least recently used item is kept at the end.
--> get() should return value in O(1) time.
--> put() should insert/update in O(1) time.
--> So we need fast lookup + fast delete + fast insert.

--> HashMap gives O(1) access, and Doubly Linked List gives O(1) deletion and ordering, together making get() and put() both run in O(1) time.