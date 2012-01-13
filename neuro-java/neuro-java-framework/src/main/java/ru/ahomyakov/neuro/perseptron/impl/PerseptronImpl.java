package ru.ahomyakov.neuro.perseptron.impl;

import ru.ahomyakov.neuro.VectorUtils;
import ru.ahomyakov.neuro.perseptron.interfaces.AbstractPerseptron;
import ru.ahomyakov.neuro.perseptron.interfaces.Layer;

import java.util.List;
import java.util.ListIterator;

public class PerseptronImpl extends AbstractPerseptron {

    public PerseptronImpl() {
        super();
    }

    public PerseptronImpl(List<Layer> layers) {
        super(layers);
    }


    public PerseptronImpl teach(double[] in, double[] out, double eta) {
        double[] result = process(in);
        double[] error = VectorUtils.minus(out, result);
        ListIterator<Layer> iter = layers.listIterator(layers.size());
        while (iter.hasPrevious())
            error = iter.previous().teach(error, eta);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Neuro network \n" + layers.size() + " layers\n");
        for (Layer layer : layers)
            result = result.append(layer.toString());
        return result.toString();
    }
}
