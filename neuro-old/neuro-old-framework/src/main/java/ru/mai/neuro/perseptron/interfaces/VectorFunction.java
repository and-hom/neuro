package ru.mai.neuro.perseptron.interfaces;

/**
 *
 */
public interface VectorFunction {
    double[] f(double[] x);
    double[] df(double[] x);
}
