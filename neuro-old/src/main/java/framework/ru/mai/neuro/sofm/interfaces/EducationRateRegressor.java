package ru.mai.neuro.sofm.interfaces;

import ru.mai.neuro.errors.IllegalInitDataException;

/**
 *
 */
public interface EducationRateRegressor {
    void setStep(double step) throws IllegalInitDataException;

    public double newEducationRate(double oldEducationRate);
}
