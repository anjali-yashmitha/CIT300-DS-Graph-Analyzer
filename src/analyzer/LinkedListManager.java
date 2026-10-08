package analyzer;

import java.util.Scanner;

public class LinkedListManager {
    private Node head;

    public LinkedListManager() {
        this.head = null;
    }

    // Insert method
    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Inserted " + data + " into the list.");
    }

    // Delete method (Handles empty list, first node, middle/end, and value not
    // found)
    public void delete(int data) {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }

        if (head.data == data) {
            head = head.next;
            System.out.println("Deleted " + data + " from the list.");
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Value " + data + " not found in the list.");
        } else {
            current.next = current.next.next;
            System.out.println("Deleted " + data + " from the list.");
        }
    }

    // Search method (Returns position or -1 if not found)
    public int search(int data) {
        Node current = head;
        int position = 0;
        while (current != null) {
            if (current.data == data) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    // Display method
    public void display() {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.print("Linked List: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // ShowMenu method with robust input validation (does not crash on letters)
    public void showMenu(Scanner sc) {
        int choice = 0;
        do {
            System.out.println("\n--- Linked List Operations Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice (1-5): ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter value to insert: ");
                        if (sc.hasNextInt()) {
                            int val = sc.nextInt();
                            insert(val);
                        } else {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            sc.next(); // clear invalid input
                        }
                        break;
                    case 2:
                        System.out.print("Enter value to delete: ");
                        if (sc.hasNextInt()) {
                            int val = sc.nextInt();
                            delete(val);
                        } else {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            sc.next();
                        }
                        break;
                    case 3:
                        System.out.print("Enter value to search: ");
                        if (sc.hasNextInt()) {
                            int val = sc.nextInt();
                            int pos = search(val);
                            if (pos != -1) {
                                System.out.println("Value " + val + " found at position: " + pos);
                            } else {
                                System.out.println("Value " + val + " not found in the list (-1).");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            sc.next();
                        }
                        break;
                    case 4:
                        display();
                        break;
                    case 5:
                        System.out.println("Returning to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // clear invalid input
            }
        } while (choice != 5);
        sc.nextLine(); // remove the leftover Enter
    }
}