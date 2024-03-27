public class Test {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add vertices
//        graph.addVertex("0");
//        graph.addVertex("1");
//        graph.addVertex("2");
//        graph.addVertex("3");

        // Add edges
//        graph.addEdge("0", "1", 5);
//        graph.addEdge("0", "2", 10);
//        graph.addEdge("0", "3", 1);
//        graph.addEdge("1", "2", 2);
//        graph.addEdge("1", "3", 3);
//        graph.addEdge("2", "3", 7);


        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 10);
        graph.addEdge("B", "C", 20);
        graph.addEdge("B", "D", 3);
        graph.addEdge("C", "D", 7);
        graph.addEdge("B", "E", 1);
        graph.addEdge("D", "E", 1);
        graph.addEdge("E", "F", 1);
        graph.addEdge("F", "A", 1);
        // Test the MST algorithm
        Graph mstGraph = graph.MST();

        Graph sp = graph.SP("B", "A");
        sp.vlist.forEach(v -> {
            System.out.println(v.name + ": ");
            v.adjlist.forEach(e -> {
                System.out.println(e.from.name + " -> " + e.to.name + ": " + e.weight);
            });
        });
        System.out.println(graph.SPCost("B", "A"));

        // Display the MST
//        System.out.println("Minimum Spanning Tree (MST):");
//        mstGraph.vlist.forEach(vertex -> {
//            vertex.adjlist.forEach(edge -> {
//                System.out.println(vertex.name + " -> " + edge.to.name + " (Weight: " + edge.weight + ")");
//            });
//        });
//        System.out.println("MST cost: " + mstGraph.MSTCost());
        Graph g = new Graph();
        // Add vertices
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");

        g.addEdge("A", "B", 16);
        g.addEdge("A", "C", 20);
        g.addEdge("A", "D", 9999);
        g.addEdge("B", "C", 9999);
        g.addEdge("B", "D", 5);
        g.addEdge("C", "D", 9999);

        // Calculate the cost of the minimum spanning tree (MST)
        int mstCost = g.MSTCost();
        System.out.println("Cost of Minimum Spanning Tree: " + mstCost);

        // Calculate the cost of the shortest path between vertices "A" and "B"
        int spCost = g.SPCost("A", "B");
        System.out.println("Cost of Shortest Path between A and B: " + spCost);

        // Find the shortest path between vertices "A" and "B"
        Graph shortestPath = g.SP("A", "B");
        System.out.println("Shortest Path between A and B:");
        shortestPath.vlist.forEach(vertex -> {
            System.out.println(vertex.name);
        });
    }
}
