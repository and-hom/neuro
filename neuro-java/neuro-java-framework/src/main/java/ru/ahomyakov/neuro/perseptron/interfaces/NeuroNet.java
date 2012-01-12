package ru.ahomyakov.neuro.perseptron.interfaces;

/**
 * Нейросеть
 */
public interface NeuroNet extends ru.ahomyakov.neuro.base.NeuroNet {

    public void reset();

    void addLayer(Layer layer);

    /**
     * @param in  вход
     * @param out требуемый выход
     * @param eta норма обучения
     * @return возврат результата для унификации с ФП
     */
    @Override
    NeuroNet teach(double[] in, double[] out, double eta);
}
