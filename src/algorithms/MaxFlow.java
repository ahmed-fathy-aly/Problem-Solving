package algorithms;

import java.util.*;

/**
 * Complexity O(flow * n * n)
 * Gets the maximum flow between two nodes
 * uses path augmentation, where each new path is augmented with a dfs
 */
public class MaxFlow {
    private int n, src, dest;
    private int[][] c;
    private int[] prev;
    private List<Integer> edges[];

    public long getMaxFlow(List<Integer> edges[], int[][] capacity, int src, int dest) {
        this.n = capacity.length;
        this.src = src;
        this.dest = dest;
        this.c = capacity;
        this.prev = new int[n];
        this.edges = edges;

        long totalFlow = 0;
        while (true) {
            long addedFlow = removeFlowDfs();
            if (addedFlow == 0)
                break;
            totalFlow += addedFlow;
        }
        return totalFlow;
    }

    private long removeFlowDfs() {
        Arrays.fill(prev, -1);
        prev[src] = -2;
        long minFlow = dfs(src);

        // if we didn't find dest then there's no path
        if (prev[dest] == -1)
            return 0;

        // remove the flow and add reverse edges
        int curr = dest;
        while (curr != src) {
            c[prev[curr]][curr] -= minFlow;
            c[curr][prev[curr]] += minFlow;
            curr = prev[curr];
        }

        return minFlow;
    }

    private long dfs(int node) {
        if (node == dest)
            return Long.MAX_VALUE;

        long max = 0;
        for (int next : edges[node])
            if (prev[next] == -1 && c[node][next] > 0) {
                prev[next] = node;
                max = Math.max(max, Math.min(c[node][next], dfs(next)));
            }
        return max;
    }

}


class StressTest {

    public static void main(String... args) {
        Random random = new Random();
        int nNodes = 1000;
        int nEdges = 1000;
        int maxCapacity = 1000;
        int nCases = 20;
        int[][] cap = new int[nNodes][nNodes];
        List<Integer> edges[] = new List[nNodes];
        for (int i = 0; i < nNodes; i++)
            edges[i] = new ArrayList<>();
        long t1 = System.currentTimeMillis();

        for (int k = 0; k < nCases; k++) {
            System.out.println(k);

            for (int i = 0; i < nEdges; i++){
                int from = random.nextInt(nNodes);
                int to = random.nextInt(nNodes);
                int capacity = random.nextInt(maxCapacity);
                edges[to].add(from);
                edges[from].add(to);
                cap[from][to] = capacity;
            }

            MaxFlow flow = new MaxFlow();
            flow.getMaxFlow(edges, cap, 0, nNodes - 1);
        }
        long t2 = System.currentTimeMillis();
        long time = t2 - t1;
        System.out.println(time);
    }

}