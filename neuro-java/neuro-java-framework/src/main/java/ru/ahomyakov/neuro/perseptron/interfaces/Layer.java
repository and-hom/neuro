package ru.ahomyakov.neuro.perseptron.interfaces;

import ru.ahomyakov.neuro.base.VectorFunction;

/**
 * Слой (матрица весов + слой нейронов)
 */
public interface Layer {
    void setActivityFunction(VectorFunction function);

    double[] operate(double[] in);

    /**
     * @param error ошибка
     * @param eta   норма обучения
     * @return ошибка след слоя
     */
    double[] teach(double[] error, double eta);

    void reset();
}
