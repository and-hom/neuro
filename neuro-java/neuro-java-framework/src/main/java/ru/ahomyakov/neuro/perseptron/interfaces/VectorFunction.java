package ru.ahomyakov.neuro.perseptron.interfaces;

/**
 *
 */
public interface VectorFunction {
    double[] f(double[] x);

    double[] df(double[] x);
}
