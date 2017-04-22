package algorithms;

import java.util.*;

/**
 * O(edges * log(edges)) implementation for single source shortest path
 */
public class Dijkstra {

    public static double MAX = 10000000000000000.0;

    /**
     *
     * @return shortest distance to other node and MAX for unreachable nodes
     */
    public static double[] disjktra(int startNode, int n, List<Edge> next[]) {
        final double dist[] = new double[n];
        Arrays.fill(dist, MAX);
        dist[startNode] = 0;
        TreeSet<Integer> pq = new TreeSet<>((l, r) -> {
            if (dist[l] == dist[r])
                return Integer.compare(l, r);
            else
                return Double.compare(dist[l], dist[r]);
        });

        pq.add(startNode);
        while (pq.size() > 0) {
            int node = pq.pollFirst();
            for (Edge nextEdge : next[node])
                if (dist[node] + nextEdge.weight < dist[nextEdge.dest]) {
                    pq.remove(nextEdge.dest);
                    dist[nextEdge.dest] = dist[node] + nextEdge.weight;
                    pq.add(nextEdge.dest);
                }
        }

        return dist;
    }

}

class Edge {
    int dest;
    double weight;

    public Edge(int dest, double weight) {
        this.dest = dest;
        this.weight = weight;
    }
}
