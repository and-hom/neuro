package ru.ahomyakov.neuro.base;


public interface NeuroNet {

    /**
     * @param in  вход
     * @param out требуемый выход
     * @param eta норма обучения
     *
     * @return возврат результата для унификации с ФП
     */
    NeuroNet teach(double[] in, double[] out, double eta);

    double[] process(double[] in);
}
