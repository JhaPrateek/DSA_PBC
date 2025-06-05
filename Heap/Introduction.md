# Heap Data Structure Notes (Java)

## Introduction to Heap

A **Heap** is a **complete binary tree** that satisfies the **heap property**. It is commonly used in algorithms like **Heap Sort** and for implementing **Priority Queues**.

### Complete Binary Tree
A binary tree where all levels except possibly the last are fully filled, and all nodes in the last level are as far left as possible.

### Heap Property
- **Max-Heap**: Every node’s value is greater than or equal to its children.
- **Min-Heap**: Every node’s value is less than or equal to its children.

### Array Representation
Heaps are typically implemented using arrays. For a node at index `i`:
- **Left child**: `2i + 1`
- **Right child**: `2i + 2`
- **Parent**: `(i - 1) / 2`

---

## 1. Heapify

**Heapify** is the process of rearranging a binary tree to maintain the heap property. Used for building a heap from an array or restoring the heap property after insertion or deletion.

### Algorithm (Max-Heapify)
1. Start at node `i` (subtrees are assumed to be heaps).
2. Compare with left and right children.
3. Swap with the largest if necessary.
4. Recursively heapify affected subtree.

### Example
```
Array: [4, 10, 3, 5, 1]
Index 0: Value 4, Left: 10, Right: 3
Swap with 10 → [10, 4, 3, 5, 1]
```

### Code
```java
public class Heap {
    public void maxHeapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, n, largest);
        }
    }
}
```

**Time Complexity**: `O(log n)`  
**Space Complexity**: `O(log n)` (recursive call stack)

---

## 2. Heap Sort

Heap Sort uses a **max-heap** to sort elements in ascending order.

### Algorithm
1. Build max-heap.
2. Extract the root repeatedly and place it at the end.
3. Reduce heap size and heapify root.

### Example
```
Input: [12, 11, 13, 5, 6, 7]
Build max-heap → [13, 11, 12, 5, 6, 7]
Sort → [5, 6, 7, 11, 12, 13]
```

### Code
```java
public class HeapSort {
    public void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            maxHeapify(arr, i, 0);
        }
    }

    private void maxHeapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        HeapSort hs = new HeapSort();
        hs.heapSort(arr);
        System.out.println("Sorted array: " + java.util.Arrays.toString(arr));
    }
}
```

**Time Complexity**:  
- Build Heap: `O(n)`  
- Heapify `n` times: `O(n log n)`  
- **Total**: `O(n log n)`

**Space Complexity**: `O(1)`

---

## 3. Insertion in Heap

### Algorithm (Max-Heap)
1. Append element to end of array.
2. Compare with parent and swap if larger.
3. Repeat until heap property restored.

### Example
```
Insert 15 into [10, 5, 3, 2, 4]
→ [15, 5, 10, 2, 4, 3]
```

### Code
```java
public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    public void insert(int value) {
        if (size >= capacity) {
            throw new IllegalStateException("Heap is full");
        }

        heap[size] = value;
        int current = size++;
        while (current > 0 && heap[current] > heap[(current - 1) / 2]) {
            int parent = (current - 1) / 2;
            int temp = heap[current];
            heap[current] = heap[parent];
            heap[parent] = temp;
            current = parent;
        }
    }
}
```

**Time Complexity**: `O(log n)`  
**Space Complexity**: `O(1)`

---

## 4. Deletion in Heap

### Algorithm (Max-Heap)
1. Replace root with last element.
2. Decrease heap size.
3. Heapify the root.

### Example
```
[16, 10, 8, 5, 4] → Remove 16 → [4, 10, 8, 5] → Heapify → [10, 4, 8, 5]
```

### Code
```java
public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    public int deleteMax() {
        if (size == 0) throw new IllegalStateException("Heap is empty");

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        maxHeapify(heap, size, 0);
        return max;
    }

    private void maxHeapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, n, largest);
        }
    }
}
```

**Time Complexity**: `O(log n)`  
**Space Complexity**: `O(1)`

---

## 5. Priority Queue Using Heap

A **Priority Queue** allows insertion and extraction of elements based on priority. A **max-heap** gives highest value as top priority.

### Operations
- **Insert**: `O(log n)`
- **Extract-Max**: `O(log n)`
- **Peek**: `O(1)`

### Code
```java
public class PriorityQueueMaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public PriorityQueueMaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    public void insert(int value) {
        if (size >= capacity) throw new IllegalStateException("Heap is full");

        heap[size] = value;
        int current = size++;
        while (current > 0 && heap[current] > heap[(current - 1) / 2]) {
            int parent = (current - 1) / 2;
            int temp = heap[current];
            heap[current] = heap[parent];
            heap[parent] = temp;
            current = parent;
        }
    }

    public int extractMax() {
        if (size == 0) throw new IllegalStateException("Heap is empty");

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        maxHeapify(heap, size, 0);
        return max;
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        return heap[0];
    }

    private void maxHeapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        PriorityQueueMaxHeap pq = new PriorityQueueMaxHeap(10);
        pq.insert(3);
        pq.insert(8);
        pq.insert(10);
        pq.insert(4);

        System.out.println("Max element: " + pq.extractMax()); // 10
        System.out.println("New max: " + pq.peek());            // 8
    }
}
```

**Time Complexity**:
- Insert: `O(log n)`
- Extract-Max: `O(log n)`
- Peek: `O(1)`

**Space Complexity**: `O(n)`

---

## Summary Table

| Operation        | Time Complexity | Space Complexity |
|------------------|------------------|------------------|
| Heapify          | `O(log n)`       | `O(log n)`       |
| Heap Sort        | `O(n log n)`     | `O(1)`           |
| Insertion        | `O(log n)`       | `O(1)`           |
| Deletion         | `O(log n)`       | `O(1)`           |
| Priority Queue   | `O(log n)`       | `O(n)`           |
