package ru.mai.neuro.perseptron.impl;

import ru.ahomyakov.neuro.VectorUtils;
import ru.mai.neuro.perseptron.interfaces.Layer;
import ru.mai.neuro.perseptron.interfaces.NeuroNet;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class NeuroNetImpl implements NeuroNet {
    List<Layer> layers;

    public NeuroNetImpl() {
        layers = new ArrayList<Layer>(5);
    }

    public NeuroNetImpl(List<Layer> layers) {
        this.layers = layers;
    }

    public void reset() {
        for (Layer layer : layers)
            layer.reset();
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public NeuroNetImpl errorBackTrace(double[] in, double[] out, double eta) {
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
