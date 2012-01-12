/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ahomyakov.neuro.kurs;

import java.awt.geom.Point2D;

/**
 * @author and_hom
 */
public class SofmPoint extends Point2D.Double {

    protected int clusterNumber = -1;

    public SofmPoint(double x, double y, int clusterNumber) {
        super(x, y);
        this.clusterNumber = clusterNumber;
    }

    public SofmPoint(Point2D.Double point, int clusterNumber) {
        super(point.x, point.y);
        this.clusterNumber = clusterNumber;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }
}
