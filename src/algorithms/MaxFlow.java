package algorithms;

import java.util.*;

/**
 * Complexity O(flow * n * n)
 * Gets the maximum flow between two nodes
 * uses path augmentation, where each new path is augmented with a dfs
 * 
 */
public class MaxFlow {
    private int n, src, dest;
    private int[][] c;
    private int[] prev;

    public int getMaxFlow(int[][] capacity, int src, int dest) {
        this.n = capacity.length;
        this.src = src;
        this.dest = dest;
        this.c = capacity;
        this.prev = new int[n];

        int totalFlow = 0;
        while (true) {
            int addedFlow = removeFlow();
            if (addedFlow == 0)
                break;
            totalFlow += addedFlow;
        }
        return totalFlow;
    }

    private int removeFlow() {
        Arrays.fill(prev, -1);
        prev[src] = -2;
        int minFlow = dfs(src);

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

    private int dfs(int node) {
        if (node == dest)
            return Integer.MAX_VALUE;

        int max = 0;
        for (int next = 0; next < n; next++)
            if (prev[next] == -1 && c[node][next] > 0) {
                prev[next] = node;
                max = Math.max(max, Math.min(c[node][next], dfs(next)));
            }
        return max;
    }

}

class StressTest{

    public static void main(String... args) {
        Random random = new Random();
        int nNodes = 100;
        int maxCapacity = 100;
        int nCases = 100;
        int[][] cap = new int[nNodes][nNodes];

        long t1 = System.currentTimeMillis();

        for (int k = 0; k < nCases; k++) {
            System.out.println(k);

            for (int r = 0; r < nNodes; r++)
                for (int c = 0; c < nNodes; c++)
                    cap[r][c] = random.nextInt(maxCapacity);
            MaxFlow flow = new MaxFlow();
            flow.getMaxFlow(cap, 0, nNodes - 1);
        }
        long t2 = System.currentTimeMillis();
        long time = t2 - t1;
        System.out.println(time);
    }

}