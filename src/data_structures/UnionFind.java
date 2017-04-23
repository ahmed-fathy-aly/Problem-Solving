package data_structures;

import java.util.Arrays;

/**
 * Disjoint set union find with path compression and union by rank
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 *
 *
 */
public class UnionFind {

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
