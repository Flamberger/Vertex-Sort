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
        try {
            for (Edge e : v1.adjlist) {
                if (e.to == v2) {
                    edge = e;
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("hello world");
        }
	    return edge;
    }
    
    public int MSTCost() {
        Graph mst = this.MST();
        if (mst == null || vlist.size() < 2) return 0;

        int cost = 0;
        for (Vertex v : mst.vlist) {
            for (Edge e : v.adjlist) {
                cost += e.weight;
            }
        }
        cost /= 2;
	    return cost;
    }

    private boolean isVertex(String name) {
        for (Vertex v : vlist) {
            if (v.name.equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    private int checkVertex(ArrayList<Set<Vertex>> verts, String name) {
        for (Set<Vertex> vs : verts) {
            for (Vertex v : vs) {
                if (v.name.equalsIgnoreCase(name)) return verts.indexOf(vs);
            }
        }
        return -1;
    }

    public Graph MST() {
        Graph mst = new Graph();
        if (vlist.size() == 0) return mst;
        if (vlist.size() == 1) {
            mst.addVertex(vlist.get(0).name);
            return mst;
        }

        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Set<Vertex>> visitedNodes = new ArrayList<>();

        vlist.forEach(v -> {
            Set<Vertex> node = new HashSet<>();
            node.add(v);
            visitedNodes.add(node);
            edges.addAll(v.adjlist);
        });

        edges.sort(Comparator.comparingInt(edge -> edge.weight));

        edges.forEach(e -> {
            Vertex from = e.from;
            Vertex to = e.to;
            int idxFrom = checkVertex(visitedNodes, from.name);
            int idxTo = checkVertex(visitedNodes, to.name);
            if (idxFrom != idxTo) {
                if (!mst.isVertex(from.name)) {
                    mst.addVertex(from.name);
                }
                if (!mst.isVertex(to.name)) {
                    mst.addVertex(to.name);
                }
                mst.addEdge(from.name, to.name, e.weight);

                // join visited sets
                visitedNodes.get(idxFrom).addAll(visitedNodes.get(idxTo));
                visitedNodes.remove(idxTo);
            }
        });

        return mst;
    }

    public int SPCost(String from, String to) {
        Graph spGraph = SP(from, to);
        int cost = 0;
        for (Vertex v : spGraph.vlist) {
            for (Edge e : v.adjlist) {
                cost += e.weight;
            }
        }

        return cost/2;
    }

    public Graph SP(String from, String to) {
        if (vlist.size() == 1) {
            Graph g = new Graph();
            g.addVertex(vlist.get(0).name);
            return g;
        }
        Graph shortP = new Graph();
        PriorityQueue<Vertex> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.dist, v2.dist));
        Vertex origin = getVertex(from);

        vlist.forEach(v -> {
            if (v.name.equalsIgnoreCase(from)) {
                v.dist = 0;
            }
            pq.add(v);
        });

        while (!pq.isEmpty()){
            Vertex v = pq.poll();
            if (v.name.equalsIgnoreCase(to)) {
                Vertex current = v;
                while (current.prev != null) {
                    Vertex prev = current.prev;
                    if (!shortP.isVertex(current.name)) {
                        shortP.addVertex(current.name);
                    }
                    if (!shortP.isVertex(prev.name)) {
                        shortP.addVertex(prev.name);
                    }
                    Edge ed = getEdge(prev.name, current.name);
                    shortP.addEdge(prev.name, current.name, ed.weight);

                    current = prev;
                }
                return shortP;
            }

            for (Edge e : v.adjlist) {
                Vertex vert = e.to;
                if (!pq.contains(vert)) continue;
                int altD = 0;
                altD = e.weight + e.from.dist;

                if (altD < vert.dist) {
                    vert.dist = altD;
                    vert.prev = v;
                    pq.remove(vert);
                    pq.add(vert);
                }
            }
        }

	    return shortP;
    }

}


