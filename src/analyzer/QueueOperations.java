package analyzer;

public class QueueOperations {
    private int[] queueArray;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public QueueOperations() {
        this(10);
    }

    public QueueOperations(int capacity) {
        this.capacity = capacity;
        this.queueArray = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("[Error] Queue Overflow! Cannot enqueue " + value);
            return;
        }
        rear = (rear + 1) % capacity;
        queueArray[rear] = value;
        size++;
        System.out.println("[Success] Enqueued " + value + " to queue.");
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("[Error] Queue Underflow! Cannot dequeue from empty queue.");
            return -1;
        }
        int dequeuedValue = queueArray[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println("[Success] Dequeued " + dequeuedValue + " from queue.");
        return dequeuedValue;
    }

    public int peekFront() {
        if (isEmpty()) {
            System.out.println("[Error] Queue is empty!");
            return -1;
        }
        return queueArray[front];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("[Info] Queue is empty.");
            return;
        }
        System.out.print("Queue (Front to Rear): ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(queueArray[index] + (i == size - 1 ? "" : " <- "));
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}