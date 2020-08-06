package Graph.SCC;

import Graph.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TestKosarajuSCC {
    @Test
    public void test() {
        EdgeWeightedDigraph dg = new EdgeWeightedDigraph(8);
        DirectedEdge[] edges = new DirectedEdge[13];
        edges[0] = new DirectedEdge(3, 7, 1);
        edges[1] = new DirectedEdge(7, 3, 1);
        edges[2] = new DirectedEdge(3, 4, 1);
        edges[3] = new DirectedEdge(7, 5, 1);
        edges[4] = new DirectedEdge(4, 5, 1);
        edges[5] = new DirectedEdge(5, 6, 1);
        edges[6] = new DirectedEdge(6, 4, 1);
        edges[7] = new DirectedEdge(5, 0, 1);
        edges[8] = new DirectedEdge(6, 0, 1);
        edges[9] = new DirectedEdge(6, 2, 1);
        edges[10] = new DirectedEdge(2, 0, 1);
        edges[11] = new DirectedEdge(0, 1, 1);
        edges[12] = new DirectedEdge(1, 2, 1);
        /*
           SCC 3  -> 4  -> 0
               7     5     1
                     6     2
         */
        for (DirectedEdge e : edges) {
            dg.addEdge(e);
        }

        KosarajuSCC kosaraju = new KosarajuSCC(dg);
        // 3
        int m = kosaraju.countStronglyConnectedComponents();
        System.out.println(m + " Components");
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new LinkedList<>();
        }
        for (int i = 0; i < dg.V(); i++) {
            components[kosaraju.getId(i)].add(i);
        }
        System.out.println("Print SCC");
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void test2() {
        EdgeWeightedDigraph dg = new EdgeWeightedDigraph(5);
        DirectedEdge[] edges = new DirectedEdge[5];
        edges[0] = new DirectedEdge(0, 1, 1);
        edges[1] = new DirectedEdge(1, 2, 1);
        edges[2] = new DirectedEdge(2, 0, 1);
        edges[3] = new DirectedEdge(1, 3, 1);
        edges[4] = new DirectedEdge(3, 4, 1);
        /*
            SCC 0  -> 3 -> 4
                1
                2
         */
        for (DirectedEdge e : edges) {
            dg.addEdge(e);
        }

        KosarajuSCC kosaraju = new KosarajuSCC(dg);
        int m = kosaraju.countStronglyConnectedComponents();
        System.out.println(m + " Components");
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new LinkedList<>();
        }
        for (int i = 0; i < dg.V(); i++) {
            components[kosaraju.getId(i)].add(i);
        }
        System.out.println("Print SCC");
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
