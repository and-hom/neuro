package ru.mai.neuro.sofm.impl.regressor;

import ru.mai.neuro.errors.IllegalInitDataException;
import ru.mai.neuro.sofm.interfaces.EducationRateRegressor;

public class GeometricEduactionRateRegressor implements EducationRateRegressor {
    protected double step = 0.3;

    /**
     * newVal=step*oldVal
     *
     * @param step
     */
    public GeometricEduactionRateRegressor(double step) throws IllegalInitDataException {
        setStep(step);
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) throws IllegalInitDataException {
        if (step <= 0)
            throw new IllegalInitDataException("Step must be more than 0 and less than 1");
        this.step = step;
    }

    public double newEducationRate(double oldEducationRate) {
        double newEducationRate = oldEducationRate * step;
        if (newEducationRate < 0)
            return 0;
        return newEducationRate;
    }
}