
import javax.swing.*;
import java.awt.*;

public class Chart {

    public static void show(int[]  sizes,
                            long[] dijkstraTimes,
                            long[] dijkstraMems,
                            long[] bfTimes,
                            long[] bfMems) {

        JFrame frame = new JFrame("SSSP Algorithm Comparison — Bay Area");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new GridLayout(1, 2, 10, 10));

        frame.add(new BarPanel("Runtime Comparison (ms)",
                sizes, dijkstraTimes, bfTimes,
                new Color(70, 130, 180), new Color(220, 80, 80),
                "Dijkstra", "Bellman-Ford"));

        frame.add(new BarPanel("Memory Usage (MB)",
                sizes, dijkstraMems, bfMems,
                new Color(70, 180, 130), new Color(200, 140, 50),
                "Dijkstra", "Bellman-Ford"));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static class BarPanel extends JPanel {
        private final String title;
        private final int[]  sizes;
        private final long[] data1, data2;
        private final Color  color1, color2;
        private final String label1, label2;

        BarPanel(String title, int[] sizes, long[] data1, long[] data2,
                 Color color1, Color color2, String label1, String label2) {
            this.title  = title;
            this.sizes  = sizes;
            this.data1  = data1;
            this.data2  = data2;
            this.color1 = color1;
            this.color2 = color2;
            this.label1 = label1;
            this.label2 = label2;
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int padLeft = 60, padBottom = 60, padTop = 60, padRight = 20;
            int chartW = w - padLeft - padRight;
            int chartH = h - padTop - padBottom;

            g2.setColor(Color.DARK_GRAY);
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(title, (w - fm.stringWidth(title)) / 2, 30);

            long maxVal = 1;
            for (long v : data1) maxVal = Math.max(maxVal, v);
            for (long v : data2) maxVal = Math.max(maxVal, v);

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(padLeft, padTop, padLeft, padTop + chartH);
            g2.drawLine(padLeft, padTop + chartH, padLeft + chartW, padTop + chartH);

            g2.setFont(new Font("Arial", Font.PLAIN, 11));
            g2.setColor(Color.GRAY);
            for (int i = 0; i <= 4; i++) {
                int y = padTop + chartH - (chartH * i / 4);
                long val = maxVal * i / 4;
                g2.drawString(val + "", 2, y + 4);
                g2.setColor(new Color(220, 220, 220));
                g2.drawLine(padLeft, y, padLeft + chartW, y);
                g2.setColor(Color.GRAY);
            }

            int n = sizes.length;
            int groupW = chartW / n;
            int barW = groupW / 3;

            for (int i = 0; i < n; i++) {
                int x = padLeft + i * groupW + groupW / 6;

                int h1 = (int) (chartH * data1[i] / maxVal);
                g2.setColor(color1);
                g2.fillRoundRect(x, padTop + chartH - h1, barW, h1, 4, 4);
                g2.setColor(color1.darker());
                g2.drawRoundRect(x, padTop + chartH - h1, barW, h1, 4, 4);

                int h2 = (int) (chartH * data2[i] / maxVal);
                g2.setColor(color2);
                g2.fillRoundRect(x + barW + 4, padTop + chartH - h2, barW, h2, 4, 4);
                g2.setColor(color2.darker());
                g2.drawRoundRect(x + barW + 4, padTop + chartH - h2, barW, h2, 4, 4);

                g2.setColor(Color.DARK_GRAY);
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.drawString(data1[i] + "", x, padTop + chartH - h1 - 3);
                g2.drawString(data2[i] + "", x + barW + 4, padTop + chartH - h2 - 3);

                g2.setFont(new Font("Arial", Font.BOLD, 11));
                String lbl = (sizes[i] / 1000) + "K";
                int lx = x + barW - g2.getFontMetrics().stringWidth(lbl) / 2;
                g2.drawString(lbl, lx, padTop + chartH + 20);
            }

            int lx = padLeft;
            int ly = h - 20;
            g2.setFont(new Font("Arial", Font.PLAIN, 11));

            g2.setColor(color1);
            g2.fillRect(lx, ly - 10, 12, 12);
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(label1, lx + 16, ly);

            g2.setColor(color2);
            g2.fillRect(lx + 100, ly - 10, 12, 12);
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(label2, lx + 116, ly);
        }
    }
}