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
public class Edge {
     private City from;
    private City to;
    private int distance;

    public Edge(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
