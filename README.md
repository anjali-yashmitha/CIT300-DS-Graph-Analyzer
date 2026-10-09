# CIT300-DS-Graph-Analyzer

**CIT300 Data Structures and Algorithms: Graded Practical Assignment 2**
**Data Structure and Graph Performance Analyzer**

---

## Project Description

The Data Structure and Graph Performance Analyzer is a Java console application that lets a user work with different data structures from one main menu. Each data structure has its own submenu with the required operations, input validation, and handling for empty or invalid cases.

The program also compares the performance of algorithms by counting the number of steps they use: Linear Search against Binary Search, and Breadth First Search (BFS) against Depth First Search (DFS).

---

## Team Members

| Member | Student Name | Student ID | Assigned Responsibility |
|---|---|---|---|
| Member 1 (Group Leader) | C.H.M.A.Y.K. Monarawila | 23DA2-0212 | Array, Searching, Performance Comparison, Main Menu |
| Member 2 | Thawoos Fathima Sajiya Bee | 23DA2-0446 | Stack and Queue |
| Member 3 | G.M.H.P. Karunarathna | 23DA2-0193 | Linked List |
| Member 4 | B.M. Navoda Harshani Bannaka | 23DA2-0219 | Graph (BFS and DFS) |

**Integration and testing:** done by all four members together.

---

## Individual Contributions

### Member 1: C.H.M.A.Y.K. Monarawila (23DA2-0212)

**Assigned Responsibility:** Array, Searching, Performance Comparison, Main Menu

**Individual Contribution:**
- Implemented `ArrayOperations` (insert, delete, search, display) with a submenu, position checks, and handling for full and empty arrays.
- Implemented `SearchOperations` with Linear Search and Binary Search, a step counter for each, and a compare option (Binary Search runs on a sorted copy of the array).
- Implemented `PerformanceComparison`: Linear against Binary Search on arrays of 10 to 100,000 items, the same comparison on the user's own array, and BFS against DFS on the graph.
- Implemented `Main.java`: the main menu (options 1 to 9) that connects the components, and "Display All Results".
- Took part in the integration and testing of the whole system together with all members (menus tested with wrong inputs such as letters, wrong numbers, empty structures, and invalid positions).
- Created the GitHub repository, set up the branch workflow, reviewed and merged the pull requests, and fixed a comment error in pull request #7 before merging.

### Member 2: Thawoos Fathima Sajiya Bee (23DA2-0446)

**Assigned Responsibility:** Stack and Queue

**Individual Contribution:**
- Implemented the array-based `StackOperations` class (push, pop, peek, display) with stack overflow and stack underflow handling.
- Implemented the `QueueOperations` class as a circular array queue (enqueue, dequeue, peek front, display) with queue overflow and queue underflow handling.
- Kept the core stack and queue operations at O(1) time complexity.
- Added the submenus (`showMenu`) with a `readInt` helper, so wrong input does not crash the program.
- Submitted the work through pull requests #2 and #3 from the branch `Member-2-stack-queue`.
- Took part in the integration and testing of the whole system together with all members.

### Member 3: G.M.H.P. Karunarathna (23DA2-0193)

**Assigned Responsibility:** Linked List

**Individual Contribution:**
- Implemented the singly linked list using the `LinkedListManager` and `Node` classes.
- Implemented insert, delete (empty list, first node, middle or last node, and value not found), search (returns the position or -1), and display.
- Added the Linked List submenu (`showMenu`) with input validation, so letters do not crash the program.
- Fixed the Scanner input buffer problem (`sc.nextLine()`), so the main menu works correctly after returning from the Linked List menu.
- Submitted the work through pull requests #4, #5, and #6 from the branch `Member-3-LinkedList`.
- Took part in the integration and testing of the whole system together with all members.

### Member 4: B.M. Navoda Harshani Bannaka (23DA2-0219)

**Assigned Responsibility:** Graph (BFS and DFS)

**Individual Contribution:**
- Added the `GraphOperations` class, which stores the graph as an adjacency list using a `LinkedHashMap`.
- Implemented the graph operations: add vertex, add edge, display graph, BFS traversal, and DFS traversal. Both traversals return the number of vertices visited.
- Added a sample graph loader (vertices A to E) and the Graph submenu with input validation.
- Submitted the work through pull request #7 from the branch `Member-4-graph`.
- Took part in the integration and testing of the whole system together with all members.

---

## Technologies Used

- **Language:** Java (tested with Java 21; Java 8 or later is enough)
- **Type of application:** Console-based (text menus)
- **IDE:** Eclipse
- **Version control:** Git and GitHub (branches, commits, pull requests)

---

## Main System Features

```
=============================================
     DATA STRUCTURE & GRAPH ANALYZER
=============================================
1. Array Operations
2. Stack Operations
3. Queue Operations
4. Linked List Operations
5. Searching Operations
6. Graph Operations
7. Performance Comparison
8. Display All Results
9. Exit
```

| Component | Operations |
|---|---|
| Array | Insert, delete, search, display (fixed capacity of 20) |
| Stack | Push, pop, peek, display (capacity of 10) |
| Queue | Enqueue, dequeue, peek front, display (circular array, capacity of 10) |
| Linked List | Insert, delete, search, display |
| Searching | Linear search, binary search, compare both with step counts |
| Graph | Add vertex, add edge, display graph, BFS, DFS, load sample graph (adjacency list) |
| Performance Comparison | Linear against Binary Search (different array sizes and your own array), BFS against DFS, run all |
| Display All Results | Shows the content of every structure and the last performance results |

**Input validation and error handling**
- Letters or invalid numbers in any menu give a message and the program asks again (no crash).
- Pop, peek, dequeue, delete, and search on empty structures show a clear message.
- Stack overflow and queue overflow are handled when the structure is full.
- Invalid array positions, duplicate vertices, duplicate edges, an edge from a vertex to itself, and missing vertices are rejected with a message.

---

## How to Run the Program

### Option A: Eclipse

1. Clone the repository: `git clone https://github.com/anjali-yashmitha/CIT300-DS-Graph-Analyzer.git`
2. In Eclipse, choose **File > New > Java Project**.
3. Enter the project name `CIT300-DS-Graph-Analyzer`, untick **Use default location**, browse to the cloned folder, and click **Finish**.
4. Right click `src/analyzer/Main.java`, then choose **Run As > Java Application**.
5. Use the **Console** tab to enter the menu choices.

### Option B: Command line

Open a terminal in the project folder and run:

```
javac -d out src/analyzer/*.java
java -cp out analyzer.Main
```
