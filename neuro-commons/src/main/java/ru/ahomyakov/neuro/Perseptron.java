package ru.ahomyakov.neuro;


public interface Perseptron {

    /**
     * @param in  вход
     * @param out требуемый выход
     * @param eta норма обучения
     *
     * @return возврат результата для унификации с ФП
     */
    Perseptron errorBackTrace(double[] in, double[] out, double eta);

    double[] process(double[] in);
}
