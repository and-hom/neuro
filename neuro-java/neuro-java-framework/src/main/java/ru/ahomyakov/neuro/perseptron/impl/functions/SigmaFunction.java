package ru.ahomyakov.neuro.perseptron.impl.functions;

import ru.ahomyakov.neuro.perseptron.interfaces.VectorFunction;

public class SigmaFunction implements VectorFunction {
    protected double xCompression;
    protected double y0;
    protected double yMax;
    protected double barrier;

    public SigmaFunction() {
        yMax = 1;
        xCompression = 1;
    }

    public SigmaFunction(double xCompression, double y0, double yMax, double barrier) {
        this.xCompression = xCompression;
        this.y0 = y0;
        this.yMax = yMax;
        this.barrier = barrier;
    }

    public double[] f(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++)
            result[i] = y0 + yMax / (1 + Math.exp(-xCompression * x[i]));
        return result;
    }

    public double[] df(double[] x) {
        double[] v = f(x);
        double[] result = new double[v.length];
        for (int i = 0; i < v.length; i++)
            result[i] = v[i] * (1 - v[i]);
        return result;
    }

    public double getXCompression() {
        return xCompression;
    }

    public void setXCompression(double xCompression) {
        this.xCompression = xCompression;
    }

    public double getY0() {
        return y0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public double getYMax() {
        return yMax;
    }

    public void setYMax(double yMax) {
        this.yMax = yMax;
    }

    @Override
    public String toString() {
        return y0 + "+" + yMax + "/(1+exp(-" + xCompression + "x))";
    }
}
