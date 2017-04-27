package data_structures;


import java.util.*;

/**
 * adjacency list representation of the graph
 * the nodes are zero-indexed
 */
public class Graph {
    int n;
    List<Edge> edges[];

    public Graph(int n) {
        this.n = n;
        this.edges = new List[n];
        for (int i = 0; i < n; i++)
            edges[i] = new ArrayList<>();
    }

    int getNumberOfNodes(){
        return n;
    }

    /**
     *
     * @param source from 0 -> n - 1
     */
    List<Edge> getEdges(int source){
        return edges[source];
    }

    public void addDirectedEdge(int source, int dest, double weight) {
        edges[source].add(new Edge(source, dest, weight));
    }

    public void addUnDirectedEdge(int source, int dest, double weight) {
        addDirectedEdge(source, dest, weight);
        addDirectedEdge(dest, source, weight);
    }

    /**
     * sorted by the source number then the order of which the edge was added
     * @return list of all edges in the graph
     */
    List<Edge> getAllEdges(){
        List<Edge> result = new ArrayList<>();
        for (int i = 0; i <n ; i++)
            result.addAll(edges[i]);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(String.format("%d nodes\n", n));
        for (int i = 0; i < n; i++)
            for (Edge e : edges[i])
                if (e.weight > 0)
                    strb.append(String.format("%d --%d--> %f\n", i, e.dest, e.weight));
                else
                    strb.append(String.format("%d ---> %d\n", i, e.dest));
        return strb.toString();
    }

    /**
     * @return shortest distance to other node and MAX for unreachable nodes or Double.MAX_VALUE for unreachable nodes
     */
    public double[] disjktra(int startNode) {
        final double dist[] = new double[n];
        Arrays.fill(dist, Double.MAX_VALUE);
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
            for (Edge nextEdge : edges[node])
                if (dist[node] + nextEdge.weight < dist[nextEdge.dest]) {
                    pq.remove(nextEdge.dest);
                    dist[nextEdge.dest] = dist[node] + nextEdge.weight;
                    pq.add(nextEdge.dest);
                }
        }

        return dist;
    }

    class Edge {
        int source, dest;
        double weight;

        public Edge(int source, int dest, double weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }
}

