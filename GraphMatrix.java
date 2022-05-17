/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje3;

/**
 *
 * @author rushl
 */
public class GraphMatrix {

    int edges[][];
    int numCities;
    int numE;

    public GraphMatrix(int V) {
        this.numCities = V;
        edges = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                edges[i][j] = 0;
            }
        }
    }

    public void addEdge(int from, int to, int weight) {
        edges[from][to] = weight;
    }

    public boolean isAdjacent(int v1, int v2) {
        return (edges[v1][v2] != 0);
    }

    public int degree(int v) {
        int degree = 0;
        for (int i = 0; i < numCities; i++) {
            if (edges[v][i] != 0) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                s.append(edges[i][j] + " ");
                //System.out.print(edges[i][j] + " ");
            }
            s.append("\n");
            // System.out.print("\n");

        }
        return s.toString();
    }

    public static void main(String[] args) {
        City a = new City("Ä°stanbul");
        GraphMatrix g = new GraphMatrix(5);
        g.addEdge(0, 1, 250);
        g.addEdge(0, 3, 300);
        g.addEdge(1, 4, 600);
        g.addEdge(3, 2, 700);
        g.addEdge(a.getId(), 0, 1000);
        //System.out.println(g);
        System.out.println(g.toString());
        g.printFartherCities(a);

    }

    public void printFartherCities(City city) {

        for (int i = 0; i < edges.length; i++) {
            if (edges[city.getId()][i] >= 500) {
                System.out.print(i + " ");
            }

        }
        System.out.print(" are far from " + city.getName() + "\n");

    }

    public void createCitiesFile(int edges, int vertices) {

    }
}
