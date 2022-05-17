/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Project3 {

    static Scanner scn = new Scanner(System.in);
    static ArrayList<Edge> allEdges = new ArrayList<>();
    static ArrayList<City> allCities = new ArrayList<>();
    static HashMap<City, ArrayList<Edge>> HM = new HashMap();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        createCitiesFile(10, 20);
        System.out.println(createCityGraph("cities.txt"));
        addToHashMap();

    }

    public static void addToHashMap() {
        for (int i = 0; i < allCities.size(); i++) {
            City theCity = allCities.get(i);
            if (!HM.containsKey(theCity)) {
                for (Edge allEdge : allEdges) {
                    ArrayList<Edge> list = new ArrayList<>();
                    if (allEdge.getFrom().getId() == allCities.get(i).getId()) {
                        list.add(allEdge);
                        HM.put(allCities.get(i), list);
                    }
                }
            } else {

                for (Edge allEdge : allEdges) {
                    if (allEdge.getFrom().getId() == allCities.get(i).getId()) {
                        HM.get(i).add(allEdge);
                    }

                }
            }
        }
    }

    public static void createCitiesFile(int edges, int vertices) throws FileNotFoundException, IOException {
        File cities = new File("cities.txt");

        FileWriter fr = new FileWriter(cities);
        fr.write(edges + "\n");
        fr.write(vertices + "\n");

        Random a = new Random();
        for (int i = 0; i < edges; i++) {
            int distance = (int) (Math.random() * 450 + 80);

            fr.write(a.nextInt(edges) + " ");
            fr.write(a.nextInt(edges) + " ");
            fr.write((int) distance + "\n");
        }

        fr.close();

        /*City a1 = new City("İstanbul");  
         City a2 = new City("Paris");
         City a3 = new City("Ankara");
         City a4 = new City("Berlin");
         City a5 = new City("Londra");
         City a6 = new City("Tokio");
         City a7 = new City("Roma");
         City a8 = new City("Stockholm");
         City a9 = new City("Madrid");
         City a10 = new City("Delhi");*/
        Files.readAllLines(cities.toPath());
    }

    public static ListGraph createCityGraph(String fileName) throws IOException {
        File b = new File(fileName);
        List<String> a = Files.readAllLines(b.toPath());
        int v = Integer.parseInt(a.get(0));
        int e = Integer.parseInt(a.get(1));
        System.out.println("File Loaded!!!!!!!!\n");
        System.out.println("Number of Cities= " + v + "\nNumber of Edges= " + e + "\n");

        ListGraph graph = new ListGraph(v);

        for (int i = 2; i < a.size(); i++) {
            String str = a.get(i);
            String[] set = str.split("\\s+");
            int v1 = Integer.parseInt(set[0]);
            int v2 = Integer.parseInt(set[1]);
            int v3 = Integer.parseInt(set[2]);
            City city = new City("City" + (i - 2));
            allCities.add(city);
            graph.addEdge(v1, v2, v3);
            Edge edge = new Edge(new City("city" + v1), new City("city" + v2), v3);
            allEdges.add(edge);
        }
        return graph;
    }

    public void printFartherCities(City toCity) {
        int count = 0;
        for (int i = 0; i <= allEdges.size(); i++) {
            Edge edge = allEdges.get(i);
            if (edge.getTo().getId() == toCity.getId()) {
                if (edge.getDistance() >= 500) {
                    System.out.println(edge.getFrom() + " is far from " + edge.getTo());
                    count++;
                }
            }

        }
        if (count == 0) {
            System.out.println("There isn't any pair of cities which they're far to each other like 500KM or more");
        }

    }

    public void printCloseCities(City toCity) {
        int count = 0;
        for (int i = 0; i <= allEdges.size(); i++) {
            Edge edge = allEdges.get(i);
            if (edge.getTo().getId() == toCity.getId()) {
                if (edge.getDistance() <= 100) {
                    System.out.println(edge.getFrom() + " is close to " + edge.getTo());
                }
            }

        }
        if (count == 0) {
            System.out.println("There isn't any pair of cities which they're close to each other like 100KM or less");
        }

    }

    public static boolean isConnected(City from, City to) {
        for (int i = 0; i <= allEdges.size(); i++) {
            Edge edge = allEdges.get(i);
            if (edge.getFrom().equals(from) && edge.getTo().equals(to)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void pathTo(City city1, City city2) throws IOException {
        BreadthFirstSearch bfs = new BreadthFirstSearch(createCityGraph("cities.txt"), city1);
        bfs.pathTo(city2);
    }

    public static void numberOfCityGroups() throws IOException {
        ConnectedComponents cc = new ConnectedComponents(createCityGraph("cities.txt"));
        System.out.println("num of connected components" + cc.getCount());
    }

    public static void minimumDistance(City city1, City city2) throws IOException {
        BreadthFirstSearch bfs = new BreadthFirstSearch(createCityGraph("cities.txt"), city1);
        if (bfs.hasPathTo(city1) == true) {
            System.out.println(city1.getName() + " BFS hasPathTo " + city2.getName());
            System.out.println("Printing the path");
            bfs.printPathTo(city2);
        } else {
            System.out.println("There isn't any path from" + city1.getName() + " to " + city2.getName());
        }
        System.out.println(" Distance for cit1 to city 2 =" + bfs.distTo(city2));

    }
}
