package ru.ahomyakov.neuro.perseptron.impl.functions;

import ru.ahomyakov.neuro.perseptron.interfaces.VectorFunction;

/**
 * Тождественная ф-ция активности
 */
public class EqualsFunction implements VectorFunction {
    public double[] f(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++)
            result[i] = x[i];
        return result;
    }

    public double[] df(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++)
            result[i] = 1;
        return result;
    }

    @Override
    public String toString() {
        return "Equals function y=x";
    }
}
