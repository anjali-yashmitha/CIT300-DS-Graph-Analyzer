package analyzer;
import java.util.Scanner;

public class ArrayOperations {

    private int[] data;      // the array that stores the numbers
    private int size;        // how many numbers are stored now
    private final int CAPACITY = 20;

    public ArrayOperations() {
        data = new int[CAPACITY];
        size = 0;
    }

    // Insert a value at a position (0 to size). O(n) because items may shift right.
    public boolean insert(int position, int value) {
        if (size == CAPACITY) {
            System.out.println("Array is full. Cannot insert.");
            return false;
        }
        if (position < 0 || position > size) {
            System.out.println("Invalid position. Use 0 to " + size + ".");
            return false;
        }
        for (int i = size; i > position; i--) {
            data[i] = data[i - 1];
        }
        data[position] = value;
        size++;
        System.out.println("Inserted " + value + " at position " + position + ".");
        return true;
    }

    // Delete the value at a position. O(n) because items shift left.
    public boolean delete(int position) {
        if (size == 0) {
            System.out.println("Array is empty. Nothing to delete.");
            return false;
        }
        if (position < 0 || position >= size) {
            System.out.println("Invalid position. Use 0 to " + (size - 1) + ".");
            return false;
        }
        int removed = data[position];
        for (int i = position; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        System.out.println("Deleted " + removed + " from position " + position + ".");
        return true;
    }

    // Search for a value with a simple loop. Returns the position or -1.
    public int search(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // Show all values in the array.
    public void display() {
        if (size == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println("(size " + size + " of " + CAPACITY + ")");
    }

    // Other classes need a copy of the data (for searching and performance tests).
    public int[] toArray() {
        int[] copy = new int[size];
        for (int i = 0; i < size; i++) {
            copy[i] = data[i];
        }
        return copy;
    }

    public int getSize() {
        return size;
    }

    // Read a whole number safely (no crash if the user types letters).
    private int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String text = sc.nextLine().trim();
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    // The Array submenu. Main.java will call this.
    public void showMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int value = readInt(sc, "Enter value to insert: ");
                    int pos = readInt(sc, "Enter position (0 to " + size + "): ");
                    insert(pos, value);
                    break;
                case 2:
                    int delPos = readInt(sc, "Enter position to delete: ");
                    delete(delPos);
                    break;
                case 3:
                    int target = readInt(sc, "Enter value to search: ");
                    int found = search(target);
                    if (found == -1) {
                        System.out.println(target + " was not found.");
                    } else {
                        System.out.println(target + " found at position " + found + ".");
                    }
                    break;
                case 4:
                    display();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 to 5.");
            }
        } while (choice != 5);
    }
}
