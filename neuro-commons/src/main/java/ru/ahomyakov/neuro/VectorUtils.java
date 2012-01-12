package ru.ahomyakov.neuro;

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
        StringBuilder result = new StringBuilder();
        result.append('\n');
        for (double aV : v) {
            result.append(aV).append("\t");
        }
        return result.toString();
    }

    public static String printMatrix(double[][] m) {
        StringBuilder result = new StringBuilder();
        for (double[] aM : m) {
            result.append('\n');
            for (double anAM : aM)
                result.append(anAM).append("\t");
        }
        return result.toString();
    }

    public static String printMatrixT(double[][] m) {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < m[0].length; j++) {
            result.append('\n');
            for (double[] aM : m) result.append(aM[j]).append("\t");
        }
        return result.toString();
    }
}
