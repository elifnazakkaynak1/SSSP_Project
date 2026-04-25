import java.util.*;

public class BellmanFord {

    public static final int INF = Integer.MAX_VALUE;

    public static class FlatEdge {
        int from, to, weight;
        public FlatEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static int[] run(int numNodes, List<FlatEdge> edges, int source) {
        int[] dist = new int[numNodes + 1];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        for (int i = 1; i < numNodes; i++) {
            boolean updated = false;
            for (FlatEdge e : edges) {
                if (dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[e.from] + e.weight;
                    updated = true;
                }
            }
            if (!updated) break;
        }
        return dist;
    }

    public static List<FlatEdge> extractEdges(Graph graph, int maxNode) {
        List<FlatEdge> edges = new ArrayList<>();
        for (int u = 1; u <= maxNode; u++) {
            for (Graph.Edge e : graph.getNeighbors(u)) {
                if (e.to <= maxNode) {
                    edges.add(new FlatEdge(u, e.to, e.weight));
                }
            }
        }
        return edges;
    }

    public static long estimateMemoryBytes(int numNodes, int numEdges) {
        long distArray = (long) numNodes * 4;
        long edgeList  = (long) numEdges * 28;
        return distArray + edgeList;
    }
}