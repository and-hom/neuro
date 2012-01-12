package ru.mai.k806.commons;

/**
 */
public class Sample {

    public Sample(double[] input, double[] output) {
        this.input = input;
        this.output = output;
    }

    public int getInputLength() {
        return input.length;
    }

    public int getOutputLength() {
        return output.length;
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

    protected double[] input;
    protected double[] output;
}
