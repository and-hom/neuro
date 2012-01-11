package ru.ahomyakov.neuro.perseptron.impl.functions;

import ru.ahomyakov.neuro.perseptron.interfaces.VectorFunction;

/**
 * Барьерная ф-ция активности
 */
public class BarierFunction implements VectorFunction {
    double barier = 0;
    double downValue = 0;
    double upValue = 1;

    public BarierFunction() {
    }

    public BarierFunction(double barier, double downValue, double upValue) {
        this.barier = barier;
        this.downValue = downValue;
        this.upValue = upValue;
    }

    public double[] f(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++)
            result[i] = x[i] > barier ? upValue : downValue;
        return result;
    }

    //  Аппроксимируем сигмоидальной ф-цией
    public double[] df(double[] x) {
        return new SigmaFunction(1, downValue, upValue, barier).df(x);
    }

//   Простая ф-ция активности
//    public double[] df(double[] x) {
//        double[] result = new double[x.length];
//        for (int i = 0; i < x.length; i++)
//            result[i] = 1;
//        return result;
//    }

//  Правильная ф-ция
//    public double[] df(double[] x) {
//        double[] result = new double[x.length];
//        for (int i = 0; i < x.length; i++)
//            if (x[i] == 0)
//                result[i] = 1000;
//            else result[i] = 0;
//        return result;
//    }

    public double getBarier() {
        return barier;
    }

    public void setBarier(double barier) {
        this.barier = barier;
    }

    public double getDownValue() {
        return downValue;
    }

    public void setDownValue(double downValue) {
        this.downValue = downValue;
    }

    public double getUpValue() {
        return upValue;
    }

    public void setUpValue(double upValue) {
        this.upValue = upValue;
    }

    @Override
    public String toString() {
        return "Barrier function: barrier=" + barier + "; lVal=" + downValue + "; rVal=" + upValue;
    }
}
