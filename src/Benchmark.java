import java.io.*;

public class Benchmark {

    public static void saveCSV(String filePath,
                               int[]  sizes,
                               long[] dijkstraTimes,
                               long[] dijkstraMems,
                               long[] bfTimes,
                               long[] bfMems) throws IOException {

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("nodes,dijkstra_ms,dijkstra_mb,bf_ms,bf_mb");
            for (int i = 0; i < sizes.length; i++) {
                String bf  = bfTimes[i] == -1 ? "" : String.valueOf(bfTimes[i]);
                String bfm = bfMems[i]  == -1 ? "" : String.valueOf(bfMems[i]);
                pw.printf("%d,%d,%d,%s,%s%n",
                        sizes[i], dijkstraTimes[i], dijkstraMems[i], bf, bfm);
            }
        }
        System.out.println("CSV kaydedildi: " + filePath);
    }
}