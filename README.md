# SSSP Algorithm Comparison — Bay Area Road Network

This project implements and compares two Single-Source Shortest Path (SSSP) 
algorithms: Dijkstra's Algorithm and Bellman-Ford Algorithm, tested on the 
Bay Area road network from the 9th DIMACS Implementation Challenge.

## Dataset

- **Source:** 9th DIMACS Implementation Challenge
- **File:** USA-road-d.BAY.gr
- **Nodes:** 321,270 | **Edges:** 800,172

## Algorithms

- **Dijkstra's Algorithm** — O((V + E) log V)
- **Bellman-Ford Algorithm** — O(V × E)

## Project Structure
SSSP_Project/
├── src/
│   ├── Main.java
│   ├── Graph.java
│   ├── Dijkstra.java
│   ├── BellmanFord.java
│   ├── Benchmark.java
│   └── Chart.java
├── data/
│   └── USA-road-d.BAY.gr
└── results/
└── results.csv
## How to Run

1. Place `USA-road-d.BAY.gr` inside the `data/` folder
2. Compile and run `Main.java`
3. Results will be saved to `results/results.csv`
4. A comparison chart will open automatically

## Results

| Nodes | Dijkstra | Bellman-Ford |
|-------|----------|--------------|
| 10,000 | 10 ms | 12 ms |
| 50,000 | 80 ms | 709 ms |
| 200,000 | 66 ms | 2,883 ms |

## Course

CENG383 — Algorithm Analysis
