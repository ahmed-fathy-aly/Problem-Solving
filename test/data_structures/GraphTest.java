package data_structures;

import org.junit.Test;

import java.util.Arrays;
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

    @Test
    public void testDijkstra1(){
        Graph graph = new Graph(6);
        graph.addUnDirectedEdge(0, 1, 1);
        graph.addUnDirectedEdge(0, 3, 7);
        graph.addUnDirectedEdge(0, 5, 2);
        graph.addUnDirectedEdge(1, 2, 3);
        graph.addUnDirectedEdge(1, 4, 2);
        graph.addUnDirectedEdge(2, 3, 1);
        graph.addUnDirectedEdge(2, 4, 1);
        graph.addUnDirectedEdge(4, 5, 3);

        assertArrayEquals(new double[]{0, 1, 4, 5, 3, 2}, graph.disjktra(0), 0.01);
        assertArrayEquals(new double[]{1, 0, 3, 4, 2, 3}, graph.disjktra(1), 0.01);

    }

    @Test
    public void testDijkstra2(){
        Graph graph = new Graph(4);
        graph.addDirectedEdge(0, 1, 1);
        graph.addDirectedEdge(1, 2, 1);
        graph.addDirectedEdge(2, 3, 1);

        assertArrayEquals(new double[]{0, 1, 2, 3}, graph.disjktra(0), 0.1);
        assertArrayEquals(new double[]{Double.MAX_VALUE, 0, 1, 2}, graph.disjktra(1), 0.1);
        assertArrayEquals(new double[]{Double.MAX_VALUE, Double.MAX_VALUE, 0, 1}, graph.disjktra(2), 0.1);
        assertArrayEquals(new double[]{Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, 0}, graph.disjktra(3), 0.1);

    }

    @Test
    public void testDijkstra3(){
        Graph graph = new Graph(5);
        graph.addUnDirectedEdge(1, 2, 24);
        graph.addUnDirectedEdge(1, 4, 20);
        graph.addUnDirectedEdge(3, 1, 3);
        graph.addUnDirectedEdge(4, 3, 12);

        assertArrayEquals(new double[]{Double.MAX_VALUE, 0, 24, 3, 15}, graph.disjktra(1), 0.1);
    }
}