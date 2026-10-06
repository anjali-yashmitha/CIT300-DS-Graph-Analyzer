package analyzer;

public class StackOperations {
    private int[] stackArray;
    private int top;
    private int capacity;

    public StackOperations() {
        this(10);
    }

    public StackOperations(int capacity) {
        this.capacity = capacity;
        this.stackArray = new int[capacity];
        this.top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("[Error] Stack Overflow! Cannot push " + value);
            return;
        }
        stackArray[++top] = value;
        System.out.println("[Success] Pushed " + value + " to stack.");
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("[Error] Stack Underflow! Cannot pop from an empty stack.");
            return -1;
        }
        int poppedValue = stackArray[top--];
        System.out.println("[Success] Popped " + poppedValue + " from stack.");
        return poppedValue;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("[Error] Stack is empty!");
            return -1;
        }
        return stackArray[top];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("[Info] Stack is empty.");
            return;
        }
        System.out.print("Stack (Top to Bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(stackArray[i] + (i == 0 ? "" : " -> "));
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }
}