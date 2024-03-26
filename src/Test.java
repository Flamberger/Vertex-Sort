public class Test {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add vertices
        graph.addVertex("0");
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");

        // Add edges
        graph.addEdge("0", "1", 5);
        graph.addEdge("0", "2", 10);
        graph.addEdge("0", "3", 1);
        graph.addEdge("1", "2", 20);
        graph.addEdge("1", "3", 3);
        graph.addEdge("2", "3", 7);


//        graph.addVertex("0");
//        graph.addVertex("1");
//        graph.addVertex("2");
//        graph.addVertex("3");
//        graph.addVertex("4");
//        graph.addVertex("5"); // New vertex
//
//        // Add edges
//        graph.addEdge("0", "1", 5);
//        graph.addEdge("0", "2", 10);
//        graph.addEdge("1", "2", 20);
//        graph.addEdge("1", "3", 3);
//        graph.addEdge("2", "3", 7);
//        graph.addEdge("2", "4", 8);
//        graph.addEdge("3", "4", 15);
//        graph.addEdge("4", "5", 12); // New edge
//        graph.addEdge("5", "0", 6);
        // Test the MST algorithm
        Graph mstGraph = graph.MST();

        // Display the MST
        System.out.println("Minimum Spanning Tree (MST):");
        mstGraph.vlist.forEach(vertex -> {
            vertex.adjlist.forEach(edge -> {
                System.out.println(vertex.name + " -> " + edge.to.name + " (Weight: " + edge.weight + ")");
            });
        });
        System.out.println("MST cost: " + mstGraph.MSTCost());
    }
}
