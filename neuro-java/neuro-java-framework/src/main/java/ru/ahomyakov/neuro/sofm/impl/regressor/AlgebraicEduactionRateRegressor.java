package ru.ahomyakov.neuro.sofm.impl.regressor;

import ru.ahomyakov.neuro.errors.IllegalInitDataException;
import ru.ahomyakov.neuro.sofm.interfaces.EducationRateRegressor;

public class AlgebraicEduactionRateRegressor implements EducationRateRegressor {
    protected double step = 0;

    public AlgebraicEduactionRateRegressor(double step) throws IllegalInitDataException {
        setStep(step);
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) throws IllegalInitDataException {
        if (step <= 0)
            throw new IllegalInitDataException("Step must be more than 0");
        this.step = step;
    }

    public double newEducationRate(double oldEducationRate) {
        double newEducationRate = oldEducationRate - step;
        if (newEducationRate < 0)
            return 0;
        return newEducationRate;
    }
}
