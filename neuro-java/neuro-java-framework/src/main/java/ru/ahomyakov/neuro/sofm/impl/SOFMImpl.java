package ru.ahomyakov.neuro.sofm.impl;

import ru.ahomyakov.neuro.errors.IllegalDimensionException;
import ru.ahomyakov.neuro.errors.IllegalInitDataException;
import ru.ahomyakov.neuro.errors.NetIsNotInitalizedExceprion;
import ru.ahomyakov.neuro.sofm.impl.regressor.GeometricEduactionRateRegressor;
import ru.ahomyakov.neuro.sofm.interfaces.EducationRateRegressor;
import ru.ahomyakov.neuro.sofm.interfaces.SOFM;
import ru.ahomyakov.neuro.sofm.interfaces.VectorSimilarityRate;

import java.util.Random;

/**
 * Реализация SOFM.
 * Не запоминает объекты
 */
public class SOFMImpl implements SOFM {

    protected double[][] clusters;
    protected double educationRate[];
    protected double initalEducationRate = 0.99;
    protected EducationRateRegressor regressor;
    protected VectorSimilarityRate vectorSimilarityRate;

    public SOFMImpl() throws IllegalInitDataException {
        this.regressor = new GeometricEduactionRateRegressor(0.7);
        this.vectorSimilarityRate = new EuclidVectorSimilarityRate();
    }

    public SOFMImpl(EducationRateRegressor regressor, VectorSimilarityRate vectorSimilarityRate, double educationRate) throws IllegalInitDataException {
        this.regressor = regressor;
        this.vectorSimilarityRate = vectorSimilarityRate;
        setEducationRate(educationRate);
    }

    public void setEducationRate(double eduRate) throws IllegalInitDataException {
        if (eduRate <= 0 || eduRate >= 1) {
            throw new IllegalInitDataException("Education rate must be greate than 0, and less then 1");
        }
        this.initalEducationRate = eduRate;
    }

    public void init(int clusterNumber, int paramCount) throws IllegalInitDataException {
        if (clusterNumber < 2)
            throw new IllegalInitDataException("Cluster count must not be less than 2");
        if (paramCount < 1)
            throw new IllegalInitDataException("Feature count must be greate than 0");
        double[] center = new double[paramCount];
        for (int i = 0; i < paramCount; i++) {
            center[i] = 0;
        }
        init(clusterNumber, center);
    }

    public void init(int clusterNumber, double[] center) throws IllegalInitDataException {
        if (clusterNumber < 2)
            throw new IllegalInitDataException("Cluster count must not be less than 2");
        if (center == null || center.length == 0)
            throw new IllegalInitDataException("Feature count must be greate than 0");
        Random random = new Random(System.currentTimeMillis());
        clusters = new double[clusterNumber][];
        for (int i = 0; i < clusterNumber; i++) {
            clusters[i] = new double[center.length];
            for (int j = 0; j < center.length; j++) {
                clusters[i][j] = center[j] + 0.05 - random.nextDouble() * 0.1;
            }
        }
        educationRate = new double[clusterNumber];
        for (int i = 0; i < clusterNumber; i++)
            educationRate[i] = initalEducationRate;
    }

    public int getClusterNumber() {
        return clusters.length;
    }

    /**
     * <font color="red">Внимание! Метод возвращает ссылку на массив, используемый сетью. При изменении
     * чисел в массиве поведение сети может измениться!</font>
     *
     * @return массив значений признаков кластеров (1-е измерение - кластеры, 2-е - признаки по порядку)
     */
    public double[][] getClusters() {
        return clusters;
    }

    public int operate(double[] objectParams) throws IllegalDimensionException, NetIsNotInitalizedExceprion, IllegalInitDataException {
        return operate(objectParams, false);
    }

    public int operate(double[] objectParams, boolean locked) throws IllegalDimensionException, NetIsNotInitalizedExceprion, IllegalInitDataException {
        if (clusters == null || clusters.length == 0) {
            throw new NetIsNotInitalizedExceprion("There are no clusters");
        }
        if (clusters[0] == null || clusters[0].length == 0) {
            throw new NetIsNotInitalizedExceprion("There are no features");
        }
        if (vectorSimilarityRate == null) {
            throw new NetIsNotInitalizedExceprion("There are no vector similarity calculator");
        }
        if (regressor == null) {
            throw new NetIsNotInitalizedExceprion("There are no education rate regressor");
        }
        if (clusters[0].length != objectParams.length) {
            throw new IllegalDimensionException("Different feature count in object and map");
        }
        int leader = 0;
        double distance = vectorSimilarityRate.getRate(clusters[0], objectParams);
        double newDistance = 0;
        for (int i = 1; i < clusters.length; i++) {
            newDistance = vectorSimilarityRate.getRate(clusters[i], objectParams);
            if (newDistance < distance) {
                distance = newDistance;
                leader = i;
            }
        }
        if (!locked) {
            for (int j = 0; j < clusters[leader].length; j++)
                clusters[leader][j] += educationRate[leader] * (objectParams[j] - clusters[leader][j]);
            educationRate[leader] = regressor.newEducationRate(educationRate[leader]);
        }
        return leader;
    }
}
