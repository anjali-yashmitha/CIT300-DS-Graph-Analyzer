package analyzer;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Performance / complexity demonstration.
 * Compares Linear Search with Binary Search, and BFS with DFS.
 * It records the number of steps (and the time for the searches).
 */
public class PerformanceComparison {

    // Last results, so "Display All Results" in the main menu can show them again.
    private String sizeResult = "";
    private String arrayResult = "";
    private String graphResult = "";

    // How many times a search is repeated to get a stable average time.
    private static final int REPEAT = 1000;

    // ---------------------------------------------------------------
    // 1. Search comparison on generated sorted arrays of different sizes
    // ---------------------------------------------------------------
    private void compareBySize() {
        SearchOperations search = new SearchOperations();
        int[] sizes = {10, 100, 1000, 10000, 100000};

        StringBuilder sb = new StringBuilder();
        sb.append("\n=====================================================================================\n");
        sb.append(" PERFORMANCE COMPARISON: LINEAR SEARCH vs BINARY SEARCH\n");
        sb.append(" (worst case for linear search: the target is the LAST item of a sorted array)\n");
        sb.append("=====================================================================================\n");
        sb.append(String.format("%-10s %-14s %-14s %-20s %-20s%n",
                "Size (n)", "Linear steps", "Binary steps", "Linear time (us)", "Binary time (us)"));
        sb.append("-------------------------------------------------------------------------------------\n");

        for (int n : sizes) {
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = i * 2;                  // sorted values 0, 2, 4, ...
            }
            int target = data[n - 1];             // the last value

            search.linearSearch(data, target);
            int linearSteps = search.getLastSteps();
            search.binarySearch(data, target);
            int binarySteps = search.getLastSteps();

            long start = System.nanoTime();
            for (int r = 0; r < REPEAT; r++) {
                search.linearSearch(data, target);
            }
            double linearTime = (System.nanoTime() - start) / (double) REPEAT / 1000.0;

            start = System.nanoTime();
            for (int r = 0; r < REPEAT; r++) {
                search.binarySearch(data, target);
            }
            double binaryTime = (System.nanoTime() - start) / (double) REPEAT / 1000.0;

            sb.append(String.format(Locale.US, "%-10d %-14d %-14d %-20.3f %-20.3f%n",
                    n, linearSteps, binarySteps, linearTime, binaryTime));
        }

        sb.append("=====================================================================================\n");
        sb.append(" Linear search is O(n): the steps grow in the same way as n.\n");
        sb.append(" Binary search is O(log n): the steps grow very slowly, but the array must be sorted.\n");
        sb.append(" The times change from computer to computer, but the number of steps is always the same.\n");

        sizeResult = sb.toString();
        System.out.print(sizeResult);
    }

    // ---------------------------------------------------------------
    // 2. Search comparison on the array that the user created
    // ---------------------------------------------------------------
    private void compareMyArray(Scanner sc, ArrayOperations arrayOps) {
        if (arrayOps.getSize() == 0) {
            System.out.println("\nThe array is empty. Add values in Array Operations first.");
            return;
        }
        SearchOperations search = new SearchOperations();
        int target = readInt(sc, "Enter the value to search for: ");

        int[] original = arrayOps.toArray();
        int[] sorted = original.clone();
        Arrays.sort(sorted);

        int linearIndex = search.linearSearch(original, target);
        int linearSteps = search.getLastSteps();
        int binaryIndex = search.binarySearch(sorted, target);
        int binarySteps = search.getLastSteps();

        StringBuilder sb = new StringBuilder();
        sb.append("\n=============================================================\n");
        sb.append(" PERFORMANCE COMPARISON: SEARCH ON YOUR ARRAY\n");
        sb.append("=============================================================\n");
        sb.append(" Array (as entered): ").append(Arrays.toString(original)).append("\n");
        sb.append(" Sorted copy:        ").append(Arrays.toString(sorted)).append("\n");
        sb.append(" Target value:       ").append(target).append("\n");
        sb.append("-------------------------------------------------------------\n");
        sb.append(String.format("%-12s %-18s %-8s %-20s%n", "Operation", "Algorithm", "Steps", "Result"));
        sb.append("-------------------------------------------------------------\n");
        sb.append(String.format("%-12s %-18s %-8d %-20s%n", "Search", "Linear Search", linearSteps,
                linearIndex == -1 ? "not found" : "found at index " + linearIndex));
        sb.append(String.format("%-12s %-18s %-8d %-20s%n", "Search", "Binary Search", binarySteps,
                binaryIndex == -1 ? "not found" : "found at index " + binaryIndex + " (sorted)"));
        sb.append("=============================================================\n");
        sb.append(" Binary search used the sorted copy, because it needs sorted data.\n");

        arrayResult = sb.toString();
        System.out.print(arrayResult);
    }

    // ---------------------------------------------------------------
    // 3. Graph traversal comparison: BFS and DFS
    // ---------------------------------------------------------------
    private void compareGraph(GraphOperations graph) {
        if (graph.isEmpty()) {
            graph.loadSampleGraph();
            System.out.println("\nThe graph was empty, so the sample graph (A to E) was loaded.");
        }
        String start = graph.getFirstVertex();
        System.out.println("\nStart vertex: " + start);

        int bfsSteps = graph.bfs(start);
        int dfsSteps = graph.dfs(start);

        StringBuilder sb = new StringBuilder();
        sb.append("\n=============================================================\n");
        sb.append(" PERFORMANCE COMPARISON: GRAPH TRAVERSAL (start vertex " + start + ")\n");
        sb.append("=============================================================\n");
        sb.append(String.format("%-18s %-12s %-8s%n", "Operation", "Algorithm", "Steps"));
        sb.append("-------------------------------------------------------------\n");
        sb.append(String.format("%-18s %-12s %-8d%n", "Graph Traversal", "BFS", bfsSteps));
        sb.append(String.format("%-18s %-12s %-8d%n", "Graph Traversal", "DFS", dfsSteps));
        sb.append("=============================================================\n");
        sb.append(" Both visit every reachable vertex once, so both are O(V + E).\n");
        sb.append(" The number of steps can be the same. Only the visiting order is different.\n");

        graphResult = sb.toString();
        System.out.print(graphResult);
    }

    // ---------------------------------------------------------------
    // Show the last saved results (used by "Display All Results")
    // ---------------------------------------------------------------
    public void showResults() {
        if (sizeResult.isEmpty() && arrayResult.isEmpty() && graphResult.isEmpty()) {
            System.out.println("No performance results yet. Run option 7 first.");
            return;
        }
        System.out.print(sizeResult);
        System.out.print(arrayResult);
        System.out.print(graphResult);
    }

    // ---------------------------------------------------------------
    // Menu
    // ---------------------------------------------------------------
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

    public void showMenu(Scanner sc, ArrayOperations arrayOps, GraphOperations graph) {
        int choice;
        do {
            System.out.println("\n--------------- PERFORMANCE COMPARISON ---------------");
            System.out.println("1. Linear vs Binary Search (different array sizes)");
            System.out.println("2. Linear vs Binary Search (my array)");
            System.out.println("3. BFS vs DFS (graph traversal)");
            System.out.println("4. Run all comparisons");
            System.out.println("5. Return to Main Menu");
            choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    compareBySize();
                    break;
                case 2:
                    compareMyArray(sc, arrayOps);
                    break;
                case 3:
                    compareGraph(graph);
                    break;
                case 4:
                    compareBySize();
                    compareMyArray(sc, arrayOps);
                    compareGraph(graph);
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