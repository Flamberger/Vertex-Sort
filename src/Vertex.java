import java.beans.VetoableChangeListener;
import java.util.ArrayList;

public class Vertex {
    public String name;
    public ArrayList<Edge> adjlist;
    int dist;
    Vertex prev;

    public Vertex(String _name) {
        name = _name;
        adjlist = new ArrayList<>();
        dist = Integer.MAX_VALUE;
        prev = null;
    }
}