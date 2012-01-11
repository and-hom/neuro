package ru.ahomyakov.genetics.neuro.perseptron;

/**
 * Пример для обучения нейросети.
 */
public class Sample {
    protected double[] input;
    protected double[] output;

    public Sample() {
    }

    public Sample(double[] input, double[] output) {
        this.input = input;
        this.output = output;
    }

    public double[] getInput() {
        return input;
    }

    public void setInput(double[] input) {
        this.input = input;
    }

    public double[] getOutput() {
        return output;
    }

    public void setOutput(double[] output) {
        this.output = output;
    }
}
