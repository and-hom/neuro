package ru.mai.neuro.perseptron.impl;

import ru.mai.neuro.perseptron.interfaces.Layer;
import ru.mai.neuro.perseptron.interfaces.VectorFunction;
import ru.mai.neuro.perseptron.utils.RandomUtils;
import ru.mai.neuro.perseptron.utils.VectorUtils;

public class LayerImpl implements Layer {
    double[][] weightMatrix;
    double[] movementVector;
    VectorFunction activityFunction;
    double[] input;
    double[] weightInput;

    public LayerImpl(int inputNumber, int outputNumber, VectorFunction activityFunction) {
        weightMatrix = RandomUtils.randomMatrix(inputNumber, outputNumber);
        movementVector = RandomUtils.randomVector(outputNumber);
        this.activityFunction = activityFunction;
    }

    public LayerImpl(double[][] weightMatrix, double[] movementVector, VectorFunction activityFunction) {
        this.weightMatrix = weightMatrix;
        this.movementVector = movementVector;
        this.activityFunction = activityFunction;
    }

    public VectorFunction getActivityFunction() {
        return activityFunction;
    }

    public double[][] getWeightMatrix() {
        return weightMatrix;
    }

    public void setWeightMatrix(double[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
    }

    public void setWeightMatrixValue(double value, int inputNumber, int outputNumber) {
        weightMatrix[inputNumber][outputNumber] = value;
    }

    public double getWeightMatrixValue(int inputNumber, int outputNumber) {
        return weightMatrix[inputNumber][outputNumber];
    }

    @Override
    public void setActivityFunction(VectorFunction activityFunction) {
        this.activityFunction = activityFunction;
    }

    @Override
    public double[] operate(double[] in) {
        double[] result = new double[weightMatrix[0].length];
        this.input = in;
        for (int j = 0; j < result.length; j++) {
            result[j] = movementVector[j];
            for (int i = 0; i < in.length; i++)
                result[j] += in[i] * weightMatrix[i][j];
        }
        weightInput = result;
        return activityFunction.f(result);
    }

    /**
     * Дельта-правило. Коррекция весов рёбер.
     *
     * @param error ошибка
     * @param eta   норма обучения
     * @return ошибка след слоя
     */
    @Override
    public double[] teach(double[] error, double eta) {
        double[] result = new double[weightMatrix.length];
        // Производная ф-ции активности по входному сигналу
        double[] dfdnet = activityFunction.df(weightInput);

        double[] delta = new double[dfdnet.length];
        for (int j = 0; j < delta.length; j++)
            delta[j] = dfdnet[j] * error[j];

        // Распространение ошибки
        for (int i = 0; i < weightMatrix.length; i++) {
            result[i] = 0;
            for (int j = 0; j < movementVector.length; j++)
                result[i] += weightMatrix[i][j] * delta[j];
        }
        // Корректировка весов
        for (int j = 0; j < error.length; j++) {
            for (int i = 0; i < weightMatrix.length; i++) {
                weightMatrix[i][j] += eta * delta[j] * input[i];
            }
            movementVector[j] += eta * delta[j];
        }
        return result;
    }

    public void reset() {
        try {
            weightMatrix = RandomUtils.randomMatrix(weightMatrix.length, weightMatrix[0].length);
            movementVector = RandomUtils.randomVector(movementVector.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Layer\n ActivityFunction " + activityFunction.toString() +
                "\n" + weightMatrix.length + " inputs\n" + movementVector.length + " outputs\n" +
                "weights: " + VectorUtils.printMatrix(weightMatrix) +
                "\nmovement: " + VectorUtils.printVector(movementVector) + "\n";
    }
}
