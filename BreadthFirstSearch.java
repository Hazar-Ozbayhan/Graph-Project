/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje3;

import java.util.LinkedList;

public class BreadthFirstSearch {

    boolean[] marked;
    int[] edgeTo;
    int[] distTo;
    City from;

    public BreadthFirstSearch(ListGraph g, City from) {
        edgeTo = new int[g.getNumV()];
        marked = new boolean[g.getNumV()];
        distTo = new int[g.getNumV()];
        this.from = from;
        bfs(g, from.getId());
    }

    public boolean hasPathTo(City w) {
        return marked[w.getId()];
    }

    public int distTo(City w) {
        return distTo[w.getId()];
    }

    public Integer[] pathTo(City w) {
        int k = edgeTo[w.getId()];
        java.util.Stack<Integer> st = new java.util.Stack<Integer>();
        st.push(k);
        while (k != this.from.getId()) {
            k = edgeTo[k];
            st.push(k);
        }

        Integer[] path = new Integer[st.size()];
        for (int i = 0; i < path.length; i++) {
            path[i] = st.pop();
        }
        return path;
    }

    public void printPathTo(City w) {

        Integer[] path = pathTo(w);

        for (int i = 0; i < path.length; i++) {

            System.out.print("->" + path[i]);
        }
        System.out.print("->" + w);
    }

    public void bfs(ListGraph g, int source) {
        marked[source] = true;
        Integer[] a = (Integer[]) g.neighborsArray(source);
        if (a.length == 0) {
            return;
        }

        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(source);
        while (!q.isEmpty()) {
            source = q.removeFirst();
            a = (Integer[]) g.neighborsArray(source);
            for (int i = 0; i < a.length; i++) {
                int w = a[i];
                if (!marked[w]) {
                    System.out.println(w + ".");
                    q.addLast(w);
                    marked[w] = true;
                    edgeTo[w] = source;
                    distTo[w] = distTo[source] + 1;
                }
            }
        }
    }
}
