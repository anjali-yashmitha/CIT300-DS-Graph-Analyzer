import java.util.*;

public class Graph {
   
    private Map<String, List<String>> adjList = new HashMap<>();

    public void addVertex(String vertex) {
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
            System.out.println("Vertex '" + vertex + "' added successfully.");
        } else {
            System.out.println("Vertex already exists!");
        }
    }

    public void addEdge(String source, String destination) {
        if (adjList.containsKey(source) && adjList.containsKey(destination)) {
            adjList.get(source).add(destination);
            adjList.get(destination).add(source); 
            System.out.println("Edge added between " + source + " and " + destination);
        } else {
            System.out.println("One or both vertices do not exist!");
        }
    }

    public void displayGraph() {
        if (adjList.isEmpty()) {
            System.out.println("Graph is empty!");
            return;
        }
        System.out.println("\n--- Graph Structure (Adjacency List) ---");
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void bfs(String startVertex) {
        if (!adjList.containsKey(startVertex)) {
            System.out.println("Start vertex not found in graph!");
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " ");

            for (String neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(String startVertex) {
        if (!adjList.containsKey(startVertex)) {
            System.out.println("Start vertex not found in graph!");
            return;
        }

        Set<String> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfsHelper(startVertex, visited);
        System.out.println();
    }

    private void dfsHelper(String vertex, Set<String> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (String neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void graphMenu(Scanner scanner) {
        int choice;

        do {
            System.out.println("\n--- GRAPH OPERATIONS ---");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter vertex name (e.g., A, B, C): ");
                    String v = scanner.nextLine();
                    addVertex(v);
                    break;
                case 2:
                    System.out.print("Enter source vertex: ");
                    String src = scanner.nextLine();
                    System.out.print("Enter destination vertex: ");
                    String dest = scanner.nextLine();
                    addEdge(src, dest);
                    break;
                case 3:
                    displayGraph();
                    break;
                case 4:
                    System.out.print("Enter start vertex for BFS: ");
                    String bStart = scanner.nextLine();
                    bfs(bStart);
                    break;
                case 5:
                    System.out.print("Enter start vertex for DFS: ");
                    String dStart = scanner.nextLine();
                    dfs(dStart);
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph g = new Graph();
        g.graphMenu(scanner);
    }
}