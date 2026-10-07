package analyzer;

import java.util.Scanner;

public class StackOperations {
    private int[] stack;
    private int top;
    private int capacity;

    public StackOperations(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.top = -1;
    }

    public void push(int value) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow! Cannot push " + value);
            return;
        }
        stack[++top] = value;
        System.out.println("Pushed " + value + " to stack.");
    }

    public void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow! Stack is empty.");
            return;
        }
        System.out.println("Popped " + stack[top--] + " from stack.");
    }

    public void peek() {
        if (top == -1) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Top element: " + stack[top]);
    }

    public void display() {
        if (top == -1) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.print("Stack elements (top to bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + " ");
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
            System.out.println("\n--- STACK OPERATIONS MENU ---");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");

            int choice = readInt(sc, "Enter choice: ");

            switch (choice) {
                case 1:
                    int val = readInt(sc, "Enter value to push: ");
                    push(val);
                    break;
                case 2:
                    pop();
                    break;
                case 3:
                    peek();
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