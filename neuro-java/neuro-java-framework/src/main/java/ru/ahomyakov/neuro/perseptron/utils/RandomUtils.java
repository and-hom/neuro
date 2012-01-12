package ru.ahomyakov.neuro.perseptron.utils;

import java.util.Random;

public class RandomUtils {
    protected static Random random = new Random();

    public synchronized static double[] randomVector(int dim) {
        double[] result = new double[dim];
        random.setSeed(System.currentTimeMillis());
        for (int i = 0; i < dim; i++)
            result[i] = 0.2 - random.nextDouble() / 10d;
        return result;
    }

    public synchronized static double[][] randomMatrix(int xDim, int yDim) {
        double[][] result = new double[xDim][yDim];
        random.setSeed(System.currentTimeMillis());
        for (int i = 0; i < xDim; i++)
            for (int j = 0; j < yDim; j++)
                result[i][j] = 0.2 - random.nextDouble() / 10d;
        return result;
    }
}
