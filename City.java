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
public class City {

    private final String name;
    private final int id;
    private static int count = 0;

    public City(String name) {
        this.name = name;
        id = count++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "City{" + "name=" + name + ", id=" + id + '}';
    }
}
