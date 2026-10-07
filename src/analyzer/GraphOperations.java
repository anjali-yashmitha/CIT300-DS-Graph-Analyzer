package analyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Graph component of the Data Structure and Graph Performance Analyzer.
 * The graph is undirected and is stored as an adjacency list.
 * Each vertex (a String) maps to the list of its neighbours.
 */
public class GraphOperations {

    // LinkedHashMap keeps the vertices in the order they were added.
    private Map<String, List<String>> adjList = new LinkedHashMap<>();

    // ---------------------------------------------------------------
    // Basic graph operations
    // ---------------------------------------------------------------

    // Add a new vertex. Duplicate and empty names are rejected.
    public void addVertex(String vertex) {
        if (vertex == null || vertex.trim().isEmpty()) {
            System.out.println("Vertex name cannot be empty.");
            return;
        }
        vertex = vertex.trim();
        if (adjList.containsKey(vertex)) {
            System.out.println("Vertex '" + vertex + "' already exists.");
            return;
        }
        adjList.put(vertex, new ArrayList<>());
        System.out.println("Vertex '" + vertex + "' added successfully.");
    }

    // Add an undirected edge between two existing vertices.
    public void addEdge(String source, String destination) {
        if (source == null || destination == null) {
            System.out.println("Vertex name cannot be empty.");
            return;
        }
        source = source.trim();
        destination = destination.trim();

        if (!adjList.containsKey(source) || !adjList.containsKey(destination)) {
            System.out.println("One or both vertices do not exist. Add them first.");
            return;
        }
        if (source.equals(destination)) {
            System.out.println("A vertex cannot be connected to itself.");
            return;
        }
        if (adjList.get(source).contains(destination)) {
            System.out.println("Edge between " + source + " and " + destination
                    + " already exists.");
            return;
        }
        adjList.get(source).add(destination);
        adjList.get(destination).add(source);
        System.out.println("Edge added between " + source + " and " + destination + ".");
    }

    // Show every vertex and its neighbours.
    public void displayGraph() {
        if (adjList.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }
        System.out.println("\n--- Graph Structure (Adjacency List) ---");
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // ---------------------------------------------------------------
    // Traversals. Both return the number of vertices visited (steps).
    // ---------------------------------------------------------------

    // Breadth First Search: visit the nearest vertices first, using a queue. O(V + E)
    public int bfs(String startVertex) {
        if (adjList.isEmpty()) {
            System.out.println("Graph is empty.");
            return 0;
        }
        if (startVertex == null || !adjList.containsKey(startVertex.trim())) {
            System.out.println("Start vertex not found in the graph.");
            return 0;
        }
        startVertex = startVertex.trim();

        int steps = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            steps++;                              // one vertex visited
            System.out.print(current + " ");

            for (String neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
}
        System.out.println();
        return steps;
    }

    // Depth First Search: go as deep as possible first, using recursion. O(V + E)
    public int dfs(String startVertex) {
        if (adjList.isEmpty()) {
            System.out.println("Graph is empty.");
            return 0;
        }
        if (startVertex == null || !adjList.containsKey(startVertex.trim())) {
            System.out.println("Start vertex not found in the graph.");
            return 0;
        }
        startVertex = startVertex.trim();

        Set<String> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        int steps = dfsHelper(startVertex, visited);
        System.out.println();
        return steps;
    }

    // Visits one vertex, then visits its unvisited neighbours.
    // Returns how many vertices were visited from this vertex.
    private int dfsHelper(String vertex, Set<String> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");
        int count = 1;                            // this vertex

        for (String neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                count += dfsHelper(neighbor, visited);
            }
        }
        return count;
    }

    // ---------------------------------------------------------------
    // Helpers used by Main and PerformanceComparison
    // ---------------------------------------------------------------

    // Replace the graph with a small example graph:
    //   A - B, A - C, B - D, C - D, D - E
    public void loadSampleGraph() {
        adjList.clear();
        String[] names = {"A", "B", "C", "D", "E"};
        for (String name : names) {
            adjList.put(name, new ArrayList<>());
        }
        connect("A", "B");
        connect("A", "C");
        connect("B", "D");
        connect("C", "D");
        connect("D", "E");
    }

    // Adds an edge without printing anything (used only by loadSampleGraph).
    private void connect(String a, String b) {
        adjList.get(a).add(b);
        adjList.get(b).add(a);
    }

    // Returns the first vertex that was added, or null if the graph is empty.
    public String getFirstVertex() {
        for (String vertex : adjList.keySet()) {
            return vertex;
        }
        return null;
    }

    public boolean isEmpty() {
        return adjList.isEmpty();
    }

    // ---------------------------------------------------------------
    // Menu
    // ---------------------------------------------------------------

    // Reads a whole number safely (no crash when the user types letters).
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

    // The Graph submenu. Main.java calls this method.
    public void showMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Load Sample Graph");
            System.out.println("7. Return to Main Menu");
            choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1: {
                    System.out.print("Enter vertex name (for example A, B, C): ");
                    String vertex = sc.nextLine();
                    addVertex(vertex);
                    break;
                }
                case 2: {
                    System.out.print("Enter first vertex: ");
                    String source = sc.nextLine();
                    System.out.print("Enter second vertex: ");
                    String destination = sc.nextLine();
                    addEdge(source, destination);
                    break;
                }
                case 3:
                    displayGraph();
                    break;
                case 4: {
                    System.out.print("Enter start vertex for BFS: ");
                    String start = sc.nextLine();
                    int steps = bfs(start);
                    if (steps > 0) {
                        System.out.println("Vertices visited (steps): " + steps);
                    }
                    break;
                }
                case 5: {
                    System.out.print("Enter start vertex for DFS: ");
                    String start = sc.nextLine();
                    int steps = dfs(start);
                    if (steps > 0) {
                        System.out.println("Vertices visited (steps): " + steps);
                    }
                    break;
                }
                case 6:
                    loadSampleGraph();
                    System.out.println("Sample graph loaded (vertices A to E).");
                    break;
                case 7:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 to 7.");
            }
        } while (choice != 7);
    }
}
