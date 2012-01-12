package ru.ahomyakov.neuro.perseptron.interfaces;

import ru.ahomyakov.neuro.base.NeuroNet;

/**
 * Нейросеть
 */
public interface Perseptron extends NeuroNet {

    public Perseptron reset();

    void addLayer(Layer layer);

    /**
     * @param in  вход
     * @param out требуемый выход
     * @param eta норма обучения
     * @return возврат результата для унификации с ФП
     */
    @Override
    Perseptron teach(double[] in, double[] out, double eta);
}
