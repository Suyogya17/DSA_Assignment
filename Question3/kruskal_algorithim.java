package Question3;
import java.util.*;
 
 class Edge implements Comparable<Edge> {
    int source, destination;
    double weight;
 
    public Edge(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
     @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }
}
 public class kruskal_algorithim {
 
    private int vertices;
    private List<Edge> edges;
     public kruskal_algorithim(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }
     public void addEdge(int source, int destination, double weight) {
        edges.add(new Edge(source, destination, weight));
    }
 
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return find(parent, parent[i]);
    }
 
    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        parent[rootX] = rootY;
    }
 
    public List<Edge> kruskalMST() {
        List<Edge> result = new ArrayList<>();
        Collections.sort(edges); 
 
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
 
        for (Edge edge : edges) {
            int rootSource = find(parent, edge.source);
            int rootDestination = find(parent, edge.destination);
             if (rootSource != rootDestination) {
                result.add(edge);
                union(parent, rootSource, rootDestination);
            }
        }
 
        return result;
    }
 
    public static void main(String[] args) {
        kruskal_algorithim graph = new kruskal_algorithim(4);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);
 
        List<Edge> mstEdges = graph.kruskalMST();
 
        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : mstEdges) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}
 