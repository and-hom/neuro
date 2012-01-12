package ru.ahomyakov.neuro.sofm.impl;

import ru.ahomyakov.neuro.errors.IllegalDimensionException;
import ru.ahomyakov.neuro.sofm.interfaces.VectorSimilarityRate;

/**
 * Угол между векторами
 */
public class AngleSimilarityRateImpl implements VectorSimilarityRate {
    protected double[] startPoint;

    public AngleSimilarityRateImpl() {
    }

    public AngleSimilarityRateImpl(double[] startPoint) {
        this.startPoint = startPoint;
    }

    public double getRate(double[] vector1, double[] vector2) throws IllegalDimensionException {
        if (vector1.length != vector2.length)
            throw new IllegalDimensionException("Length of vector1 is not equals length of vector2");
        if (startPoint != null && vector1.length != startPoint.length)
            throw new IllegalDimensionException("Vector 1 dimension is not equals space dimension");
        if (startPoint == null) {
            return angleCenterIs0(vector1, vector2);
        } else {
            return angleCenterIsNot0(vector1, vector2);
        }
    }

    private double angleCenterIs0(double[] vector1, double[] vector2) {
        double scalarProduct = 0;
        for (int i = 0; i < vector1.length; i++)
            scalarProduct += vector1[i] * vector2[i];
        double rate1 = 0;
        for (double d : vector1)
            rate1 += d;
        double rate2 = 0;
        for (double d : vector2)
            rate2 += d;
        double r = Math.sqrt(rate1 * rate2);
        return Math.acos(scalarProduct / r);
    }

    private double angleCenterIsNot0(double[] vector1, double[] vector2) {
        double scalarProduct = 0;
        for (int i = 0; i < vector1.length; i++)
            scalarProduct += (vector1[i] - startPoint[i]) * (vector2[i] - startPoint[i]);
        double rate1 = 0;
        for (int i = 0; i < vector1.length; i++)
            rate1 += (vector1[i] - startPoint[i]) * (vector1[i] - startPoint[i]);
        double rate2 = 0;
        for (int i = 0; i < vector2.length; i++)
            rate2 += (vector2[i] - startPoint[i]) * (vector2[i] - startPoint[i]);
        double r = Math.sqrt(rate1 * rate2);
        return Math.acos(scalarProduct / r);
    }
}
