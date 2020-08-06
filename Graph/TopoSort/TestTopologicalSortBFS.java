package Graph.TopoSort;

import Graph.*;
import org.junit.Test;
public class TestTopologicalSortBFS {
    @Test
    public void test1() {
        EdgeWeightedDigraph dg = new EdgeWeightedDigraph(6);
        DirectedEdge[] edges = new DirectedEdge[6];
        edges[0] = new DirectedEdge(1, 0, 1);
        edges[1] = new DirectedEdge(0, 5, 1);
        edges[2] = new DirectedEdge(3, 1, 1);
        edges[3] = new DirectedEdge(3, 2, 1);
        edges[4] = new DirectedEdge(4, 2, 1);
        edges[5] = new DirectedEdge(4, 5, 1);
        for (DirectedEdge e : edges) {
            dg.addEdge(e);
        }

        TopologicalSortBFS ts = new TopologicalSortBFS(dg);
        System.out.println(ts.sort());
    }
}
