package ru.mai.neuro.sofm.interfaces;

import ru.mai.neuro.errors.IllegalDimensionException;
import ru.mai.neuro.errors.IllegalInitDataException;
import ru.mai.neuro.errors.NetIsNotInitalizedExceprion;

/**
 * SOFM-сеть
 */
public interface SOFM {
    /**
     * Установка нормы обучения
     *
     * @param eduRate действительное число большее 0 и меньшее 1
     */
    void setEducationRate(double eduRate) throws IllegalInitDataException;

    /**
     * Инициализация сети (сброс всех предыдущих настроек)
     *
     * @param clusterNumber количество кластеров, целое положительное число
     * @param paramCount    количество признаков, по которым происходит кластеризация
     */
    void init(int clusterNumber, int paramCount) throws IllegalInitDataException;
    
    
    /**
     * Инициализация сети (сброс всех предыдущих настроек)
     *
     * @param clusterNumber количество кластеров, целое положительное число
     * @param center    точка, вблизи которой помещаются центроиды кластеров в начальный момент
     */
    void init(int clusterNumber, double[] center) throws IllegalInitDataException;

    
    /**
     * Возвращает количество кластеров в сети
     *
     * @return кол-во кластеров
     */
    int getClusterNumber();

    /**
     * Получить набор координат кластеров
     *
     * @return
     */
    double[][] getClusters();

    /**
     * @param objectParams значения признаков (размерность должна совпадать с кол-вом признаков сети)
     * @return номер кластера, к которому отнесён объект
     */
    int operate(double[] objectParams) throws IllegalDimensionException, NetIsNotInitalizedExceprion, IllegalInitDataException;

    /**
     * @param objectParams значения признаков (размерность должна совпадать с кол-вом признаков сети)
     * @param locked       блокировка обучения на момент теста
     * @return номер кластера, к которому отнесён объект
     */
    int operate(double[] objectParams, boolean locked) throws IllegalDimensionException, NetIsNotInitalizedExceprion, IllegalInitDataException;
}
