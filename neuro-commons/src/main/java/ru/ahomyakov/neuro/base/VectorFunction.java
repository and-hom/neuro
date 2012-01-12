package ru.ahomyakov.neuro.base;

/**
 *
 */
public interface VectorFunction {
    double[] f(double[] x);

    double[] df(double[] x);
}
