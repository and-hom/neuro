package ru.mai.k806.commons;

/**
 * Некая сеть, производящая классификацию
 */
public interface Classificator {
    double whatIsIt(double[] sample);
}
