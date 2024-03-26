import java.util.*;

public class Graph {
    public ArrayList<Vertex> vlist;

    public Graph() {
        vlist = new ArrayList<>();
    }

    public void addVertex(String name) {
        Vertex v = new Vertex(name);
        vlist.add(v);
    }

    public Vertex getVertex(String name) {
        Vertex vert = null;
	    for (Vertex v : vlist) {
            if (v.name.equalsIgnoreCase(name)) {
                vert = v;
                break;
            }
        }
        return vert;
    }
    public void addEdge(String from, String to, int weight) {
        Vertex v1 = getVertex(from);
        Vertex v2 = getVertex(to);

        Edge toEdge = new Edge(v1, v2, weight);
        v1.adjlist.add(toEdge);
        Edge fromEdge = new Edge(v2, v1, weight);
        v2.adjlist.add(fromEdge);
    }

    public Edge getEdge(String from, String to) {
        Edge edge = null;
        Vertex v1 = getVertex(from);
        Vertex v2 = getVertex(to);

        for (Edge e : v1.adjlist) {
            if (e.to == v2) {
                edge = e;
                break;
            }
        }
	    return edge;
    }
    
    public int MSTCost() {
        // code below ought to return the MST cost
        Graph mst = this.MST();
        if (mst == null || vlist.size() < 2) return 0;

        int cost = 0;
        for (Vertex v : mst.vlist) {
            for (Edge e : v.adjlist) {
                cost += e.weight;
            }
        }

	    return cost/2;
    }

    public Graph MST() {
        // code below ought to return the MST graph, or whatever it said here
        if (vlist.size() == 0) return null;

        Graph graphMST = new Graph();
        ArrayList<Edge> edges = new ArrayList<>();
        Set<Vertex> visitedNodes = new HashSet<>();

        vlist.forEach(v -> {
            edges.addAll(v.adjlist);
        });

        edges.sort(Comparator.comparingInt(edge -> edge.weight));

        edges.forEach(e -> {
            Vertex from = e.from;
            Vertex to = e.to;

            // Check if these specific vertices have been added yet
            if (!visitedNodes.contains(to)) {
                // Add the vertices to the MST
                if (!graphMST.vlist.contains(from)) {
                    graphMST.addVertex(from.name);
                }
                graphMST.addVertex(to.name);
                graphMST.addEdge(from.name, to.name, e.weight);

                // Mark both vertices as visited
                visitedNodes.add(from);
                visitedNodes.add(to);
            }
        });

        return graphMST;
    }

    public int SPCost(String from, String to) {
	return 0;
    }

    public Graph SP(String from, String to) {
	return null;
    }
}
