package ru.mai.neuro.perseptron.interfaces;

import ru.ahomyakov.neuro.Perseptron;

/**
 * Нейросеть
 */
public interface NeuroNet extends Perseptron{
    
    public void reset();

    void addLayer(Layer layer);

    /**
     * @param in  вход
     * @param out требуемый выход
     * @param eta норма обучения
     *
     * @return возврат результата для унификации с ФП
     */
    @Override
    NeuroNet errorBackTrace(double[] in, double[] out, double eta);
}
