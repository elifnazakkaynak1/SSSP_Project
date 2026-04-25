import java.io.*;
import java.util.*;

public class Graph {

    public static class Edge {
        public int to;
        public int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private int numNodes;
    private int numEdges;
    private List<List<Edge>> adjList;

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        this.adjList = new ArrayList<>(numNodes + 1);
        for (int i = 0; i <= numNodes; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjList.get(from).add(new Edge(to, weight));
        numEdges++;
    }

    public List<Edge> getNeighbors(int node) {
        return adjList.get(node);
    }

    public int getNumNodes() { return numNodes; }
    public int getNumEdges() { return numEdges; }

    public static Graph loadFromDIMACS(String filePath) throws IOException {
        Graph graph = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("c")) continue;

                if (line.startsWith("p sp")) {
                    String[] parts = line.split("\\s+");
                    int nodes = Integer.parseInt(parts[2]);
                    graph = new Graph(nodes);
                    System.out.println("Graf yükleniyor: " + nodes + " düğüm");

                } else if (line.startsWith("a")) {
                    String[] parts = line.split("\\s+");
                    int from   = Integer.parseInt(parts[1]);
                    int to     = Integer.parseInt(parts[2]);
                    int weight = Integer.parseInt(parts[3]);
                    graph.addEdge(from, to, weight);
                }
            }
        }

        System.out.println("Yüklendi: " + graph.getNumEdges() + " kenar");
        return graph;
    }
}