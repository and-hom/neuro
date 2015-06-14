package ru.mai.k806.commons;

import java.util.Random;

/**
 *
 */
public class Claster {

    public Claster(int dimension) {
        centroid = new double[dimension];
        Random random = new Random();
        for (int i = 0; i < dimension; i++)
            centroid[i] = random.nextDouble();
    }

    public Claster(double[] centroid) {
        this.centroid = centroid;
    }

    public double[] getCentroid() {
        return centroid;
    }

    public void setCentroid(double[] centroid) {
        this.centroid = centroid;
    }

    double[] centroid;
}
