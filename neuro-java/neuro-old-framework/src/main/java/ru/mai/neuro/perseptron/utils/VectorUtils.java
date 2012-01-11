package ru.mai.neuro.perseptron.utils;

public class VectorUtils {
    public static double[] minus(double[] a, double[] b) {
        if (a.length != b.length)
            throw new Error("Different vector dimension");
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++)
            result[i] = a[i] - b[i];
        return result;

    }

    public static String printVector(double[] v) {
        StringBuffer result = new StringBuffer();
        result.append('\n');
        for (int i = 0; i < v.length; i++) {
            result.append(v[i] + "\t");
        }
        return result.toString();
    }

    public static String printMatrix(double[][] m) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < m.length; i++) {
            result.append('\n');
            for (int j = 0; j < m[i].length; j++)
                result.append(m[i][j] + "\t");
        }
        return result.toString();
    }
}
