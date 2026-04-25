import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "data/USA-road-d.BAY.gr";

        System.out.println("Dosya okunuyor...");
        Graph graph = Graph.loadFromDIMACS(path);

        int[] sizes = {10000, 50000, 200000};

        System.out.println("\n--- DIJKSTRA vs BELLMAN-FORD KARŞILAŞTIRMA ---");
        System.out.printf("%-10s | %-14s | %-12s | %-16s | %-10s%n",
                "Düğüm", "Dijkstra(ms)", "Dij.Mem(MB)", "BellmanFord(ms)", "BF.Mem(MB)");
        System.out.println("-".repeat(72));

        long[] dijkstraTimes = new long[sizes.length];
        long[] dijkstraMems  = new long[sizes.length];
        long[] bfTimes       = new long[sizes.length];
        long[] bfMems        = new long[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];

            Graph subgraph = new Graph(size);
            for (int u = 1; u <= size; u++) {
                for (Graph.Edge e : graph.getNeighbors(u)) {
                    if (e.to <= size) subgraph.addEdge(u, e.to, e.weight);
                }
            }

            long start = System.currentTimeMillis();
            Dijkstra.run(subgraph, 1);
            dijkstraTimes[i] = System.currentTimeMillis() - start;
            dijkstraMems[i]  = Dijkstra.estimateMemoryBytes(
                    size, subgraph.getNumEdges()) / 1024 / 1024;

            List<BellmanFord.FlatEdge> edges =
                    BellmanFord.extractEdges(graph, size);
            start = System.currentTimeMillis();
            BellmanFord.run(size, edges, 1);
            bfTimes[i] = System.currentTimeMillis() - start;
            bfMems[i]  = BellmanFord.estimateMemoryBytes(
                    size, edges.size()) / 1024 / 1024;

            System.out.printf("%-10d | %-14s | %-12s | %-16s | %-10s%n",
                    size,
                    dijkstraTimes[i] + " ms",
                    dijkstraMems[i]  + " MB",
                    bfTimes[i]       + " ms",
                    bfMems[i]        + " MB");
        }

        Chart.show(sizes, dijkstraTimes, dijkstraMems, bfTimes, bfMems);

        Benchmark.saveCSV("results/results.csv",
                sizes, dijkstraTimes, dijkstraMems, bfTimes, bfMems);
    }
}