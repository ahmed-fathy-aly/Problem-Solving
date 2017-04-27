package data_structures;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by afathy on 4/27/17.
 */
public class GraphTest {

    @Test
    public void testConstructDirectedAndGetEdges() {
        Graph graph = new Graph(6);
        int sources[] = {1, 1, 1, 4, 4};
        int dest[] = {2, 3, 2, 5, 6};
        double weight[] = {1, 2, 3, 4, 5};
        for (int i = 0; i < sources.length; i++)
            graph.addDirectedEdge(sources[i], dest[i], weight[i]);

        List<Graph.Edge> edges = graph.getAllEdges();
        Collections.sort(edges, Comparator.comparingDouble(o -> o.weight));
        for (int i = 0; i < sources.length; i++) {
            assertEquals(sources[i], edges.get(i).source);
            assertEquals(dest[i], edges.get(i).dest);
            assertEquals(weight[i], edges.get(i).weight, 0.001);
        }
    }

    @Test
    public void testConstructInDirectedAndGetEdges() {
        Graph graph = new Graph(3);
        graph.addUnDirectedEdge(1, 2, 3);

        List<Graph.Edge> edges = graph.getAllEdges();
        assertEquals(2, edges.size());
        Collections.sort(edges, Comparator.comparingDouble(o -> o.source));
        assertEquals(1, edges.get(0).source);
        assertEquals(2, edges.get(0).dest);
        assertEquals(3, edges.get(0).weight, 0.1);
        assertEquals(2, edges.get(1).source);
        assertEquals(1, edges.get(1).dest);
        assertEquals(3, edges.get(1).weight, 0.1);
    }

    @Test
    public void testToString() {
        Graph graph = new Graph(6);
        int sources[] = {1, 1, 1, 4, 4};
        int dest[] = {2, 3, 2, 5, 6};
        double weight[] = {1, 2, 3, 4, 5};
        for (int i = 0; i < sources.length; i++)
            graph.addDirectedEdge(sources[i], dest[i], weight[i]);

        String result = graph.toString();
        String expected = "6 nodes\n" +
                "1 --2--> 1.000000\n" +
                "1 --3--> 2.000000\n" +
                "1 --2--> 3.000000\n" +
                "4 --5--> 4.000000\n" +
                "4 --6--> 5.000000\n";
        assertEquals(expected, result);
    }

}