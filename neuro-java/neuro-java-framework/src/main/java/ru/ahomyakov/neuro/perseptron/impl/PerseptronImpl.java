package ru.ahomyakov.neuro.perseptron.impl;

import ru.ahomyakov.neuro.VectorUtils;
import ru.ahomyakov.neuro.perseptron.interfaces.Layer;
import ru.ahomyakov.neuro.perseptron.interfaces.Perseptron;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PerseptronImpl implements Perseptron {
    List<Layer> layers;

    public PerseptronImpl() {
        layers = new ArrayList<>(5);
    }

    public PerseptronImpl(List<Layer> layers) {
        this.layers = layers;
    }

    public PerseptronImpl reset() {
        for (Layer layer : layers)
            layer.reset();
        return this;
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public PerseptronImpl teach(double[] in, double[] out, double eta) {
        double[] result = process(in);
        double[] error = VectorUtils.minus(out, result);
        ListIterator<Layer> iter = layers.listIterator(layers.size());
        while (iter.hasPrevious())
            error = iter.previous().teach(error, eta);
        return this;
    }

    public double[] process(double[] in) {
        double[] result = in;
        for (Layer layer : layers)
            result = layer.operate(result);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Neuro network \n" + layers.size() + " layers\n");
        for (Layer layer : layers)
            result = result.append(layer.toString());
        return result.toString();
    }
}
