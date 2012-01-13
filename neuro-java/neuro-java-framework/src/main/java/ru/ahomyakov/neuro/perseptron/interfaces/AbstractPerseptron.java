package ru.ahomyakov.neuro.perseptron.interfaces;


import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPerseptron implements Perseptron {
    protected List<Layer> layers;

    public AbstractPerseptron() {
        layers = new ArrayList<>(5);
    }

    public AbstractPerseptron(List<Layer> layers) {
        this.layers = layers;
    }

    public AbstractPerseptron reset() {
        for (Layer layer : layers)
            layer.reset();
        return this;
    }


    public double[] process(double[] in) {
        double[] result = in;
        for (Layer layer : layers)
            result = layer.operate(result);
        return result;
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }
}
