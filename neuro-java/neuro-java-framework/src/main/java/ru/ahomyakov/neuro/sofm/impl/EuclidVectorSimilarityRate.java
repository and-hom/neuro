package ru.ahomyakov.neuro.sofm.impl;

import ru.ahomyakov.neuro.errors.IllegalDimensionException;
import ru.ahomyakov.neuro.sofm.interfaces.VectorSimilarityRate;

/**
 * Евклидово расстояние между векторами
 */
public class EuclidVectorSimilarityRate implements VectorSimilarityRate {
    public double getRate(double[] vector1, double[] vector2) throws IllegalDimensionException {
        if (vector1.length != vector2.length)
            throw new IllegalDimensionException("Length of vector1 is not equals length of vector2");
        double rate = 0;
        for (int i = 0; i < vector1.length; i++)
            rate += (vector1[i] - vector2[i])* (vector1[i] - vector2[i]);
        return Math.sqrt(rate);
    }
}
