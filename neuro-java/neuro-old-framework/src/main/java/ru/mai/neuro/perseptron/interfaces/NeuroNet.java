package ru.mai.neuro.perseptron.interfaces;

/**
 * Нейросеть
 */
public interface NeuroNet {
    
    public void reset();
    void addLayer(Layer layer);

    /**
     * @param in  вход
     * @param out требуемый выход
     * @param eta норма обучения
     */
    void teach(double[] in, double[] out, double eta);

    double[] operate(double[] in);
}
