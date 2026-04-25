import java.util.*;

public class Dijkstra {

    public static final int INF = Integer.MAX_VALUE;

    public static int[] run(Graph graph, int source) {
        int n = graph.getNumNodes();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, source});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], u = curr[1];

            if (d > dist[u]) continue;

            for (Graph.Edge e : graph.getNeighbors(u)) {
                if (dist[u] != INF && dist[u] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[u] + e.weight;
                    pq.offer(new int[]{dist[e.to], e.to});
                }
            }
        }
        return dist;
    }

    public static long estimateMemoryBytes(int numNodes, int numEdges) {
        long distArray = (long) numNodes * 4;
        long pqWorst   = (long) numEdges * 32;
        long adjList   = (long) numEdges * 24;
        return distArray + pqWorst + adjList;
    }
}