package data_structures;

import static org.junit.Assert.*;

import data_structures.UnionFind;
import org.junit.Test;

import java.util.*;

/**
 * Created by afathy on 4/23/17.
 */
public class UnionFindTest {

    @Test
    public void testSmall(){
        UnionFind unionFind = new UnionFind(6);

        assertFalse(unionFind.isJoined(1, 2));
        unionFind.union(1, 2);
        assertTrue(unionFind.isJoined(1, 2));

        assertFalse(unionFind.isJoined(1, 3));
        unionFind.union(2, 3);
        assertTrue(unionFind.isJoined(1, 3));

        assertFalse(unionFind.isJoined(1, 5));
        unionFind.union(2, 5);
        assertTrue(unionFind.isJoined(1, 5));
    }

    @Test
    public void testRandom(){
        int size = 10000;
        UnionFind unionFind =new UnionFind(size);
        DummyUnionFInd dummyUnionFInd = new DummyUnionFInd(size);

        Random random = new Random();
        int queries = 1000;
        for (int i = 0; i < queries; i++){
            int l = random.nextInt(size);
            int r = random.nextInt(size);

            boolean joined = unionFind.isJoined(l, r);
            boolean joinedExpected = dummyUnionFInd.joined(l, r);
            assertEquals(joinedExpected, joined);

            unionFind.union(l, r);
            dummyUnionFInd.union(l, r);
        }
    }

    @Test
    public void runningTimeTest(){
        int size = 10000000;
        UnionFind unionFind =new UnionFind(size);

        Random random = new Random();
        int queries = 10000000;
        for (int i = 0; i < queries; i++){
            int l = random.nextInt(size);
            int r = random.nextInt(size);
            unionFind.isJoined(l, r);
            unionFind.union(l, r);
        }
    }
    /**
     * uses bfs for union find
     * guaranteed to return the right answer so it's used for testing
     */
    class DummyUnionFInd{
        boolean[][] g;
        int n;

        DummyUnionFInd(int n){
                this.n = n;
                g = new boolean[n][n];
        }

        public boolean joined(int l, int r){
            boolean[] visited = new boolean[n];
            visited[l] = true;
            Queue<Integer> q = new LinkedList<>();
            q.add(l);
            while (!q.isEmpty()){
                int node = q.poll();
                if (node == r)
                    return true;
                for (int next = 0; next < n; next++) {
                    if (!visited[next] && g[node][next]){
                        q.add(next);
                        visited[next] = true;
                    }
                }
            }

            return false;
        }

        public void union(int l, int r){
            g[l][r] = true;
            g[r][l] = true;
        }
    }
}