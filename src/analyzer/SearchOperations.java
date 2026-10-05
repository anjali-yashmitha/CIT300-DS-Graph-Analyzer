package analyzer;

import java.util.Arrays;
import java.util.Scanner;

public class SearchOperations {

    private int lastSteps = 0;   // number of comparisons in the last search

    // Linear search: check every item from left to right. O(n)
    public int linearSearch(int[] arr, int target) {
        lastSteps = 0;
        for (int i = 0; i < arr.length; i++) {
            lastSteps++;                 // one comparison
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Binary search: the array MUST be sorted. Cut the search range in half each time. O(log n)
    public int binarySearch(int[] arr, int target) {
        lastSteps = 0;
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            lastSteps++;                 // one comparison
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int getLastSteps() {
        return lastSteps;
    }

    // Read a whole number safely.
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

    private void printResult(String name, int index, int target) {
        if (index == -1) {
            System.out.println(name + ": " + target + " not found. Steps: " + lastSteps);
        } else {
            System.out.println(name + ": " + target + " found at index " + index
                    + ". Steps: " + lastSteps);
        }
    }

    // The Searching submenu. Main.java will call this and pass the array object.
    public void showMenu(Scanner sc, ArrayOperations arrayOps) {
        int choice;
        do {
            System.out.println("\n--------------- SEARCHING OPERATIONS ---------------");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");
            System.out.println("3. Compare Linear and Binary Search");
            System.out.println("4. Return to Main Menu");
            choice = readInt(sc, "Enter your choice: ");

            if (choice >= 1 && choice <= 3 && arrayOps.getSize() == 0) {
                System.out.println("The array is empty. Add values in Array Operations first.");
                continue;
            }

            // Binary search needs sorted data, so we sort a COPY of the array.
            int[] original = arrayOps.toArray();
            int[] sorted = original.clone();
            Arrays.sort(sorted);

            switch (choice) {
                case 1:
                    int t1 = readInt(sc, "Enter value to search: ");
                    int r1 = linearSearch(original, t1);
                    printResult("Linear Search", r1, t1);
                    break;
                case 2:
                    int t2 = readInt(sc, "Enter value to search: ");
                    System.out.println("Sorted copy used: " + Arrays.toString(sorted));
                    int r2 = binarySearch(sorted, t2);
                    printResult("Binary Search", r2, t2);
                    break;
                case 3:
                    int t3 = readInt(sc, "Enter value to search: ");
                    int a = linearSearch(original, t3);
                    int linearSteps = lastSteps;
                    int b = binarySearch(sorted, t3);
                    int binarySteps = lastSteps;
                    System.out.println("Linear Search steps: " + linearSteps
                            + (a == -1 ? " (not found)" : " (found)"));
                    System.out.println("Binary Search steps: " + binarySteps
                            + (b == -1 ? " (not found)" : " (found)"));
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 to 4.");
            }
        } while (choice != 4);
    }
}