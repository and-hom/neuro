package ru.ahomyakov.neuro.perseptron.impl;

import ru.ahomyakov.neuro.NotImplementedException;
import ru.ahomyakov.neuro.perseptron.interfaces.AbstractPerseptron;
import ru.ahomyakov.neuro.perseptron.interfaces.Layer;
import ru.ahomyakov.neuro.perseptron.interfaces.Perseptron;

import java.util.List;

/**
 * Сеть встречного распространения со слоями гроссберга и кохонена
 */
public class СounterРropagationNeuroNet extends AbstractPerseptron {
    public СounterРropagationNeuroNet() {
        super();
    }

    public СounterРropagationNeuroNet(List<Layer> layers) {
        super(layers);
    }

    @Override
    public Perseptron teach(double[] in, double[] out, double eta) {
        throw new NotImplementedException();
    }
}
