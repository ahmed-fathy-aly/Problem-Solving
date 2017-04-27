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

    int getNumberOfNodes() {
        return n;
    }

    /**
     * @param source from 0 -> n - 1
     */
    List<Edge> getEdges(int source) {
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
     *
     * @return list of all edges in the graph
     */
    List<Edge> getAllEdges() {
        List<Edge> result = new ArrayList<>();
        for (int i = 0; i < n; i++)
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

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Graph))
            return false;
        Graph other = (Graph) obj;
        if (other.getNumberOfNodes() != n)
            return false;

        // compare the edges
        for (int i = 0; i < n; i++){

            // sort the two lists of edges coming from that node
            List<Edge> mine = getEdges(i);
            List<Edge> his = other.getEdges(i);
            Comparator<Edge> edgeComparator = (o1, o2) -> {
                if (o1.source != o2.source)
                    return Integer.compare(o1.source, o2.source);
                else if (o1.dest != o2.dest)
                    return Integer.compare(o1.dest, o2.dest);
                else
                    return Double.compare(o1.weight, o2.weight);
            };
            Collections.sort(mine, edgeComparator);
            Collections.sort(his, edgeComparator);

            // check the two list of edges are equal
            if (mine.size() != his.size())
                return false;
            for (int k = 0; k < mine.size(); k++){

                // I don't want to ovveride the equals in the Edge, maybe I'll use it differently in a problem
                if (mine.get(k).source != his.get(k).source)
                    return false;
                if (mine.get(k).dest!= his.get(k).dest)
                    return false;
                if (mine.get(k).weight!= his.get(k).weight)
                    return false;

            }
        }

        return true;
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

    /**
     * Kruskal's minimum spanning tree
     * O(m * log(m) + m * log(n)) with m  edges and n nodes
     * depends on {@Link data_structures.UnionFind} so I just copied it here to make it easier to copy in problems
     * @return graph representing the minimum spanning tree
     */
    public Graph getMST() {
        List<Edge> allEdges = getAllEdges();
        Collections.sort(allEdges, Comparator.comparingDouble(o -> o.weight));
        Graph result = new Graph(n);
       UnionFind unionFind = new UnionFind(n);
        for (Edge e : allEdges)
            if (!unionFind.isJoined(e.source, e.dest)) {
                unionFind.union(e.source, e.dest);
                result.addUnDirectedEdge(e.source, e.dest, e.weight);
            }
        return result;
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

    class UnionFind {

        int n;
        int[] parent, size;

        public UnionFind(int n) {
            this.n = n;
            parent = new int[n];
            Arrays.fill(parent, -1);
            size = new int[n];
            Arrays.fill(size, 1);
        }

        /**
         * @return true if l and r belong to the same set
         */
        public boolean isJoined(int l, int r) {
            return find(l) == find(r);
        }

        /**
         * O(log(n))
         * joins the sets of the two nodes l, r
         * @return true if they are already joined
         */
        public boolean union(int l, int r) {
            int root1 = find(l);
            int root2 = find(r);
            if (root1 == root2)
                return true;
            if (size[root2] > size[root1]){
                int temp = root1;
                root1 = root2;
                root2 = temp;
            }

            size[root1] += size[root2];
            parent[root2] = root1;
            return false;
        }

        public int find(int node) {
            if (parent[node] == -1)
                return node;
            return parent[node] = find(parent[node]);
        }

    }

}

