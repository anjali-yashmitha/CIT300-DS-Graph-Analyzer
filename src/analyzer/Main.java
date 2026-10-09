package analyzer;

import java.util.Scanner;

/**
 * Data Structure and Graph Performance Analyzer.
 * This class shows the main menu and connects all the components together.
 */
public class Main {

    // Reads a whole number safely (no crash when the user types letters).
    private static int readInt(Scanner sc, String message) {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // One object for each component. All menus share the same Scanner.
        ArrayOperations arrayOps = new ArrayOperations();
        StackOperations stackOps = new StackOperations(10);
        QueueOperations queueOps = new QueueOperations(10);
        LinkedListManager listOps = new LinkedListManager();
        SearchOperations searchOps = new SearchOperations();
        GraphOperations graphOps = new GraphOperations();
        PerformanceComparison performance = new PerformanceComparison();

        int choice;
        do {
            System.out.println("\n=============================================");
            System.out.println("     DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    arrayOps.showMenu(sc);
                    break;
                case 2:
                    stackOps.showMenu(sc);
                    break;
                case 3:
                    queueOps.showMenu(sc);
                    break;
                case 4:
                    listOps.showMenu(sc);
                    break;
                case 5:
                    searchOps.showMenu(sc, arrayOps);
                    break;
                case 6:
                    graphOps.showMenu(sc);
                    break;
                case 7:
                    performance.showMenu(sc, arrayOps, graphOps);
                    break;
                case 8:
                    displayAll(arrayOps, stackOps, queueOps, listOps, graphOps, performance);
                    break;
                case 9:
                    System.out.println("Thank you. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 to 9.");
            }
        } while (choice != 9);

        sc.close();
    }

    // Option 8: shows the current content of every component and the last performance results.
    private static void displayAll(ArrayOperations arrayOps, StackOperations stackOps,
                                   QueueOperations queueOps, LinkedListManager listOps,
                                   GraphOperations graphOps, PerformanceComparison performance) {
        System.out.println("\n=============================================");
        System.out.println("               ALL RESULTS");
        System.out.println("=============================================");

        System.out.println("\n[Array]");
        arrayOps.display();

        System.out.println("\n[Stack]");
        stackOps.display();

        System.out.println("\n[Queue]");
        queueOps.display();

        System.out.println("\n[Linked List]");
        listOps.display();

        System.out.println("\n[Graph]");
        graphOps.displayGraph();

        System.out.println("\n[Performance Comparison]");
        performance.showResults();
    }
}