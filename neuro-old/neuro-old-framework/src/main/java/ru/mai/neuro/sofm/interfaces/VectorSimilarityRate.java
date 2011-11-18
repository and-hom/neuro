package ru.mai.neuro.sofm.interfaces;

import ru.mai.neuro.errors.IllegalDimensionException;

/**
 * Мера близости векторов. Например, евклидово расстояние между их концами или угол между ними.
 * Диапазон значений зависит от реализации.
 */
public interface VectorSimilarityRate {
    double getRate(double[] vector1, double[] vector2) throws IllegalDimensionException;
}
