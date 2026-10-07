package analyzer;

import java.util.Scanner;

public class QueueOperations {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public QueueOperations(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(int value) {
        if (size == capacity) {
            System.out.println("Queue Overflow! Cannot enqueue " + value);
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = value;
        size++;
        System.out.println("Enqueued " + value + " to queue.");
    }

    public void dequeue() {
        if (size == 0) {
            System.out.println("Queue Underflow! Queue is empty.");
            return;
        }
        System.out.println("Dequeued " + queue[front] + " from queue.");
        front = (front + 1) % capacity;
        size--;
    }

    public void peekFront() {
        if (size == 0) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Front element: " + queue[front]);
    }

    public void display() {
        if (size == 0) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue elements (front to rear): ");
        for (int i = 0; i < size; i++) {
            System.out.print(queue[(front + i) % capacity] + " ");
        }
        System.out.println();
    }

    // Helper method to validate integer input
    private int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine(); // clear buffer
                return value;
            } else {
                System.out.println("Invalid input! Please enter an integer.");
                sc.nextLine(); // clear invalid token
            }
        }
    }

    public void showMenu(Scanner sc) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- QUEUE OPERATIONS MENU ---");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek Front");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");

            int choice = readInt(sc, "Enter choice: ");

            switch (choice) {
                case 1:
                    int val = readInt(sc, "Enter value to enqueue: ");
                    enqueue(val);
                    break;
                case 2:
                    dequeue();
                    break;
                case 3:
                    peekFront();
                    break;
                case 4:
                    display();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid option. Choose between 1 and 5.");
            }
        }
    }
}