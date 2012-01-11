package ru.ahomyakov.neuro.sofm.interfaces;

import ru.ahomyakov.neuro.errors.IllegalDimensionException;

/**
 * Мера близости векторов. Например, евклидово расстояние между их концами или угол между ними.
 * Диапазон значений зависит от реализации.
 */
public interface VectorSimilarityRate {
    double getRate(double[] vector1, double[] vector2) throws IllegalDimensionException;
}
